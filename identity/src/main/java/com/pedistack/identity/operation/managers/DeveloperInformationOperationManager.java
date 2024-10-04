package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.DeveloperInformationEntity;
import com.pedistack.identity.v1_0.common.Developer;

public interface DeveloperInformationOperationManager {

  DeveloperInformationEntity addOrUpdateDeveloperInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String developerInformationIdentifier,
      Developer developer)
      throws PedistackException;

  Developer developerInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException;

  Developer developerInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException;

  Developer developerInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  Developer developerInformationWithMobileNumber(
      String tenant, String sessionUserIdentifier, String sessionReference, String mobileNumber)
      throws PedistackException;
}
