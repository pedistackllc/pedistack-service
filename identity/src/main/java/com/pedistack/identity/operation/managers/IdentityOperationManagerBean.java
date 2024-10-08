package com.pedistack.identity.operation.managers;

import com.pedistack.accounts.operation.managers.FinancialAccountOperationManager;
import com.pedistack.common.accounts.AccountType;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.identity.IdentityStatus;
import com.pedistack.common.managers.GlobalConfigurationManager;
import com.pedistack.db.identity.*;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.planet.CountryEntity;
import com.pedistack.db.planet.CountryEntityDaoManager;
import com.pedistack.db.planet.CurrencyEntity;
import com.pedistack.identity.v1_0.*;
import com.pedistack.identity.v1_0.common.*;
import com.pedistack.oauth.operation.managers.UserOperationManager;
import com.pedistack.v1_0.common.CountryCode;
import com.pedistack.v1_0.common.LanguageCode;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class IdentityOperationManagerBean implements IdentityOperationManager {

  private final UserOperationManager userOperationManager;
  private final IdentityEntityDaoManager identityEntityDaoManager;
  private final AddressInformationOperationManager addressInformationOperationManager;
  private final BusinessInformationOperationManager businessInformationOperationManager;
  private final DeveloperInformationOperationManager developerInformationOperationManager;
  private final IdentificationInformationOperationManager identificationInformationOperationManager;
  private final PersonalInformationOperationManager personalInformationOperationManager;
  private final SocialMediaInformationOperationManager socialMediaInformationOperationManager;
  private final GlobalConfigurationManager globalConfigurationManager;
  private final FinancialAccountOperationManager financialAccountOperationManager;
  private final CountryEntityDaoManager countryEntityDaoManager;
  private final NextOfKinOperationManager nextOfKinOperationManager;

  public IdentityOperationManagerBean(
      UserOperationManager userOperationManager,
      CountryEntityDaoManager countryEntityDaoManager,
      FinancialAccountOperationManager financialAccountOperationManager,
      IdentityEntityDaoManager identityEntityDaoManager,
      AddressInformationOperationManager addressInformationOperationManager,
      BusinessInformationOperationManager businessInformationOperationManager,
      DeveloperInformationOperationManager developerInformationOperationManager,
      IdentificationInformationOperationManager identificationInformationOperationManager,
      PersonalInformationOperationManager personalInformationOperationManager,
      GlobalConfigurationManager globalConfigurationManager,
      NextOfKinOperationManager nextOfKinOperationManager,
      SocialMediaInformationOperationManager socialMediaInformationOperationManager) {
    this.userOperationManager = userOperationManager;
    this.nextOfKinOperationManager = nextOfKinOperationManager;
    this.globalConfigurationManager = globalConfigurationManager;
    this.identityEntityDaoManager = identityEntityDaoManager;
    this.addressInformationOperationManager = addressInformationOperationManager;
    this.businessInformationOperationManager = businessInformationOperationManager;
    this.developerInformationOperationManager = developerInformationOperationManager;
    this.identificationInformationOperationManager = identificationInformationOperationManager;
    this.personalInformationOperationManager = personalInformationOperationManager;
    this.socialMediaInformationOperationManager = socialMediaInformationOperationManager;
    this.financialAccountOperationManager = financialAccountOperationManager;
    this.countryEntityDaoManager = countryEntityDaoManager;
  }

  @Override
  @Transactional
  public IdentityResponse customerRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      CustomerRegistrationRequest customerRegistrationRequest)
      throws PedistackException {

    final CountryEntity countryEntity =
        countryEntityDaoManager.findByAlpha2Code(customerRegistrationRequest.getCountryCode());
    final String userIdentifier =
        userOperationManager.createUser(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            customerRegistrationRequest.getMsisdn(),
            customerRegistrationRequest.getEmailAddress(),
            customerRegistrationRequest.getUsername(),
            customerRegistrationRequest.getPassword(),
            globalConfigurationManager.defaultCustomerProfileName());

    final UserEntity userEntity = new UserEntity();
    userEntity.setId(userIdentifier);

    final Person person = new Person();
    BeanUtils.copyProperties(customerRegistrationRequest, person);
    final PersonEntity personEntity =
        personalInformationOperationManager.addOrUpdatePersonalInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, person);

    final IdentityEntity identityEntity = new IdentityEntity();
    identityEntity.setInternalIdentity(RandomStringUtils.randomNumeric(15));
    identityEntity.setUser(userEntity);
    identityEntity.setPerson(personEntity);
    identityEntity.setStatus(IdentityStatus.REGISTERED.name());
    identityEntityDaoManager.save(identityEntity);

    for (CurrencyEntity currencyEntity : countryEntity.getCurrencies()) {
      financialAccountOperationManager.createUserAccounts(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          userIdentifier,
          currencyEntity.getCode(),
          AccountType.MAIN,
          AccountType.LOYALTY);
    }

    final IdentityResponse identityResponse = new IdentityResponse();
    identityResponse.setInternalIdentity(identityEntity.getInternalIdentity());
    identityResponse.setMsisdn(customerRegistrationRequest.getMsisdn());
    identityResponse.setEmailAddress(customerRegistrationRequest.getEmailAddress());
    identityResponse.setUsername(customerRegistrationRequest.getUsername());
    identityResponse.setStatus(com.pedistack.identity.v1_0.common.IdentityStatus.REGISTERED);

    return identityResponse;
  }

  @Override
  @Transactional
  public IdentityResponse businessRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      BusinessRegistrationRequest businessRegistrationRequest)
      throws PedistackException {
    final CountryEntity countryEntity =
        countryEntityDaoManager.findByAlpha2Code(businessRegistrationRequest.getCountryCode());
    final String userIdentifier =
        userOperationManager.createUser(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            businessRegistrationRequest.getMsisdn(),
            businessRegistrationRequest.getEmailAddress(),
            businessRegistrationRequest.getUsername(),
            businessRegistrationRequest.getPassword(),
            globalConfigurationManager.defaultCustomerProfileName());

    final UserEntity userEntity = new UserEntity();
    userEntity.setId(userIdentifier);

    final Person person = new Person();
    BeanUtils.copyProperties(businessRegistrationRequest, person);
    final PersonEntity personEntity =
        personalInformationOperationManager.addOrUpdatePersonalInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, person);

    final Business business = new Business();
    BeanUtils.copyProperties(businessRegistrationRequest, business);
    final BusinessEntity businessEntity =
        businessInformationOperationManager.addOrUpdateBusinessInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, null, business);

    final PostalAddress postalAddress = new PostalAddress();
    BeanUtils.copyProperties(businessRegistrationRequest, postalAddress);
    final AddressEntity postalAddressEntity =
        addressInformationOperationManager.addOrUpdatePostalAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, null, postalAddress);

    for (CurrencyEntity currencyEntity : countryEntity.getCurrencies()) {
      financialAccountOperationManager.createUserAccounts(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          userIdentifier,
          currencyEntity.getCode(),
          AccountType.MAIN,
          AccountType.LOYALTY,
          AccountType.COMMISSION);
    }

    final IdentityEntity identityEntity = new IdentityEntity();
    identityEntity.setInternalIdentity(RandomStringUtils.randomNumeric(15));
    identityEntity.setUser(userEntity);
    identityEntity.setBusiness(businessEntity);
    identityEntity.setPerson(personEntity);
    identityEntity.setPostalAddresses(List.of(postalAddressEntity));
    identityEntity.setStatus(IdentityStatus.REGISTERED.name());
    identityEntityDaoManager.save(identityEntity);

    final IdentityResponse identityResponse = new IdentityResponse();
    identityResponse.setInternalIdentity(identityEntity.getInternalIdentity());
    identityResponse.setMsisdn(businessRegistrationRequest.getMsisdn());
    identityResponse.setEmailAddress(businessRegistrationRequest.getEmailAddress());
    identityResponse.setUsername(businessRegistrationRequest.getUsername());
    identityResponse.setStatus(com.pedistack.identity.v1_0.common.IdentityStatus.REGISTERED);

    return identityResponse;
  }

  @Override
  @Transactional
  public IdentityResponse agentRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      AgentRegistrationRequest agentRegistrationRequest)
      throws PedistackException {

    final CountryEntity countryEntity =
        countryEntityDaoManager.findByAlpha2Code(agentRegistrationRequest.getCountryCode().name());

    final String userIdentifier =
        userOperationManager.createUser(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            agentRegistrationRequest.getMsisdn(),
            agentRegistrationRequest.getEmailAddress(),
            agentRegistrationRequest.getUsername(),
            agentRegistrationRequest.getPassword(),
            globalConfigurationManager.defaultAgentProfileName());

    final UserEntity userEntity = new UserEntity();
    userEntity.setId(userIdentifier);

    final Person person = new Person();
    BeanUtils.copyProperties(agentRegistrationRequest, person);
    final PersonEntity personEntity =
        personalInformationOperationManager.addOrUpdatePersonalInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, person);

    final Business business = new Business();
    BeanUtils.copyProperties(agentRegistrationRequest, business);
    business.setName(agentRegistrationRequest.getBusinessName());
    business.setTradingName(agentRegistrationRequest.getBusinessTradingName());
    business.setResidentCountryCode(agentRegistrationRequest.getResidentCountryCode());

    final BusinessEntity businessEntity =
        businessInformationOperationManager.addOrUpdateBusinessInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, null, business);

    final PostalAddress postalAddress = new PostalAddress();
    BeanUtils.copyProperties(agentRegistrationRequest, postalAddress);
    final AddressEntity postalAddressEntity =
        addressInformationOperationManager.addOrUpdatePostalAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, userIdentifier, null, postalAddress);

    IdentityEntity parentIdentityEntity = null;
    if (agentRegistrationRequest.getParentAgentEmailAddress() != null) {
      parentIdentityEntity =
          identityEntityDaoManager.findByEmailAddress(
              agentRegistrationRequest.getParentAgentEmailAddress());
    } else if (agentRegistrationRequest.getParentAgentMsisdn() != null) {
      parentIdentityEntity =
          identityEntityDaoManager.findByMobileNumber(
              agentRegistrationRequest.getParentAgentMsisdn());
    } else if (agentRegistrationRequest.getParentAgentUsername() != null) {
      parentIdentityEntity =
          identityEntityDaoManager.findByUsername(
              agentRegistrationRequest.getParentAgentUsername());
    }

    for (CurrencyEntity currencyEntity : countryEntity.getCurrencies()) {
      financialAccountOperationManager.createUserAccounts(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          userIdentifier,
          currencyEntity.getCode(),
          AccountType.MAIN,
          AccountType.LOYALTY,
          AccountType.COMMISSION);
    }

    final IdentityEntity identityEntity = new IdentityEntity();
    identityEntity.setParentIdentity(parentIdentityEntity);
    identityEntity.setInternalIdentity(RandomStringUtils.randomNumeric(15));
    identityEntity.setUser(userEntity);
    identityEntity.setPerson(personEntity);
    identityEntity.setBusiness(businessEntity);
    identityEntity.setPostalAddresses(List.of(postalAddressEntity));
    identityEntity.setStatus(IdentityStatus.REGISTERED.name());
    identityEntityDaoManager.save(identityEntity);

    final IdentityResponse identityResponse = new IdentityResponse();
    identityResponse.setInternalIdentity(identityEntity.getInternalIdentity());
    identityResponse.setMsisdn(agentRegistrationRequest.getMsisdn());
    identityResponse.setEmailAddress(agentRegistrationRequest.getEmailAddress());
    identityResponse.setUsername(agentRegistrationRequest.getUsername());
    identityResponse.setStatus(com.pedistack.identity.v1_0.common.IdentityStatus.REGISTERED);

    return identityResponse;
  }

  @Override
  public Identity identityInformation(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException {
    return createIdentityResponse(
        identityEntityDaoManager.findByUserIdentifier(sessionUserIdentifier));
  }

  @Override
  public Person updatePersonalInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Person person)
      throws PedistackException {
    final PersonEntity personEntity =
        personalInformationOperationManager.addOrUpdatePersonalInformation(
            tenant, sessionUserIdentifier, sessionReference, sessionUserIdentifier, person);
    return createPersonResponse(personEntity);
  }

  @Override
  public Business updateBusinessInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Business business)
      throws PedistackException {
    BusinessEntity businessEntity =
        businessInformationOperationManager.addOrUpdateBusinessInformation(
            tenant, sessionUserIdentifier, sessionReference, sessionUserIdentifier, null, business);
    return createBusinessResponse(businessEntity);
  }

  @Override
  @Transactional
  public PostalAddress addPostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      PostalAddress postalAddress)
      throws PedistackException {
    IdentityEntity identityEntity =
        identityEntityInformation(mobileNumber, emailAddress, username, sessionUserIdentifier);
    if (identityEntity.getPostalAddresses().size() > 10) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.POSTAL_ADDRESS_SIZE_LIMIT_ERROR_DESCRIPTION);
    }
    final AddressEntity postalAddressEntity =
        addressInformationOperationManager.addOrUpdatePostalAddressInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            identityEntity.getUser().getId(),
            null,
            postalAddress);
    if (identityEntity.getPostalAddresses() == null) {
      identityEntity.setPostalAddresses(List.of());
    }
    identityEntity.getPostalAddresses().add(postalAddressEntity);
    return createPostalAddress(postalAddressEntity);
  }

  @Override
  public List<PostalAddress> postalAddresses(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(mobileNumber, emailAddress, username, sessionUserIdentifier);
    return identityEntity.getPostalAddresses().stream().map(this::createPostalAddress).toList();
  }

  @Override
  public PostalAddress updatePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String postalAddressIdentifier,
      PostalAddress postalAddress)
      throws PedistackException {
    final AddressEntity persistedAddressEntity =
        addressInformationOperationManager.findAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, postalAddressIdentifier);
    final AddressEntity postalAddressEntity =
        addressInformationOperationManager.addOrUpdatePostalAddressInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            persistedAddressEntity.getUser().getId(),
            postalAddressIdentifier,
            postalAddress);
    return createPostalAddress(postalAddressEntity);
  }

  @Override
  @Transactional
  public void removePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String postalAddressIdentifier)
      throws PedistackException {
    final AddressEntity addressEntity =
        addressInformationOperationManager.findAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, postalAddressIdentifier);
    if (!addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createUnauthorizedException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final IdentityEntity identityEntity =
        identityEntityDaoManager.findByUserIdentifier(addressEntity.getUser().getId());
    identityEntity.getPostalAddresses().remove(addressEntity);
    identityEntityDaoManager.save(identityEntity);
    addressInformationOperationManager.deleteAddressInformation(
        tenant, sessionUserIdentifier, sessionReference, postalAddressIdentifier);
  }

  @Override
  @Transactional
  public CommunicationAddress addCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      CommunicationAddress communicationAddress)
      throws PedistackException {
    IdentityEntity identityEntity =
        identityEntityInformation(mobileNumber, emailAddress, username, sessionUserIdentifier);
    if (identityEntity.getCommunicationAddresses().size() > 10) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.COMMUNICATION_ADDRESS_SIZE_LIMIT_ERROR_DESCRIPTION);
    }
    final AddressEntity communicationAddressEntity =
        addressInformationOperationManager.addOrUpdateCommunicationAddressInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            identityEntity.getUser().getId(),
            null,
            communicationAddress);
    if (identityEntity.getCommunicationAddresses() == null) {
      identityEntity.setCommunicationAddresses(List.of());
    }
    identityEntity.getCommunicationAddresses().add(communicationAddressEntity);
    return createCommunicationAddress(communicationAddressEntity);
  }

  @Override
  public List<CommunicationAddress> communicationAddresses(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(mobileNumber, emailAddress, username, sessionUserIdentifier);
    return identityEntity.getCommunicationAddresses().stream()
        .map(this::createCommunicationAddress)
        .toList();
  }

  @Override
  @Transactional
  public CommunicationAddress updateCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String communicationAddressIdentifier,
      CommunicationAddress communicationAddress)
      throws PedistackException {
    final AddressEntity persistedAddressEntity =
        addressInformationOperationManager.findAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, communicationAddressIdentifier);
    final AddressEntity communicationAddressEntity =
        addressInformationOperationManager.addOrUpdateCommunicationAddressInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            persistedAddressEntity.getUser().getId(),
            communicationAddressIdentifier,
            communicationAddress);
    return createCommunicationAddress(communicationAddressEntity);
  }

  @Override
  @Transactional
  public void removeCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String communicationAddressIdentifier)
      throws PedistackException {
    final AddressEntity addressEntity =
        addressInformationOperationManager.findAddressInformation(
            tenant, sessionUserIdentifier, sessionReference, communicationAddressIdentifier);
    if (!addressEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createUnauthorizedException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final IdentityEntity identityEntity =
        identityEntityDaoManager.findByUserIdentifier(addressEntity.getUser().getId());
    identityEntity.getCommunicationAddresses().remove(addressEntity);
    identityEntityDaoManager.save(identityEntity);
    addressInformationOperationManager.deleteAddressInformation(
        tenant, sessionUserIdentifier, sessionReference, communicationAddressIdentifier);
  }

  @Override
  @Transactional
  public Identification addIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      Identification identification)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(mobileNumber, emailAddress, username, sessionUserIdentifier);
    final IdentificationEntity identificationEntity =
        identificationInformationOperationManager.addOrUpdateIdentificationInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            identityEntity.getUser().getId(),
            null,
            identification);
    if (identityEntity.getIdentifications() == null) {
      identityEntity.setIdentifications(List.of());
    }
    identityEntity.getIdentifications().add(identificationEntity);
    return createIdentificationResponse(identificationEntity);
  }

  @Override
  @Transactional
  public Identification updateIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identificationIdentifier,
      Identification identification)
      throws PedistackException {
    final IdentificationEntity identificationEntity =
        identificationInformationOperationManager.addOrUpdateIdentificationInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            null,
            identificationIdentifier,
            identification);
    return createIdentificationResponse(identificationEntity);
  }

  @Override
  @Transactional
  public void removeIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identificationIdentifier)
      throws PedistackException {
    final IdentificationEntity identificationEntity =
        identificationInformationOperationManager.identificationInformation(
            tenant, sessionUserIdentifier, sessionReference, identificationIdentifier);
    if (!identificationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createUnauthorizedException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final IdentityEntity identityEntity =
        identityEntityDaoManager.findByUserIdentifier(identificationEntity.getUser().getId());
    identityEntity.getIdentifications().remove(identificationEntity);
    identityEntityDaoManager.save(identityEntity);
    identificationInformationOperationManager.removeIdentification(
        tenant, sessionUserIdentifier, sessionReference, identificationIdentifier);
  }

  @Override
  @Transactional
  public void identityActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      IdentityActivationRequest identityActivationRequest)
      throws PedistackException {
    if (identityActivationRequest.getMsisdn() != null) {
      userOperationManager.msisdnActivation(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          identityActivationRequest.getMsisdn(),
          identityActivationRequest.getOtp());
    } else if (identityActivationRequest.getEmailAddress() != null) {
      userOperationManager.emailAddressActivation(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          identityActivationRequest.getEmailAddress(),
          identityActivationRequest.getOtp());
    }
  }

  @Override
  public String msisdnActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException {
    return userOperationManager.msisdnActivationOtp(
        tenant, sessionUserIdentifier, sessionReference, msisdn);
  }

  @Override
  public String emailActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException {
    return userOperationManager.emailActivationOtp(
        tenant, sessionUserIdentifier, sessionReference, emailAddress);
  }

  @Override
  public void resendMsisdnActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException {
    userOperationManager.resendMsisdnActivationOtp(
        tenant, sessionUserIdentifier, sessionReference, msisdn);
  }

  @Override
  public void resendEmailActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException {
    userOperationManager.resendEmailAddressActivationOtp(
        tenant, sessionUserIdentifier, sessionReference, emailAddress);
  }

  @Override
  @Transactional
  public SocialMedia addOrUpdateSocialMediaInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, SocialMedia socialMedia)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(null, null, null, sessionUserIdentifier);
    final SocialMediaAccountEntity socialMediaAccountEntity =
        socialMediaInformationOperationManager.addOrUpdateSocialMediaAccountInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            sessionUserIdentifier,
            Optional.ofNullable(identityEntity.getSocialMediaAccount())
                .map(SocialMediaAccountEntity::getId)
                .orElse(null),
            socialMedia);
    return createSocialMediaResponse(socialMediaAccountEntity);
  }

  @Override
  public SocialMedia socialMediaInformation(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(null, null, null, sessionUserIdentifier);
    return createSocialMediaResponse(identityEntity.getSocialMediaAccount());
  }

  @Override
  @Transactional
  public Developer addOrUpdateDeveloperInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Developer developer)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(null, null, null, sessionUserIdentifier);
    final DeveloperInformationEntity developerInformationEntity =
        developerInformationOperationManager.addOrUpdateDeveloperInformation(
            tenant,
            sessionUserIdentifier,
            sessionReference,
            sessionUserIdentifier,
            Optional.ofNullable(identityEntity.getDeveloperInformation())
                .map(DeveloperInformationEntity::getId)
                .orElse(null),
            developer);
    return createDeveloperResponse(developerInformationEntity);
  }

  @Override
  public Developer developerInformation(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(null, null, null, sessionUserIdentifier);
    return createDeveloperResponse(identityEntity.getDeveloperInformation());
  }

  @Override
  public NextOfKin addOrUpdateNextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier,
      NextOfKin nextOfKin)
      throws PedistackException {
    return createNextOfKinResponse(
        nextOfKinOperationManager.addOrUpdateNextOfKinInformation(
            tenant, sessionUserIdentifier, sessionReference, nextOfKinIdentifier, nextOfKin));
  }

  @Override
  public List<NextOfKin> nextOfKins(
      String tenant, String sessionUserIdentifier, String sessionReference) {
    return nextOfKinOperationManager.nextOfKins(tenant, sessionUserIdentifier, sessionReference);
  }

  @Override
  public NextOfKin nextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException {
    return nextOfKinOperationManager.nextOfKinInformation(
        tenant, sessionUserIdentifier, sessionReference, nextOfKinIdentifier);
  }

  @Override
  @Transactional
  public void deleteNextOfKin(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityInformation(null, null, null, sessionUserIdentifier);
    final NextOfKinEntity nextOfKinEntity =
        identityEntity.getKins().stream()
            .filter(kin -> kin.getId().equals(nextOfKinIdentifier))
            .findAny()
            .orElse(null);
    if (nextOfKinEntity == null) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.NEXT_OF_KIN_NOT_FOUND_ERROR_DESCRIPTION);
    }
    identityEntity.getKins().remove(nextOfKinEntity);
    identityEntityDaoManager.save(identityEntity);
    nextOfKinOperationManager.deleteNextOfKin(
        tenant, sessionUserIdentifier, sessionReference, nextOfKinIdentifier);
  }

  private Identity createIdentityResponse(IdentityEntity identityEntity) {
    final Identity identity = new Identity();

    identity.setEmailAddress(identityEntity.getUser().getEmailAddress());
    identity.setMsisdn(identityEntity.getUser().getMobileNumber());
    identity.setUsername(identityEntity.getUser().getUsername());

    if (identityEntity.getPerson() != null) {
      identity.setPerson(createPersonResponse(identityEntity.getPerson()));
    }
    if (identityEntity.getDeveloperInformation() != null) {
      identity.setDeveloper(createDeveloperResponse(identityEntity.getDeveloperInformation()));
    }
    if (identityEntity.getSocialMediaAccount() != null) {
      identity.setSocialMedia(createSocialMediaResponse(identityEntity.getSocialMediaAccount()));
    }
    identity.setBusiness(createBusinessResponse(identityEntity.getBusiness()));
    identity.setIdentifications(
        identityEntity.getIdentifications().stream()
            .map(this::createIdentificationResponse)
            .toList());
    identity.setPostalAddresses(
        identityEntity.getPostalAddresses().stream().map(this::createPostalAddress).toList());
    identity.setCommunicationAddresses(
        identityEntity.getCommunicationAddresses().stream()
            .map(this::createCommunicationAddress)
            .toList());
    identity.setInternalIdentity(identityEntity.getInternalIdentity());
    identity.setType(IdentityType.valueOf(identityEntity.getUser().getProfile().getType()));
    identity.setStatus(
        com.pedistack.identity.v1_0.common.IdentityStatus.valueOf(identityEntity.getStatus()));
    identity.setKins(identityEntity.getKins().stream().map(this::createNextOfKinResponse).toList());
    return identity;
  }

  private Person createPersonResponse(final PersonEntity personEntity) {
    if (personEntity != null) {
      final Person person = new Person();
      BeanUtils.copyProperties(personEntity, person);
      if (personEntity.getMaritalStatus() != null) {
        person.setMaritalStatus(MaritalStatus.valueOf(personEntity.getMaritalStatus()));
      }
      if (personEntity.getLanguageCode() != null) {
        person.setLanguageCode(LanguageCode.valueOf(personEntity.getLanguageCode()));
      }
      if (personEntity.getGender() != null) {
        person.setGender(GenderCode.valueOf(personEntity.getGender()));
      }
      if (personEntity.getTaxationCountryCode() != null) {
        person.setTaxationCountryCode(CountryCode.valueOf(personEntity.getTaxationCountryCode()));
      }
      if (personEntity.getBirthCountryCode() != null) {
        person.setBirthCountryCode(CountryCode.valueOf(personEntity.getBirthCountryCode()));
      }
      return person;
    }
    return null;
  }

  private Developer createDeveloperResponse(DeveloperInformationEntity developerInformationEntity) {
    if (developerInformationEntity != null) {
      final Developer developer = new Developer();
      BeanUtils.copyProperties(developerInformationEntity, developer);
      return developer;
    }
    return null;
  }

  private SocialMedia createSocialMediaResponse(SocialMediaAccountEntity socialMediaAccountEntity) {
    if (socialMediaAccountEntity != null) {
      final SocialMedia socialMedia = new SocialMedia();
      BeanUtils.copyProperties(socialMediaAccountEntity, socialMedia);
      return socialMedia;
    }
    return null;
  }

  private Business createBusinessResponse(BusinessEntity businessEntity) {
    if (businessEntity != null) {
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
    return null;
  }

  private PostalAddress createPostalAddress(AddressEntity addressEntity) {
    if (addressEntity != null) {
      final PostalAddress postalAddress = new PostalAddress();
      BeanUtils.copyProperties(addressEntity, postalAddress);
      postalAddress.setCountryCode(CountryCode.valueOf(addressEntity.getCountryCode()));
      postalAddress.setId(addressEntity.getId());
      return postalAddress;
    }
    return null;
  }

  private CommunicationAddress createCommunicationAddress(AddressEntity addressEntity) {
    if (addressEntity != null) {
      final CommunicationAddress communicationAddress = new CommunicationAddress();
      BeanUtils.copyProperties(addressEntity, communicationAddress);
      communicationAddress.setId(addressEntity.getId());
      return communicationAddress;
    }
    return null;
  }

  private Identification createIdentificationResponse(IdentificationEntity identificationEntity) {
    if (identificationEntity != null) {
      final Identification identification = new Identification();
      BeanUtils.copyProperties(identificationEntity, identification);
      identification.setType(IdentificationType.valueOf(identificationEntity.getType()));
      identification.setId(identificationEntity.getId());
      return identification;
    }
    return null;
  }

  private IdentityEntity identityEntityInformation(
      String msisdn, String emailAddress, String username, String userIdentifier)
      throws PedistackException {
    IdentityEntity identityEntity = null;
    if (msisdn != null) {
      identityEntity = identityEntityDaoManager.findByMobileNumber(msisdn);
    } else if (username != null) {
      identityEntity = identityEntityDaoManager.findByUsername(username);
    } else if (emailAddress != null) {
      identityEntity = identityEntityDaoManager.findByEmailAddress(emailAddress);
    } else if (userIdentifier != null) {
      identityEntity = identityEntityDaoManager.findByUserIdentifier(userIdentifier);
    }
    if (identityEntity == null) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION);
    }
    return identityEntity;
  }

  private NextOfKin createNextOfKinResponse(NextOfKinEntity nextOfKinEntity) {
    final NextOfKin nextOfKin = new NextOfKin();
    BeanUtils.copyProperties(nextOfKinEntity, nextOfKin);
    return nextOfKin;
  }
}
