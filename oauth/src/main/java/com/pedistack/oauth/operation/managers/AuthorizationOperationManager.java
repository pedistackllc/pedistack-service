package com.pedistack.oauth.operation.managers;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import org.springframework.http.HttpHeaders;

public interface AuthorizationOperationManager {

  void validateAuthorizationPermissions(
      HttpHeaders httpHeaders, AuthorizationPermissions... authorizationPermissions)
      throws PedistackException;

  String sessionUserIdentifier(HttpHeaders httpHeaders) throws PedistackException;

  String sessionReference(HttpHeaders httpHeaders) throws PedistackException;
}
