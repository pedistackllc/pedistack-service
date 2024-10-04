package com.pedistack.common.authorization;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

public final class Authorization implements Serializable {

  private final String authorizationValue;
  private final AuthorizationType authorizationType;

  public Authorization(String authorizationValue, AuthorizationType authorizationType) {
    this.authorizationValue = authorizationValue;
    this.authorizationType = authorizationType;
  }

  public static Authorization bearerAuthorization(String authorizationValue) {
    return new Authorization(
        authorizationValue.replace(AuthorizationType.BEARER.getAuthorizationType(), ""),
        AuthorizationType.BEARER);
  }

  public static Authorization basicAuthorization(String authorizationValue) {
    return new Authorization(
        authorizationValue.replace(AuthorizationType.BASIC.getAuthorizationType(), ""),
        AuthorizationType.BASIC);
  }

  public List<String> authorizationParameters(Authorization authorization)
      throws PedistackException {
    if (authorization.authorizationType == AuthorizationType.BASIC) {
      final Base64.Decoder decoder = Base64.getDecoder();
      final String decodedAuthorizationToken =
          new String(decoder.decode(authorization.getAuthorizationValue().trim()));
      return List.of(decodedAuthorizationToken.split(":"));
    }
    throw PedistackException.createInternalErrorException(
        PedistackErrorDescriptions.AUTHORIZATION_TYPE_NOT_SUPPORTED_ERROR_DESCRIPTION);
  }

  public String getAuthorizationValue() {
    return authorizationValue;
  }

  public AuthorizationType getAuthorizationType() {
    return authorizationType;
  }
}
