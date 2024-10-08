package com.pedistack.identity.operations;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.identity.operation.managers.IdentityOperationManager;
import com.pedistack.identity.v1_0.*;
import com.pedistack.identity.v1_0.common.*;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Identity"})
@RestController
public class IdentityOperations {

  private final AuthorizationOperationManager authorizationOperationManager;
  private final IdentityOperationManager identityOperationManager;

  public IdentityOperations(
      AuthorizationOperationManager authorizationOperationManager,
      IdentityOperationManager identityOperationManager) {
    this.authorizationOperationManager = authorizationOperationManager;
    this.identityOperationManager = identityOperationManager;
  }

  @Operation(
      tags = {"Identity"},
      summary = "Activate a new identity")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/activation")
  public ResponseEntity<GenericResponse<Void>> identityActivation(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody IdentityActivationRequest identityActivationRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.IDENTITY_ACTIVATION_PERMISSION);
    identityOperationManager.identityActivation(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        identityActivationRequest);
    return ResponseEntity.ok(GenericResponse.createResponse("Identity activated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Msisdn activation token information")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/activation/otp")
  public ResponseEntity<GenericResponse<String>> msisdnActivationOtp(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("msisdn") String msisdn)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_ACTIVATION_OTP_PERMISSION);
    final String otp =
        identityOperationManager.msisdnActivationToken(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn);
    return ResponseEntity.ok(
        GenericResponse.createResponse(otp, "Identity token fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Email address activation token information")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{emailAddress}/activation/otp")
  public ResponseEntity<GenericResponse<String>> emailActivationOtp(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("emailAddress") String emailAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_ACTIVATION_OTP_PERMISSION);
    final String otp =
        identityOperationManager.emailActivationToken(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            emailAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(otp, "Identity token fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Resend msisdn activation token information")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/activation/otp/refresh")
  public ResponseEntity<GenericResponse<String>> resendMsisdnActivationOtp(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("msisdn") String msisdn)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.RESEND_IDENTITY_ACTIVATION_OTP_PERMISSION);
    identityOperationManager.resendMsisdnActivationToken(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        msisdn);
    return ResponseEntity.ok(GenericResponse.createResponse("Identity token sent successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Resend email address activation token information")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{emailAddress}/activation/otp/refresh")
  public ResponseEntity<GenericResponse<Void>> resendEmailActivationOtp(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("emailAddress") String emailAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.RESEND_IDENTITY_ACTIVATION_OTP_PERMISSION);
    identityOperationManager.resendEmailActivationToken(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        emailAddress);
    return ResponseEntity.ok(GenericResponse.createResponse("Identity token sent successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Register a new customer")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/customer")
  public ResponseEntity<GenericResponse<IdentityResponse>> customerRegistration(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody CustomerRegistrationRequest customerRegistrationRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CUSTOMER_REGISTRATION_PERMISSION);
    final IdentityResponse identityResponse =
        identityOperationManager.customerRegistration(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            customerRegistrationRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identityResponse, "Customer registered successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get identity information of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id")
  public ResponseEntity<GenericResponse<Identity>> identityInformation(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_INFORMATION_PERMISSION);
    final Identity identity =
        identityOperationManager.identityInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(identity, "Customer information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Register a new agent")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/agent")
  public ResponseEntity<GenericResponse<IdentityResponse>> agentRegistration(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody AgentRegistrationRequest agentRegistrationRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.AGENT_REGISTRATION_PERMISSION);
    final IdentityResponse identityResponse =
        identityOperationManager.agentRegistration(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            agentRegistrationRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identityResponse, "Agent registered successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Register a new business")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/business")
  public ResponseEntity<GenericResponse<IdentityResponse>> businessRegistration(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody BusinessRegistrationRequest businessRegistrationRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.BUSINESS_REGISTRATION_PERMISSION);
    final IdentityResponse identityResponse =
        identityOperationManager.businessRegistration(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            businessRegistrationRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identityResponse, "Business registered successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the personal information of the authorized identity")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/person")
  public ResponseEntity<GenericResponse<Person>> updatePersonalInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody Person person)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_PERSONAL_INFORMATION_PERMISSION);
    final Person personalInformation =
        identityOperationManager.updatePersonalInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            person);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            personalInformation, "Personal information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the business information of an authorized identity")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/business")
  public ResponseEntity<GenericResponse<Business>> updateBusinessInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody Business business)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_BUSINESS_INFORMATION_PERMISSION);
    final Business businessInformation =
        identityOperationManager.updateBusinessInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            business);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            businessInformation, "Business information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add postal address for an authorized identity")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/address/postal")
  public ResponseEntity<GenericResponse<PostalAddress>> addPostalAddressInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody PostalAddress postalAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final PostalAddress persistedPostalAddress =
        identityOperationManager.addPostalAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            null,
            postalAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedPostalAddress, "Postal address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Fetch the list of postal addresses of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/address/postal")
  public ResponseEntity<GenericResponse<List<PostalAddress>>> postalAddresses(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_POSTAL_ADDRESSES_PERMISSION);
    final List<PostalAddress> postalAddresses =
        identityOperationManager.postalAddresses(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            null);
    return ResponseEntity.ok(
        GenericResponse.createResponse(postalAddresses, "Postal addresses fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the postal address information of an identity")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/address/postal/{id}")
  public ResponseEntity<GenericResponse<PostalAddress>> updatePostalAddressInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String postalAddressIdentifier,
      @RequestBody PostalAddress postalAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final PostalAddress persistedPostalAddress =
        identityOperationManager.updatePostalAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            postalAddressIdentifier,
            postalAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedPostalAddress, "Postal address updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Delete the postal address information of an identity")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/address/postal/{id}")
  public ResponseEntity<GenericResponse<PostalAddress>> removePostalAddressInformation(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String postalAddressIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.REMOVE_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    identityOperationManager.removePostalAddressInformation(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        postalAddressIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Postal address removed successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add communication address of an authorized identity")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/address/communication")
  public ResponseEntity<GenericResponse<CommunicationAddress>> addCommunicationAddressInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody CommunicationAddress communicationAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_COMMUNICATION_ADDRESS_PERMISSION);
    final CommunicationAddress persistedCommunicationAddress =
        identityOperationManager.addCommunicationAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            null,
            communicationAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedCommunicationAddress, "Communication address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update communication address information of an identity")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/address/communication/{id}")
  public ResponseEntity<GenericResponse<CommunicationAddress>>
      updateCommunicationAddressInformation(
          @RequestHeader HttpHeaders httpHeaders,
          @PathVariable("id") String communicationAddressIdentifier,
          @RequestBody CommunicationAddress communicationAddress)
          throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_COMMUNICATION_ADDRESS_PERMISSION);
    final CommunicationAddress persistedCommunicationAddress =
        identityOperationManager.updateCommunicationAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            communicationAddressIdentifier,
            communicationAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedCommunicationAddress, "Communication address updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Fetch the list of communication addresses of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/address/communication")
  public ResponseEntity<GenericResponse<List<CommunicationAddress>>> communicationAddresses(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_COMMUNICATION_ADDRESSES_PERMISSION);
    final List<CommunicationAddress> communicationAddresses =
        identityOperationManager.communicationAddresses(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            null);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            communicationAddresses, "Communication addresses fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Delete communication address of an identity")
  @DeleteMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "id/address/communication/{id}")
  public ResponseEntity<GenericResponse<Void>> removeCommunicationAddressInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String communicationAddressIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.REMOVE_IDENTITY_COMMUNICATION_ADDRESS_PERMISSION);
    identityOperationManager.removeCommunicationAddressInformation(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        communicationAddressIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse("Communication address removed successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add identification information of an authorized user")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/identification")
  public ResponseEntity<GenericResponse<Identification>> addIdentificationInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody Identification identification)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_IDENTIFICATION_PERMISSION);
    final Identification persistedIdentification =
        identityOperationManager.addIdentificationInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            null,
            identification);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedIdentification, "Identification created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update identification information of an identity")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/identification/{id}")
  public ResponseEntity<GenericResponse<Identification>> updateIdentificationInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String identificationIdentifier,
      @RequestBody Identification identification)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_IDENTIFICATION_PERMISSION);
    final Identification persistedIdentification =
        identityOperationManager.updateIdentificationInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            identificationIdentifier,
            identification);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedIdentification, "Identification updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Delete identification information of an identity")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/identification/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteIdentificationInformation(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String identificationIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.REMOVE_IDENTITY_IDENTIFICATION_PERMISSION);
    identityOperationManager.removeIdentificationInformation(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        identificationIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Identification removed successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add/update the social media information of an authorized user")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/social")
  public ResponseEntity<GenericResponse<SocialMedia>> createSocialMedia(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody SocialMedia socialMedia)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_SOCIAL_MEDIA_PERMISSION);
    final SocialMedia persistedSocialMedia =
        identityOperationManager.addOrUpdateSocialMediaInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            socialMedia);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedSocialMedia, "Social media information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Fetch the social media information of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/social")
  public ResponseEntity<GenericResponse<SocialMedia>> fetchSocialMedia(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_SOCIAL_MEDIA_PERMISSION);
    final SocialMedia persistedSocialMedia =
        identityOperationManager.socialMediaInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedSocialMedia, "Social media information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add/update the developer information of an authorized user")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/developer")
  public ResponseEntity<GenericResponse<Developer>> addUpdateDeveloperInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody Developer developer)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_DEVELOPER_PERMISSION);
    final Developer persistedDeveloper =
        identityOperationManager.addOrUpdateDeveloperInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            developer);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedDeveloper, "Developer information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Fetch the developer information of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/developer")
  public ResponseEntity<GenericResponse<Developer>> fetchDeveloperInformation(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_DEVELOPER_PERMISSION);
    final Developer persistedDeveloper =
        identityOperationManager.developerInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedDeveloper, "Developer information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add the kin information of an authorized user")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/kin")
  public ResponseEntity<GenericResponse<NextOfKin>> addNextOfKinInformation(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody NextOfKin nextOfKin)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_KIN_PERMISSION);
    final NextOfKin persistedKin =
        identityOperationManager.addOrUpdateNextOfKinInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            nextOfKin);
    return ResponseEntity.ok(
        GenericResponse.createResponse(persistedKin, "Kin information added successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the kin information of an authorized user")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/kin/{id}")
  public ResponseEntity<GenericResponse<NextOfKin>> updateNextOfKinInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String kinIdentifier,
      @RequestBody NextOfKin nextOfKin)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_KIN_PERMISSION);
    final NextOfKin persistedKin =
        identityOperationManager.addOrUpdateNextOfKinInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            kinIdentifier,
            nextOfKin);
    return ResponseEntity.ok(
        GenericResponse.createResponse(persistedKin, "Kin information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get the list of kins of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/kins")
  public ResponseEntity<GenericResponse<List<NextOfKin>>> nextOfKins(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_KINS_PERMISSION);
    final List<NextOfKin> nextOfKins =
        identityOperationManager.nextOfKins(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(nextOfKins, "Kin information listed successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get the kin information with an identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/kin/{id}")
  public ResponseEntity<GenericResponse<NextOfKin>> nextOfKinInformation(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String nextOfKinIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_KIN_PERMISSION);
    final NextOfKin nextOfKin =
        identityOperationManager.nextOfKinInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            nextOfKinIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(nextOfKin, "Kin information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Delete the kin information with an identifier")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/kin/{id}")
  public ResponseEntity<GenericResponse<Void>> deleteNextOfKinInformation(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String nextOfKinIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_IDENTITY_KIN_PERMISSION);
    identityOperationManager.deleteNextOfKin(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        nextOfKinIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse("Kin information removed successfully"));
  }
}
