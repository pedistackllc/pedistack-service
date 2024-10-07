package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.identity.BusinessVerificationStatus;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.identity.AddressEntity;
import com.pedistack.db.identity.BusinessEntity;
import com.pedistack.db.identity.BusinessEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.*;
import com.pedistack.v1_0.common.CountryCode;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class BusinessInformationOperationManagerBean
    implements BusinessInformationOperationManager {

  private final BusinessEntityDaoManager businessEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final AddressInformationOperationManager addressInformationOperationManager;

  public BusinessInformationOperationManagerBean(
      BusinessEntityDaoManager businessEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager,
      AddressInformationOperationManager addressInformationOperationManager) {
    this.businessEntityDaoManager = businessEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.addressInformationOperationManager = addressInformationOperationManager;
  }

  @Override
  public BusinessEntity addOrUpdateBusinessInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String businessIdentifier,
      Business business)
      throws PedistackException {
    businessEntityDaoManager.checkExistingBusinessInformation(
        business.getName(), business.getTradingName(), business.getRegistrationNumber());
    final UserEntity persistedUserEntity = userEntityDaoManager.findByIdentifier(userIdentifier);
    BusinessEntity businessEntity;
    if (businessIdentifier != null) {
      businessEntity = businessEntityDaoManager.findByIdentifier(businessIdentifier);
    } else {
      businessEntity = new BusinessEntity();
    }
    BeanUtils.copyProperties(business, businessEntity);
    businessEntity.setVerificationStatus(BusinessVerificationStatus.REGISTERED.name());
    businessEntity.setBusinessType(
        Optional.ofNullable(business.getBusinessType()).map(BusinessType::name).orElse(null));
    businessEntity.setIndustry(
        Optional.ofNullable(business.getIndustry()).map(Industry::name).orElse(null));
    businessEntity.setRegistrationType(
        Optional.ofNullable(business.getRegistrationType())
            .map(RegistrationType::name)
            .orElse(null));
    businessEntity.setResidentCountryCode(
        Optional.ofNullable(business.getResidentCountryCode()).map(CountryCode::name).orElse(null));
    if (business.getPostalAddress() != null) {
      final AddressEntity postalAddressEntity =
          addressInformationOperationManager.addOrUpdatePostalAddressInformation(
              tenant,
              sessionUserIdentifier,
              sessionReference,
              userIdentifier,
              null,
              business.getPostalAddress());
      businessEntity.setPostalAddress(postalAddressEntity);
    }
    if (business.getCommunicationAddress() != null) {
      final AddressEntity communicationAddressEntity =
          addressInformationOperationManager.addOrUpdateCommunicationAddressInformation(
              tenant,
              sessionUserIdentifier,
              sessionReference,
              userIdentifier,
              null,
              business.getCommunicationAddress());
      businessEntity.setCommunicationAddress(communicationAddressEntity);
    }
    businessEntity.setUser(persistedUserEntity);
    return businessEntityDaoManager.save(businessEntity);
  }

  @Override
  public PageResponse<Business> businessInformationWithUsername(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String username,
      int page,
      int size) {
    final Page<BusinessEntity> businessEntityPage =
        businessEntityDaoManager.findByUsername(username, PageRequest.of(page, size));
    return PageResponse.create(
        businessEntityPage.map(this::createBusinessResponse).stream().toList(),
        page,
        size,
        businessEntityPage.getTotalElements(),
        businessEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Business> businessInformationWithMobileNumber(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      int page,
      int size) {
    final Page<BusinessEntity> businessEntityPage =
        businessEntityDaoManager.findByMobileNumber(mobileNumber, PageRequest.of(page, size));
    return PageResponse.create(
        businessEntityPage.map(this::createBusinessResponse).stream().toList(),
        page,
        size,
        businessEntityPage.getTotalElements(),
        businessEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Business> businessInformationWithEmailAddress(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      int page,
      int size) {
    final Page<BusinessEntity> businessEntityPage =
        businessEntityDaoManager.findByEmailAddress(emailAddress, PageRequest.of(page, size));
    return PageResponse.create(
        businessEntityPage.map(this::createBusinessResponse).stream().toList(),
        page,
        size,
        businessEntityPage.getTotalElements(),
        businessEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Business> businessInformationWithClientId(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String clientId,
      int page,
      int size) {
    final Page<BusinessEntity> businessEntityPage =
        businessEntityDaoManager.findByClientId(clientId, PageRequest.of(page, size));
    return PageResponse.create(
        businessEntityPage.map(this::createBusinessResponse).stream().toList(),
        page,
        size,
        businessEntityPage.getTotalElements(),
        businessEntityPage.getTotalPages());
  }

  private Business createBusinessResponse(BusinessEntity businessEntity) {
    final Business business = new Business();
    BeanUtils.copyProperties(businessEntity, business);
    business.setBusinessType(BusinessType.valueOf(businessEntity.getBusinessType()));
    business.setResidentCountryCode(CountryCode.valueOf(businessEntity.getResidentCountryCode()));
    business.setRegistrationType(RegistrationType.valueOf(businessEntity.getRegistrationType()));
    business.setIndustry(Industry.valueOf(businessEntity.getIndustry()));
    if (businessEntity.getPostalAddress() != null) {
      business.setPostalAddress(createPostalAddress(businessEntity.getPostalAddress()));
    }
    if (businessEntity.getCommunicationAddress() != null) {
      business.setCommunicationAddress(
          createCommunicationAddress(businessEntity.getCommunicationAddress()));
    }
    return business;
  }

  private PostalAddress createPostalAddress(AddressEntity addressEntity) {
    final PostalAddress postalAddress = new PostalAddress();
    BeanUtils.copyProperties(addressEntity, postalAddress);
    postalAddress.setCountryCode(CountryCode.valueOf(addressEntity.getCountryCode()));
    return postalAddress;
  }

  private CommunicationAddress createCommunicationAddress(AddressEntity addressEntity) {
    final CommunicationAddress communicationAddress = new CommunicationAddress();
    BeanUtils.copyProperties(addressEntity, communicationAddress);
    return communicationAddress;
  }
}
