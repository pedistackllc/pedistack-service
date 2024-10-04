package com.pedistack.admins.operation.managers;

import com.pedistack.admins.v1_0.AdminUserRequest;
import com.pedistack.admins.v1_0.common.AdminUser;
import com.pedistack.common.authorization.CredentialStatus;
import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.authorization.UserStatus;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.common.utils.References;
import com.pedistack.db.admins.AdminUserEntity;
import com.pedistack.db.admins.AdminUserEntityDaoManager;
import com.pedistack.db.oauth.*;
import com.pedistack.oauth.v1_0.common.ProfileType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminUserOperationManagerBean implements AdminUserOperationManager {

  private final AdminUserEntityDaoManager adminUserEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final ProfileEntityDaoManager profileEntityDaoManager;
  private final CredentialEntityDaoManager credentialEntityDaoManager;
  private final ApplicationEventPublisher applicationEventPublisher;

  public AdminUserOperationManagerBean(
      AdminUserEntityDaoManager adminUserEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager,
      ProfileEntityDaoManager profileEntityDaoManager,
      CredentialEntityDaoManager credentialEntityDaoManager,
      ApplicationEventPublisher applicationEventPublisher) {
    this.adminUserEntityDaoManager = adminUserEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.credentialEntityDaoManager = credentialEntityDaoManager;
    this.profileEntityDaoManager = profileEntityDaoManager;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  @Transactional
  public void activateAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity =
        adminUserEntityDaoManager.findByIdentifier(adminIdentifier);
    final UserEntity userEntity = adminUserEntity.getUser();
    userEntity.setStatus(UserStatus.ACTIVE.name());
    userEntityDaoManager.save(userEntity);
  }

  @Override
  @Transactional
  public void blockAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity =
        adminUserEntityDaoManager.findByIdentifier(adminIdentifier);
    final UserEntity userEntity = adminUserEntity.getUser();
    userEntity.setStatus(UserStatus.BLOCKED.name());
    userEntityDaoManager.save(userEntity);
  }

  @Override
  @Transactional
  public void unblockAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity =
        adminUserEntityDaoManager.findByIdentifier(adminIdentifier);
    final UserEntity userEntity = adminUserEntity.getUser();
    userEntity.setStatus(UserStatus.ACTIVE.name());
    userEntityDaoManager.save(userEntity);
  }

  @Override
  @Transactional
  public void closeAdminUser(
      String sessionUserIdentifier, String sessionReference, String adminIdentifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity =
        adminUserEntityDaoManager.findByIdentifier(adminIdentifier);
    final UserEntity userEntity = adminUserEntity.getUser();
    userEntity.setStatus(UserStatus.CLOSED.name());
    userEntityDaoManager.save(userEntity);
  }

  @Override
  @Transactional
  public AdminUser create(String tenant,
      String sessionUserIdentifier, String sessionReference, AdminUserRequest adminUserRequest)
      throws PedistackException {
    userEntityDaoManager.checkExistingUsername(adminUserRequest.getUsername());
    userEntityDaoManager.checkExistingEmailAddress(adminUserRequest.getEmailAddress());
    final ProfileEntity adminProfileEntity =
        profileEntityDaoManager.findByName(adminUserRequest.getProfile());
    if (!Stream.of(
            ProfileType.ADMINISTRATOR,
            ProfileType.FINANCIAL_ADMINISTRATOR,
            ProfileType.SUPER_ADMINISTRATOR)
        .map(ProfileType::name)
        .toList()
        .contains(adminProfileEntity.getType())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.PROFILE_TYPE_INVALID_ERROR_DESCRIPTION);
    }

    final UserEntity userEntity = new UserEntity();
    userEntity.setProfile(adminProfileEntity);
    userEntity.setStatus(UserStatus.ACTIVE.name());
    userEntity.setEmailAddress(adminUserRequest.getEmailAddress());
    userEntity.setUsername(adminUserRequest.getUsername());
    userEntity.setLoginAttempts(0);
    userEntity.setClientId(References.createReference(32));
    userEntity.setTwoFactorAuthenticationStatus(Boolean.FALSE);
    userEntityDaoManager.save(userEntity);

    final CredentialEntity credentialEntity = new CredentialEntity();
    credentialEntity.setCredentialReference(References.createReference(64));
    credentialEntity.setCredential(adminUserRequest.getPassword());
    credentialEntity.setUser(userEntity);
    credentialEntity.setType(CredentialType.PASSWORD.name());
    credentialEntity.setStatus(CredentialStatus.ACTIVE.name());
    credentialEntity.setExpiryDate(
        Date.from(LocalDateTime.now().plusMonths(3).atZone(ZoneId.systemDefault()).toInstant()));
    credentialEntity.setCreationDateTime(Date.from(Instant.now()));
    credentialEntityDaoManager.save(credentialEntity);

    final AdminUserEntity adminUserEntity = new AdminUserEntity();
    adminUserEntity.setUser(userEntity);
    adminUserEntity.setFirstName(adminUserRequest.getFirstName());
    adminUserEntity.setLastName(adminUserRequest.getLastName());
    adminUserEntity.setCreationDateTime(Date.from(Instant.now()));
    adminUserEntity.setCreatedUserId(sessionUserIdentifier);
    adminUserEntityDaoManager.save(adminUserEntity);

    return createAdminResponse(adminUserEntity);
  }

  @Override
  public AdminUser get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity = adminUserEntityDaoManager.findByIdentifier(identifier);
    return createAdminResponse(adminUserEntity);
  }

  @Override
  public PageResponse<AdminUser> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final org.springframework.data.domain.Page<AdminUserEntity> adminUserEntityPage =
        adminUserEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        adminUserEntityPage.stream().map(this::createAdminResponse).toList(),
        page,
        size,
        adminUserEntityPage.getTotalElements(),
        adminUserEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public AdminUser update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      AdminUserRequest adminUserRequest)
      throws PedistackException {
    final AdminUserEntity adminUserEntity = adminUserEntityDaoManager.findByIdentifier(identifier);
    if (adminUserRequest.getFirstName() != null) {
      adminUserEntity.setFirstName(adminUserRequest.getFirstName());
    }
    if (adminUserRequest.getLastName() != null) {
      adminUserEntity.setLastName(adminUserRequest.getLastName());
    }
    if (adminUserRequest.getProfile() != null) {
      final ProfileEntity profileEntity =
          profileEntityDaoManager.findByName(adminUserRequest.getProfile());
      final UserEntity userEntity = adminUserEntity.getUser();
      userEntity.setProfile(profileEntity);
      userEntityDaoManager.save(userEntity);
    }
    adminUserEntityDaoManager.save(adminUserEntity);
    return createAdminResponse(adminUserEntity);
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    final AdminUserEntity adminUserEntity = adminUserEntityDaoManager.findByIdentifier(identifier);
    adminUserEntityDaoManager.delete(adminUserEntity);
  }

  private AdminUser createAdminResponse(final AdminUserEntity adminUserEntity) {
    final AdminUser admin = new AdminUser();
    admin.setId(adminUserEntity.getId());
    admin.setEmailAddress(adminUserEntity.getUser().getEmailAddress());
    admin.setProfile(adminUserEntity.getUser().getProfile().getName());
    admin.setUsername(adminUserEntity.getUser().getUsername());
    admin.setLastName(adminUserEntity.getLastName());
    admin.setFirstName(adminUserEntity.getFirstName());
    admin.setStatus(adminUserEntity.getUser().getStatus());
    admin.setType(ProfileType.valueOf(adminUserEntity.getUser().getProfile().getType()));
    return admin;
  }
}
