package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.identity.BusinessEntity;
import com.pedistack.identity.v1_0.common.Business;

public interface BusinessInformationOperationManager {

  BusinessEntity addOrUpdateBusinessInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String businessIdentifier,
      Business business)
      throws PedistackException;

  PageResponse<Business> businessInformationWithUsername(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String username,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Business> businessInformationWithMobileNumber(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Business> businessInformationWithEmailAddress(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      int page,
      int size)
      throws PedistackException;

  PageResponse<Business> businessInformationWithClientId(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String clientId,
      int page,
      int size)
      throws PedistackException;
}
