package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.PersonEntity;
import com.pedistack.identity.v1_0.common.Person;

public interface PersonalInformationOperationManager {

  PersonEntity addOrUpdatePersonalInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      Person person)
      throws PedistackException;

  Person personalInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, String userIdentifier)
      throws PedistackException;

  Person personalInformationWithMsisdn(
      String tenant, String sessionUserIdentifier, String sessionReference, String userMsisdn)
      throws PedistackException;

  Person personalInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String userEmailAddress)
      throws PedistackException;

  Person personalInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException;

  Person personalInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException;
}
