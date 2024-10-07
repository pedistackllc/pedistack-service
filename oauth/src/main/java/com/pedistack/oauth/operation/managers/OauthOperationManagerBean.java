package com.pedistack.oauth.operation.managers;

import com.pedistack.common.authorization.CredentialStatus;
import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.authorization.UserStatus;
import com.pedistack.common.exception.PedistackErrorDescription;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.GlobalConfigurationManager;
import com.pedistack.db.oauth.CredentialEntity;
import com.pedistack.db.oauth.CredentialEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.oauth.v1_0.OauthRequest;
import com.pedistack.oauth.v1_0.OauthResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class OauthOperationManagerBean implements OauthOperationManager {

  private final UserEntityDaoManager userEntityDaoManager;
  private final CredentialEntityDaoManager credentialEntityDaoManager;
  private final GlobalConfigurationManager globalConfigurationManager;

  private final JwtBuilder defaultJwtBuilder = Jwts.builder();

  public OauthOperationManagerBean(
      UserEntityDaoManager userEntityDaoManager,
      CredentialEntityDaoManager credentialEntityDaoManager,
      GlobalConfigurationManager globalConfigurationManager) {
    this.userEntityDaoManager = userEntityDaoManager;
    this.credentialEntityDaoManager = credentialEntityDaoManager;
    this.globalConfigurationManager = globalConfigurationManager;
  }

  @Override
  public OauthResponse authorize(OauthRequest oauthRequest) throws PedistackException {
    UserEntity userEntity;
    if (oauthRequest.getGrantType() == null) {
      throw PedistackException.createBadRequestException(
          PedistackErrorDescriptions.GRANT_NOT_FOUND_ERROR_DESCRIPTION);
    }
    switch (oauthRequest.getGrantType()) {
      case "authorization_code" -> {
        if (oauthRequest.getClientId() == null && oauthRequest.getClientSecret() == null) {
          throw PedistackException.createBadRequestException(
              PedistackErrorDescription.createDescription(
                  "Client id and secret is required",
                  "You require client id and secret for this operation"));
        }
        userEntity = userEntityDaoManager.findByClientId(oauthRequest.getClientId());
        if (!userEntity.getStatus().equals(UserStatus.ACTIVE.name())) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.USER_NOT_ACTIVE);
        }
        final Optional<CredentialEntity> secretKeyCredentialEntityOptional =
            credentialEntityDaoManager.findByUserIdentifierAndCredentialTypeReturnOptional(
                userEntity.getId(), CredentialType.CLIENT_SECRET);
        if (secretKeyCredentialEntityOptional.isEmpty()) {
          userEntity.setLoginAttempts(userEntity.getLoginAttempts() + 1);
          if (userEntity.getLoginAttempts() >= 5) {
            userEntity.setStatus(UserStatus.BLOCKED.name());
            userEntityDaoManager.save(userEntity);
            throw PedistackException.createUnauthorizedException(
                PedistackErrorDescriptions.AUTHORIZATION_ERROR);
          }
        }

        if (secretKeyCredentialEntityOptional.isPresent()
            && secretKeyCredentialEntityOptional.get().getExpiryDate() == null) {
          secretKeyCredentialEntityOptional
              .get()
              .setExpiryDate(
                  Date.from(
                      LocalDateTime.now()
                          .plusMonths(3)
                          .atZone(ZoneId.systemDefault())
                          .toInstant()));
          credentialEntityDaoManager.save(secretKeyCredentialEntityOptional.get());
        }

        if (secretKeyCredentialEntityOptional.isPresent()
            && !secretKeyCredentialEntityOptional
                .get()
                .getStatus()
                .equals(CredentialStatus.ACTIVE.name())) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.CREDENTIAL_ERROR_DESCRIPTION);
        }
        if (secretKeyCredentialEntityOptional.isPresent()
            && secretKeyCredentialEntityOptional.get().getExpiryDate() != null
            && Instant.now()
                .isAfter(secretKeyCredentialEntityOptional.get().getExpiryDate().toInstant())) {
          final CredentialEntity updatedCredentialEntity = secretKeyCredentialEntityOptional.get();
          updatedCredentialEntity.setExpiryDate(
              Date.from(
                  LocalDateTime.now().plusMonths(3).atZone(ZoneId.systemDefault()).toInstant()));
          credentialEntityDaoManager.save(updatedCredentialEntity);
        }
      }
      case "pin_code", "password" -> {
        if (oauthRequest.getUsername() == null
            && oauthRequest.getMsisdn() == null
            && oauthRequest.getEmailAddress() == null) {
          throw PedistackException.createBadRequestException(
              PedistackErrorDescription.createDescription(
                  "Email address, username or mobile number is required",
                  "You require anyone of email address, username or mobile number for this operation"));
        }
        if (oauthRequest.getUsername() != null) {
          userEntity = userEntityDaoManager.findByUsername(oauthRequest.getUsername());
        } else if (oauthRequest.getEmailAddress() != null) {
          userEntity = userEntityDaoManager.findByEmailAddress(oauthRequest.getEmailAddress());
        } else {
          userEntity = userEntityDaoManager.findByMobileNumber(oauthRequest.getMsisdn());
        }
        if (userEntity == null) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.USER_NOT_FOUND);
        }
        if (!userEntity.getStatus().equals(UserStatus.ACTIVE.name())) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.USER_NOT_ACTIVE);
        }
        Optional<CredentialEntity> credentialEntityOptional = Optional.empty();
        if (oauthRequest.getGrantType().equals("password")) {
          credentialEntityOptional =
              credentialEntityDaoManager.findByUserIdentifierAndCredentialTypeReturnOptional(
                  userEntity.getId(), CredentialType.PASSWORD);
        } else if (oauthRequest.getGrantType().equals("pin_code")) {
          credentialEntityOptional =
              credentialEntityDaoManager.findByUserIdentifierAndCredentialTypeReturnOptional(
                  userEntity.getId(), CredentialType.PIN_CODE);
        }

        if (credentialEntityOptional.isEmpty()) {
          userEntity.setLoginAttempts(userEntity.getLoginAttempts() + 1);
          if (userEntity.getLoginAttempts() >= 5) {
            userEntity.setStatus(UserStatus.BLOCKED.name());
            userEntityDaoManager.save(userEntity);
            throw PedistackException.createUnauthorizedException(
                PedistackErrorDescriptions.AUTHORIZATION_ERROR);
          }
        }
        if (credentialEntityOptional.isPresent()
            && !credentialEntityOptional.get().getStatus().equals(CredentialStatus.ACTIVE.name())) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.CREDENTIAL_ERROR_DESCRIPTION);
        }
        if (credentialEntityOptional.isPresent()
            && credentialEntityOptional.get().getExpiryDate() == null) {
          credentialEntityOptional
              .get()
              .setExpiryDate(
                  Date.from(
                      LocalDateTime.now()
                          .plusMonths(3)
                          .atZone(ZoneId.systemDefault())
                          .toInstant()));
          credentialEntityDaoManager.save(credentialEntityOptional.get());
        }
        if (credentialEntityOptional.isPresent()
            && credentialEntityOptional.get().getExpiryDate() != null
            && Instant.now().isAfter(credentialEntityOptional.get().getExpiryDate().toInstant())) {
          throw PedistackException.accessExpiredException(
              PedistackErrorDescriptions.EXPIRED_CREDENTIAL);
        }
      }
      case "refresh_token" -> {
        if (oauthRequest.getRefreshToken() == null) {
          throw PedistackException.createBadRequestException(
              PedistackErrorDescription.createDescription(
                  "Refresh token is required", "You require refresh token for this operation"));
        }
        final CredentialEntity refreshTokenCredentialEntity =
            credentialEntityDaoManager.findByCredentialAndCredentialType(
                oauthRequest.getRefreshToken(), CredentialType.REFRESH_TOKEN);
        userEntity = refreshTokenCredentialEntity.getUser();
        if (!refreshTokenCredentialEntity.getStatus().equals(CredentialStatus.ACTIVE.name())) {
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.CREDENTIAL_ERROR_DESCRIPTION);
        }
        credentialEntityDaoManager.delete(refreshTokenCredentialEntity);
      }
      default ->
          throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.AUTHORIZATION_ERROR);
    }
    if (userEntity == null) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_NOT_FOUND);
    }
    if (!userEntity.getStatus().equals(UserStatus.ACTIVE.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_NOT_ACTIVE);
    }
    final String refreshToken = RandomStringUtils.randomAlphanumeric(16);
    final CredentialEntity refreshTokenCredentialEntity = new CredentialEntity();
    refreshTokenCredentialEntity.setCredential(refreshToken);
    refreshTokenCredentialEntity.setStatus(CredentialStatus.ACTIVE.name());
    refreshTokenCredentialEntity.setCredentialReference(RandomStringUtils.randomAlphanumeric(32));
    refreshTokenCredentialEntity.setType(CredentialType.REFRESH_TOKEN.name());
    refreshTokenCredentialEntity.setUser(userEntity);
    refreshTokenCredentialEntity.setCreatedUserId(userEntity.getId());
    credentialEntityDaoManager.save(refreshTokenCredentialEntity);
    return createOauthToken(refreshToken, userEntity);
  }

  private OauthResponse createOauthToken(String refreshToken, UserEntity userEntity) {
    defaultJwtBuilder.signWith(
        SignatureAlgorithm.HS512, globalConfigurationManager.jwtSigningKey());
    defaultJwtBuilder.claim("client_id", userEntity.getClientId());
    defaultJwtBuilder.claim("scope_id", userEntity.getProfile().getId());
    final Date expiryDateTime =
        Date.from(
            LocalDateTime.now()
                .plusSeconds(globalConfigurationManager.jwtExpirationSeconds())
                .toInstant(ZoneOffset.UTC));
    defaultJwtBuilder.setExpiration(expiryDateTime);
    final OauthResponse oauthResponse = new OauthResponse();
    oauthResponse.setAccessToken(defaultJwtBuilder.compact());
    oauthResponse.setRefreshToken(refreshToken);
    oauthResponse.setTokenType("Bearer");
    oauthResponse.setScope(userEntity.getProfile().getType().toLowerCase());
    oauthResponse.setExpiresIn(expiryDateTime.getTime());
    return oauthResponse;
  }
}
