package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.identity.v1_0.*;
import com.pedistack.identity.v1_0.common.*;
import java.util.List;

public interface IdentityOperationManager {

  IdentityResponse customerRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      CustomerRegistrationRequest customerRegistrationRequest)
      throws PedistackException;

  IdentityResponse businessRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      BusinessRegistrationRequest businessRegistrationRequest)
      throws PedistackException;

  IdentityResponse agentRegistration(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      AgentRegistrationRequest agentRegistrationRequest)
      throws PedistackException;

  Identity identityInformation(String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException;

  Identity identityInformationWithMsisdn(
      String tenant, String sessionUserIdentifier, String sessionReference, String mobileNumber)
      throws PedistackException;

  Identity identityInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  Identity identityInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException;

  Person updatePersonalInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Person person)
      throws PedistackException;

  Person updatePersonalInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      Person person)
      throws PedistackException;

  Business updateBusinessInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Business business)
      throws PedistackException;

  Business updateBusinessInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      Business business)
      throws PedistackException;

  PostalAddress addPostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      PostalAddress postalAddress)
      throws PedistackException;

  List<PostalAddress> postalAddresses(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress)
      throws PedistackException;

  PostalAddress updatePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String postalAddressIdentifier,
      PostalAddress postalAddress)
      throws PedistackException;

  void removePostalAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String postalAddressIdentifier)
      throws PedistackException;

  CommunicationAddress addCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      CommunicationAddress communicationAddress)
      throws PedistackException;

  List<CommunicationAddress> communicationAddresses(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress)
      throws PedistackException;

  CommunicationAddress updateCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String communicationAddressIdentifier,
      CommunicationAddress communicationAddress)
      throws PedistackException;

  void removeCommunicationAddressInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String communicationAddressIdentifier)
      throws PedistackException;

  Identification addIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String mobileNumber,
      String username,
      String emailAddress,
      Identification identification)
      throws PedistackException;

  Identification updateIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identificationIdentifier,
      Identification identification)
      throws PedistackException;

  void removeIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identificationIdentifier)
      throws PedistackException;

  void identityActivation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      IdentityActivationRequest identityActivationRequest)
      throws PedistackException;

  String msisdnActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException;

  String emailActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  void resendMsisdnActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String msisdn)
      throws PedistackException;

  void resendEmailActivationToken(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  SocialMedia addOrUpdateSocialMediaInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, SocialMedia socialMedia)
      throws PedistackException;

  SocialMedia socialMediaInformation(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException;

  Developer addOrUpdateDeveloperInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, Developer developer)
      throws PedistackException;

  Developer developerInformation(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException;
}
