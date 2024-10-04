package com.pedistack.oauth.operation.managers;

import com.pedistack.common.authorization.*;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.GlobalConfigurationManager;
import com.pedistack.db.oauth.CredentialEntity;
import com.pedistack.db.oauth.CredentialEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import io.jsonwebtoken.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationOperationManagerBean implements AuthorizationOperationManager {

  private final UserEntityDaoManager userEntityDaoManager;
  private final CredentialEntityDaoManager credentialEntityDaoManager;

  private final JwtParser defaultJwtParser = Jwts.parser();

  public AuthorizationOperationManagerBean(
      UserEntityDaoManager userEntityDaoManager,
      CredentialEntityDaoManager credentialEntityDaoManager,
      GlobalConfigurationManager globalConfigurationManager) {
    this.userEntityDaoManager = userEntityDaoManager;
    this.credentialEntityDaoManager = credentialEntityDaoManager;
    this.defaultJwtParser.setSigningKey(globalConfigurationManager.jwtSigningKey());
  }

  @Override
  public void validateAuthorizationPermissions(
      HttpHeaders httpHeaders, AuthorizationPermissions... authorizationPermissions)
      throws PedistackException {
    final UserEntity userEntity = sessionUserInformation(httpHeaders);
    if (!userEntity.getStatus().equals(UserStatus.ACTIVE.name())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_NOT_ACTIVE);
    }
    if (!new HashSet<>(userEntity.getProfile().getPermissions())
        .containsAll(
            Arrays.stream(authorizationPermissions).map(AuthorizationPermissions::name).toList())) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.AUTHORIZATION_ERROR);
    }
  }

  @Override
  public String sessionUserIdentifier(HttpHeaders httpHeaders) throws PedistackException {
    return sessionUserInformation(httpHeaders).getId();
  }

  @Override
  public String sessionReference(HttpHeaders httpHeaders) throws PedistackException {
    return Optional.ofNullable(httpHeaders.getFirst("X-Request-ID"))
        .orElse(RandomStringUtils.randomAlphanumeric(32));
  }

  private UserEntity sessionUserInformation(HttpHeaders httpHeaders) throws PedistackException {
    final String authorizationValue = httpHeaders.getFirst("Authorization");
    if (authorizationValue == null) {
      throw PedistackException.createBadRequestException(
          PedistackErrorDescriptions.AUTHORIZATION_HEADER_NOT_FOUND);
    }
    if (authorizationValue.startsWith("Bearer")) {
      try {
        final Authorization bearerAuthorization =
            Authorization.bearerAuthorization(authorizationValue);
        final Jws<Claims> claimsJws =
            defaultJwtParser.parseClaimsJws(bearerAuthorization.getAuthorizationValue());
        return userEntityDaoManager.findByClientId(
            claimsJws.getBody().get("client_id", String.class));
      } catch (ExpiredJwtException expiredJwtException) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.EXPIRED_ACCESS_TOKEN_ERROR_DESCRIPTION);
      }
    } else if (authorizationValue.startsWith("Basic")) {
      final Authorization basicAuthorization = Authorization.basicAuthorization(authorizationValue);
      final List<String> authorizationParameters =
          basicAuthorization.authorizationParameters(basicAuthorization);
      final UserEntity persistedUserEntity =
          userEntityDaoManager.findByClientId(authorizationParameters.get(0));
      final CredentialEntity secretKeyCredentialEntity =
          credentialEntityDaoManager.findByCredentialAndCredentialType(
              authorizationParameters.get(1), CredentialType.CLIENT_SECRET);
      if (!secretKeyCredentialEntity.getStatus().equals(CredentialStatus.ACTIVE.name())
          || !secretKeyCredentialEntity
              .getUser()
              .getClientId()
              .equals(persistedUserEntity.getClientId())) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.AUTHORIZATION_ERROR);
      }
      return persistedUserEntity;
    }
    throw PedistackException.createInternalErrorException(
        PedistackErrorDescriptions.AUTHORIZATION_TYPE_NOT_SUPPORTED_ERROR_DESCRIPTION);
  }
}
