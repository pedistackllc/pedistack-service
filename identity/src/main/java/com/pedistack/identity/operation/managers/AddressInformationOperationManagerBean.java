package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.AddressEntity;
import com.pedistack.db.identity.AddressEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.AddressType;
import com.pedistack.identity.v1_0.common.CommunicationAddress;
import com.pedistack.identity.v1_0.common.PostalAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressInformationOperationManagerBean implements AddressInformationOperationManager {

  private final AddressEntityDaoManager addressEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public AddressInformationOperationManagerBean(
      AddressEntityDaoManager addressEntityDaoManager, UserEntityDaoManager userEntityDaoManager) {
    this.addressEntityDaoManager = addressEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public AddressEntity addOrUpdatePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String addressIdentifier,
      PostalAddress postalAddress)
      throws PedistackException {
    AddressEntity addressEntity;
    if (addressIdentifier != null) {
      addressEntity = addressEntityDaoManager.findByIdentifier(addressIdentifier);
    } else {
      addressEntity = new AddressEntity();
    }
    if (addressEntity.getUser() != null
        && !addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    if (postalAddress != null) {
      addressEntity.setAddressType(AddressType.POSTAL_ADDRESS.name());
      if (postalAddress.getAddressLine() != null) {
        addressEntity.setAddressLine(postalAddress.getAddressLine());
      }
      if (postalAddress.getBuildingNumber() != null) {
        addressEntity.setBuildingNumber(postalAddress.getBuildingNumber());
      }
      if (postalAddress.getCountryCode() != null) {
        addressEntity.setCountryCode(postalAddress.getCountryCode().name());
      }
      if (postalAddress.getProvince() != null) {
        addressEntity.setProvince(postalAddress.getProvince());
      }
      if (postalAddress.getPostCode() != null) {
        addressEntity.setPostCode(postalAddress.getPostCode());
      }
      if (postalAddress.getStreetName() != null) {
        addressEntity.setStreetName(postalAddress.getStreetName());
      }
      if (postalAddress.getTown() != null) {
        addressEntity.setTown(postalAddress.getTown());
      }
      if (userIdentifier != null) {
        addressEntity.setUser(userEntityDaoManager.findByIdentifier(userIdentifier));
      }
      return addressEntityDaoManager.save(addressEntity);
    }
    return addressEntity;
  }

  @Override
  public AddressEntity addOrUpdateCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String addressIdentifier,
      CommunicationAddress communicationAddress)
      throws PedistackException {
    AddressEntity addressEntity;
    if (addressIdentifier != null) {
      addressEntity = addressEntityDaoManager.findByIdentifier(addressIdentifier);
    } else {
      addressEntity = new AddressEntity();
    }
    if (addressEntity.getUser() != null
        && !addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    if (communicationAddress != null) {
      addressEntity.setAddressType(AddressType.COMMUNICATION_ADDRESS.name());
      if (communicationAddress.getEmailAddress() != null) {
        addressEntity.setEmailAddress(communicationAddress.getEmailAddress());
      }
      if (communicationAddress.getUrlAddress() != null) {
        addressEntity.setUrlAddress(communicationAddress.getUrlAddress());
      }
      if (communicationAddress.getFaxNumber() != null) {
        addressEntity.setFaxNumber(communicationAddress.getFaxNumber());
      }
      if (communicationAddress.getMobileNumber() != null) {
        addressEntity.setMobileNumber(communicationAddress.getMobileNumber());
      }
      if (communicationAddress.getTelexNumber() != null) {
        addressEntity.setTelexNumber(communicationAddress.getTelexNumber());
      }
      if (userIdentifier != null) {
        addressEntity.setUser(userEntityDaoManager.findByIdentifier(userIdentifier));
      }
      return addressEntityDaoManager.save(addressEntity);
    }
    return addressEntity;
  }

  @Override
  public AddressEntity findAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String addressIdentifier)
      throws PedistackException {
    final AddressEntity addressEntity = addressEntityDaoManager.findByIdentifier(addressIdentifier);
    if (addressEntity.getUser() != null
        && !addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return addressEntity;
  }

  @Override
  public void deleteAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String addressIdentifier)
      throws PedistackException {
    final AddressEntity addressEntity = addressEntityDaoManager.findByIdentifier(addressIdentifier);
    if (addressEntity.getUser() != null
        && !addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    addressEntityDaoManager.delete(addressEntity);
  }
}
