package com.pedistack.oauth.operation.managers;

import com.pedistack.common.exception.PedistackException;

public interface UserOperationManager {

  String createUser(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String msisdn,
      String emailAddress,
      String username,
      String password,
      String defaultProfileName)
      throws PedistackException;

  void msisdnActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String msisdn,
      String activationToken)
      throws PedistackException;

  void emailAddressActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String activationToken)
      throws PedistackException;
}
