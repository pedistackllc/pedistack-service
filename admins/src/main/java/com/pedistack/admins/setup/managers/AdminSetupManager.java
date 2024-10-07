package com.pedistack.admins.setup.managers;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.authorization.CredentialStatus;
import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.authorization.UserStatus;
import com.pedistack.db.admins.AdminUserEntity;
import com.pedistack.db.admins.AdminUserEntityDaoManager;
import com.pedistack.db.oauth.*;
import com.pedistack.oauth.v1_0.common.ProfileType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AdminSetupManager implements ApplicationListener<ContextRefreshedEvent> {

  private final ProfileEntityDaoManager profileEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final CredentialEntityDaoManager credentialEntityDaoManager;
  private final AdminUserEntityDaoManager adminUserEntityDaoManager;

  public AdminSetupManager(
      ProfileEntityDaoManager profileEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager,
      CredentialEntityDaoManager credentialEntityDaoManager,
      AdminUserEntityDaoManager adminUserEntityDaoManager) {
    this.profileEntityDaoManager = profileEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.credentialEntityDaoManager = credentialEntityDaoManager;
    this.adminUserEntityDaoManager = adminUserEntityDaoManager;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      installDefaultAdministratorProfiles();
      installDefaultBusinessMerchantProfiles();
      installDefaultPartnerProfiles();
      installDefaultAgentProfiles();
      installDefaultSuperAdministratorUser();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  void installDefaultSuperAdministratorUser() throws Exception {
    if (userEntityDaoManager.findByUsernameReturnOptional("superadmin").isEmpty()) {
      final ProfileEntity adminProfileEntity =
          profileEntityDaoManager.findByName("Default Super Administrator Profile");
      final UserEntity userEntity = new UserEntity();
      userEntity.setUsername("superadmin");
      userEntity.setProfile(adminProfileEntity);
      userEntity.setLoginAttempts(0);
      userEntity.setClientId(RandomStringUtils.randomAlphanumeric(32));
      userEntity.setEmailAddress("superadmin@pedistack.com");
      userEntity.setStatus(UserStatus.ACTIVE.name());
      final UserEntity persistedUserEntity = userEntityDaoManager.save(userEntity);
      final CredentialEntity credentialEntity = new CredentialEntity();
      credentialEntity.setCredential("ABc123456!");
      credentialEntity.setUser(persistedUserEntity);
      credentialEntity.setType(CredentialType.PASSWORD.name());
      credentialEntity.setStatus(CredentialStatus.ACTIVE.name());
      credentialEntityDaoManager.save(credentialEntity);
      final AdminUserEntity adminUserEntity = new AdminUserEntity();
      adminUserEntity.setFirstName("Super");
      adminUserEntity.setFirstName("Administrator");
      adminUserEntity.setUser(persistedUserEntity);
      adminUserEntityDaoManager.save(adminUserEntity);
    }
  }

  void installDefaultPartnerProfiles() throws Exception {
    if (profileEntityDaoManager.findByNameReturnOptional("Default Partner Profile").isEmpty()) {
      final ProfileEntity profileEntity = new ProfileEntity();
      profileEntity.setName("Default Partner Profile");
      profileEntity.setType(ProfileType.PARTNER.name());
      profileEntity.setDescription("The default partner profile");
      profileEntity.setPermissions(
          Arrays.stream(AuthorizationPermissions.values())
              .map(AuthorizationPermissions::name)
              .toList());
      profileEntity.setAdditionalInformation(new HashMap<>());
      profileEntityDaoManager.save(profileEntity);
    }
  }

  void installDefaultBusinessMerchantProfiles() throws Exception {
    final Optional<ProfileEntity> profileEntityOptional =
        profileEntityDaoManager.findByNameReturnOptional("Default Business Merchant Profile");
    if (profileEntityOptional.isEmpty()) {
      final ProfileEntity profileEntity = new ProfileEntity();
      profileEntity.setName("Default Business Merchant Profile");
      profileEntity.setType(ProfileType.BUSINESS.name());
      profileEntity.setDescription("The default business owner profile");
      profileEntity.setPermissions(
          Arrays.stream(AuthorizationPermissions.values())
              .map(AuthorizationPermissions::name)
              .toList());
      profileEntity.setAdditionalInformation(new HashMap<>());
      profileEntityDaoManager.save(profileEntity);
    } else {
      final ProfileEntity businessProfileEntity = profileEntityOptional.get();
      businessProfileEntity
          .getPermissions()
          .addAll(
              Arrays.stream(AuthorizationPermissions.values())
                  .map(AuthorizationPermissions::name)
                  .filter(name -> !businessProfileEntity.getPermissions().contains(name))
                  .toList());
      profileEntityDaoManager.save(businessProfileEntity);
    }
  }

  void installDefaultAgentProfiles() throws Exception {
    final Optional<ProfileEntity> profileEntityOptional =
        profileEntityDaoManager.findByNameReturnOptional("Default Agent Profile");
    if (profileEntityOptional.isEmpty()) {
      final ProfileEntity profileEntity = new ProfileEntity();
      profileEntity.setName("Default Agent Profile");
      profileEntity.setType(ProfileType.AGENT.name());
      profileEntity.setDescription("The default agent profile");
      profileEntity.setPermissions(
          Arrays.stream(AuthorizationPermissions.values())
              .map(AuthorizationPermissions::name)
              .toList());
      profileEntity.setAdditionalInformation(new HashMap<>());
      profileEntityDaoManager.save(profileEntity);
    } else {
      final ProfileEntity agentProfileEntity = profileEntityOptional.get();
      agentProfileEntity
          .getPermissions()
          .addAll(
              Arrays.stream(AuthorizationPermissions.values())
                  .map(AuthorizationPermissions::name)
                  .filter(name -> !agentProfileEntity.getPermissions().contains(name))
                  .toList());
      profileEntityDaoManager.save(agentProfileEntity);
    }
  }

  void installDefaultAdministratorProfiles() throws Exception {
    final Optional<ProfileEntity> superAdministratorProfileEntityOptional =
        profileEntityDaoManager.findByNameReturnOptional("Default Super Administrator Profile");
    if (superAdministratorProfileEntityOptional.isEmpty()) {
      final ProfileEntity profileEntity = new ProfileEntity();
      profileEntity.setName("Default Super Administrator Profile");
      profileEntity.setType(ProfileType.SUPER_ADMINISTRATOR.name());
      profileEntity.setDescription(
          "The default super administrator profile for administrator access");
      profileEntity.setPermissions(
          Arrays.stream(AuthorizationPermissions.values())
              .map(AuthorizationPermissions::name)
              .toList());
      profileEntity.setAdditionalInformation(new HashMap<>());
      profileEntityDaoManager.save(profileEntity);
    } else {
      final ProfileEntity superAdministratorProfileEntity =
          superAdministratorProfileEntityOptional.get();
      superAdministratorProfileEntity
          .getPermissions()
          .addAll(
              Arrays.stream(AuthorizationPermissions.values())
                  .map(AuthorizationPermissions::name)
                  .filter(name -> !superAdministratorProfileEntity.getPermissions().contains(name))
                  .toList());
      profileEntityDaoManager.save(superAdministratorProfileEntity);
    }
  }
}
