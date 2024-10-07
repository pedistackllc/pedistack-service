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

  String emailActivationOtp(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  String msisdnActivationOtp(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException;

  void resendEmailAddressActivationOtp(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  void resendMsisdnActivationOtp(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException;
}
