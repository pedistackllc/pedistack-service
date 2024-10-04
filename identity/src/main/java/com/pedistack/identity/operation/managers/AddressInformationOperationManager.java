package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.AddressEntity;
import com.pedistack.identity.v1_0.common.CommunicationAddress;
import com.pedistack.identity.v1_0.common.PostalAddress;

public interface AddressInformationOperationManager {

  AddressEntity addOrUpdatePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String addressIdentifier,
      PostalAddress postalAddress)
      throws PedistackException;

  AddressEntity addOrUpdateCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String addressIdentifier,
      CommunicationAddress communicationAddress)
      throws PedistackException;

  AddressEntity findAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String addressIdentifier)
      throws PedistackException;

  void deleteAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String addressIdentifier)
      throws PedistackException;
}
