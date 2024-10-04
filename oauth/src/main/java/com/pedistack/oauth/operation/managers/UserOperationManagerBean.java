package com.pedistack.oauth.operation.managers;

import com.pedistack.common.authorization.CredentialStatus;
import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.authorization.UserStatus;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.identity.IdentityStatus;
import com.pedistack.common.managers.GlobalConfigurationManager;
import com.pedistack.db.oauth.*;
import com.pedistack.events.identity.IdentityActivationEvent;
import com.pedistack.events.oauth.EmailActivationOtpNotificationEvent;
import com.pedistack.events.oauth.MsisdnActivationOtpNotificationEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserOperationManagerBean implements UserOperationManager {

  private final UserEntityDaoManager userEntityDaoManager;
  private final ProfileEntityDaoManager profileEntityDaoManager;
  private final GlobalConfigurationManager globalConfigurationManager;
  private final CredentialEntityDaoManager credentialEntityDaoManager;
  private final ApplicationEventPublisher applicationEventPublisher;

  public UserOperationManagerBean(
      UserEntityDaoManager userEntityDaoManager,
      ProfileEntityDaoManager profileEntityDaoManager,
      GlobalConfigurationManager globalConfigurationManager,
      CredentialEntityDaoManager credentialEntityDaoManager,
      ApplicationEventPublisher applicationEventPublisher) {
    this.userEntityDaoManager = userEntityDaoManager;
    this.credentialEntityDaoManager = credentialEntityDaoManager;
    this.profileEntityDaoManager = profileEntityDaoManager;
    this.globalConfigurationManager = globalConfigurationManager;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public String createUser(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String msisdn,
      String emailAddress,
      String username,
      String password,
      String defaultProfileName)
      throws PedistackException {
    final ProfileEntity defaultProfileEntity =
        profileEntityDaoManager.findByName(defaultProfileName);
    if (emailAddress != null) {
      userEntityDaoManager.checkExistingEmailAddress(emailAddress);
    }
    if (msisdn != null) {
      userEntityDaoManager.checkExistingMobileNumber(msisdn);
    }
    if (username != null) {
      userEntityDaoManager.checkExistingUsername(username);
    }
    final UserEntity userEntity = new UserEntity();
    userEntity.setProfile(defaultProfileEntity);
    userEntity.setClientId(RandomStringUtils.randomAlphanumeric(32));
    userEntity.setEmailAddress(emailAddress);
    userEntity.setStatus(UserStatus.REGISTERED.name());
    userEntity.setLoginAttempts(0);
    userEntity.setMobileNumber(msisdn);
    userEntity.setUsername(username);
    userEntity.setTwoFactorAuthenticationStatus(Boolean.FALSE);
    userEntity.setCreationDateTime(Date.from(Instant.now()));
    userEntity.setCreatedUserId(sessionUserIdentifier);
    userEntity.setEmailVerificationStatus(Boolean.FALSE);
    userEntity.setMsisdnVerificationStatus(Boolean.FALSE);
    userEntityDaoManager.save(userEntity);

    final CredentialEntity passwordCredentialEntity = new CredentialEntity();
    passwordCredentialEntity.setCredential(password);
    passwordCredentialEntity.setType(CredentialType.PASSWORD.name());
    passwordCredentialEntity.setUser(userEntity);
    passwordCredentialEntity.setStatus(CredentialStatus.ACTIVE.name());
    passwordCredentialEntity.setExpiryDate(
        Date.from(
            Instant.now()
                .plus(globalConfigurationManager.credentialExpiryMonths(), ChronoUnit.MONTHS)));
    passwordCredentialEntity.setCreationDateTime(Date.from(Instant.now()));
    passwordCredentialEntity.setCredentialReference(RandomStringUtils.randomAlphanumeric(32));
    credentialEntityDaoManager.save(passwordCredentialEntity);

    if (emailAddress != null) {
      final CredentialEntity emailActivationTokenCredentialEntity = new CredentialEntity();
      emailActivationTokenCredentialEntity.setCredential(
          RandomStringUtils.randomNumeric(globalConfigurationManager.emailActivationTokenLength()));
      emailActivationTokenCredentialEntity.setCredentialReference(
          RandomStringUtils.randomAlphanumeric(32));
      emailActivationTokenCredentialEntity.setUser(userEntity);
      emailActivationTokenCredentialEntity.setStatus(CredentialStatus.ACTIVE.name());
      emailActivationTokenCredentialEntity.setType(CredentialType.EMAIL_ACTIVATION_OTP.name());
      emailActivationTokenCredentialEntity.setExpiryDate(
          Date.from(Instant.now().plusSeconds(3600)));
      emailActivationTokenCredentialEntity.setCreationDateTime(Date.from(Instant.now()));
      credentialEntityDaoManager.save(emailActivationTokenCredentialEntity);
      applicationEventPublisher.publishEvent(
          new EmailActivationOtpNotificationEvent(
              this,
              tenant,
              sessionUserIdentifier,
              sessionReference,
              emailAddress,
              emailActivationTokenCredentialEntity.getCredential()));
    }
    if (msisdn != null) {
      final CredentialEntity msisdnActivationTokenCredentialEntity = new CredentialEntity();
      msisdnActivationTokenCredentialEntity.setCredential(
          RandomStringUtils.randomNumeric(
              globalConfigurationManager.msisdnActivationTokenLength()));
      msisdnActivationTokenCredentialEntity.setCredentialReference(
          RandomStringUtils.randomAlphanumeric(32));
      msisdnActivationTokenCredentialEntity.setUser(userEntity);
      msisdnActivationTokenCredentialEntity.setStatus(CredentialStatus.ACTIVE.name());
      msisdnActivationTokenCredentialEntity.setType(CredentialType.MSISDN_ACTIVATION_OTP.name());
      msisdnActivationTokenCredentialEntity.setExpiryDate(
          Date.from(Instant.now().plusSeconds(3600)));
      msisdnActivationTokenCredentialEntity.setCreationDateTime(Date.from(Instant.now()));
      credentialEntityDaoManager.save(msisdnActivationTokenCredentialEntity);
      applicationEventPublisher.publishEvent(
          new MsisdnActivationOtpNotificationEvent(
              this,
              tenant,
              sessionUserIdentifier,
              sessionReference,
              msisdn,
              msisdnActivationTokenCredentialEntity.getCredential()));
    }
    return userEntity.getId();
  }

  @Override
  @Transactional
  public void msisdnActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String msisdn,
      String activationToken)
      throws PedistackException {
    final UserEntity msisdnUserEntity = userEntityDaoManager.findByMobileNumber(msisdn);
    if (!msisdnUserEntity.getStatus().equals(UserStatus.REGISTERED.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_ALREADY_ACTIVATED_ERROR_DESCRIPTION);
    }
    final CredentialEntity msisdnActivationTokenCredentialEntity =
        credentialEntityDaoManager.findByCredentialAndCredentialType(
            activationToken, CredentialType.MSISDN_ACTIVATION_OTP);
    if (!msisdnActivationTokenCredentialEntity.getStatus().equals(CredentialStatus.ACTIVE.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.ACTIVATION_ERROR);
    }
    if (Date.from(Instant.now()).after(msisdnActivationTokenCredentialEntity.getExpiryDate())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.EXPIRED_ACTIVATION_OTP);
    }
    msisdnUserEntity.setMsisdnVerificationStatus(Boolean.TRUE);
    msisdnUserEntity.setStatus(UserStatus.ACTIVE.name());
    userEntityDaoManager.save(msisdnUserEntity);
    credentialEntityDaoManager.delete(msisdnActivationTokenCredentialEntity);
    applicationEventPublisher.publishEvent(
        new IdentityActivationEvent(
            this,
            tenant,
            sessionUserIdentifier,
            sessionReference,
            msisdnUserEntity.getId(),
            IdentityStatus.ACTIVATED));
  }

  @Override
  @Transactional
  public void emailAddressActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String activationToken)
      throws PedistackException {
    final UserEntity emailAddressUserEntity = userEntityDaoManager.findByEmailAddress(emailAddress);
    if (!emailAddressUserEntity.getStatus().equals(UserStatus.REGISTERED.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_ALREADY_ACTIVATED_ERROR_DESCRIPTION);
    }
    final CredentialEntity emailActivationTokenCredentialEntity =
        credentialEntityDaoManager.findByCredentialAndCredentialType(
            activationToken, CredentialType.EMAIL_ACTIVATION_OTP);
    if (!emailActivationTokenCredentialEntity.getStatus().equals(CredentialStatus.ACTIVE.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.ACTIVATION_ERROR);
    }
    if (Date.from(Instant.now()).after(emailActivationTokenCredentialEntity.getExpiryDate())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.EXPIRED_ACTIVATION_OTP);
    }
    emailAddressUserEntity.setEmailVerificationStatus(Boolean.TRUE);
    emailAddressUserEntity.setStatus(UserStatus.ACTIVE.name());
    userEntityDaoManager.save(emailAddressUserEntity);
    credentialEntityDaoManager.delete(emailActivationTokenCredentialEntity);
    applicationEventPublisher.publishEvent(
        new IdentityActivationEvent(
            this,
            tenant,
            sessionUserIdentifier,
            sessionReference,
            emailAddressUserEntity.getId(),
            IdentityStatus.ACTIVATED));
  }
}
