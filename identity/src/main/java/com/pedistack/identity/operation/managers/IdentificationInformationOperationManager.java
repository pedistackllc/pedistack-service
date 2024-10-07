package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.identity.IdentificationEntity;
import com.pedistack.identity.v1_0.common.Identification;

public interface IdentificationInformationOperationManager {

  IdentificationEntity addOrUpdateIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String userIdentifier,
      String identificationIdentifier,
      Identification identification)
      throws PedistackException;

  PageResponse<Identification> identificationInformationWithUsername(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String username,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Identification> identificationInformationWithEmailAddress(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String emailAddress,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Identification> identificationInformationWithMobileNumber(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String mobileNumber,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Identification> identificationInformationWithClientId(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String clientId,
      int page,
      int size)
      throws PedistackException;

  IdentificationEntity identificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String identificationIdentifier)
      throws PedistackException;

  void removeIdentification(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String identificationIdentifier)
      throws PedistackException;
}
