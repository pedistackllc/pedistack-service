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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Identity"})
@RequestMapping("v1")
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
      summary = "Update the personal information of an identity with mobile number")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/person")
  public ResponseEntity<GenericResponse<Person>> updatePersonalInformationWithMsisdn(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("msisdn") String msisdn,
      @RequestBody Person person)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_PERSONAL_INFORMATION_PERMISSION);
    final Person personalInformation =
        identityOperationManager.updatePersonalInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn,
            null,
            null,
            person);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            personalInformation, "Personal information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the personal information of an identity with username")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/username/{username}/person")
  public ResponseEntity<GenericResponse<Person>> updatePersonalInformationWithUsername(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("username") String username,
      @RequestBody Person person)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_PERSONAL_INFORMATION_PERMISSION);
    final Person personalInformation =
        identityOperationManager.updatePersonalInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            username,
            null,
            person);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            personalInformation, "Personal information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the personal information of an identity with email address")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{email}/person")
  public ResponseEntity<GenericResponse<Person>> updatePersonalInformationWithEmailAddress(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("email") String emailAddress,
      @RequestBody Person person)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_PERSONAL_INFORMATION_PERMISSION);
    final Person personalInformation =
        identityOperationManager.updatePersonalInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            emailAddress,
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
      summary = "Update the business information of an identity with mobile number")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/business")
  public ResponseEntity<GenericResponse<Business>> updateBusinessInformationWithMsisdn(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("msisdn") String msisdn,
      @RequestBody Business business)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_BUSINESS_INFORMATION_PERMISSION);
    final Business businessInformation =
        identityOperationManager.updateBusinessInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn,
            null,
            null,
            business);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            businessInformation, "Business information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the business information of an identity with username")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/username/{username}/business")
  public ResponseEntity<GenericResponse<Business>> updateBusinessInformationWithUsername(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("username") String username,
      @RequestBody Business business)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_BUSINESS_INFORMATION_PERMISSION);
    final Business businessInformation =
        identityOperationManager.updateBusinessInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            username,
            null,
            business);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            businessInformation, "Business information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Update the business information of an identity with email address")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{email}/business")
  public ResponseEntity<GenericResponse<Business>> updateBusinessInformationWithEmailAddress(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("email") String emailAddress,
      @RequestBody Business business)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_IDENTITY_BUSINESS_INFORMATION_PERMISSION);
    final Business businessInformation =
        identityOperationManager.updateBusinessInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            emailAddress,
            business);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            businessInformation, "Business information updated successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get identity information with mobile number")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/msisdn/{msisdn}")
  public ResponseEntity<GenericResponse<Identity>> identityInformationWithMsisdn(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("msisdn") String msisdn)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_INFORMATION_PERMISSION);
    final Identity identity =
        identityOperationManager.identityInformationWithMsisdn(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identity, "Identity information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get identity information with email address")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/email/{emailAddress}")
  public ResponseEntity<GenericResponse<Identity>> identityInformationWithEmailAddress(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("emailAddress") String emailAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_INFORMATION_PERMISSION);
    final Identity identity =
        identityOperationManager.identityInformationWithEmailAddress(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            emailAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identity, "Identity information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Get identity information with username")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "id/username/{username}")
  public ResponseEntity<GenericResponse<Identity>> identityInformationWithUsername(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("username") String username)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_IDENTITY_INFORMATION_PERMISSION);
    final Identity identity =
        identityOperationManager.identityInformationWithUsername(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            username);
    return ResponseEntity.ok(
        GenericResponse.createResponse(identity, "Identity information fetched successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add postal address for an identity with email address")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{email}/address/postal")
  public ResponseEntity<GenericResponse<PostalAddress>> addPostalAddressInformationWithEmailAddress(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("email") String emailAddress,
      @RequestBody PostalAddress postalAddress)
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
            emailAddress,
            postalAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedPostalAddress, "Postal address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add postal address for an identity with username")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/username/{username}/address/postal")
  public ResponseEntity<GenericResponse<PostalAddress>> addPostalAddressInformationWithUsername(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("username") String username,
      @RequestBody PostalAddress postalAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final PostalAddress persistedPostalAddress =
        identityOperationManager.addPostalAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            username,
            null,
            postalAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedPostalAddress, "Postal address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add postal address for an identity with mobile number")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/address/postal")
  public ResponseEntity<GenericResponse<PostalAddress>> addPostalAddressInformationWithMsisdn(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("msisdn") String msisdn,
      @RequestBody PostalAddress postalAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final PostalAddress persistedPostalAddress =
        identityOperationManager.addPostalAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn,
            null,
            null,
            postalAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedPostalAddress, "Postal address created successfully"));
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
      summary = "Add communication address of an identity with an email address")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{email}/address/communication")
  public ResponseEntity<GenericResponse<CommunicationAddress>>
      addCommunicationAddressInformationWithEmailAddress(
          @RequestHeader HttpHeaders httpHeaders,
          @PathVariable("email") String emailAddress,
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
            emailAddress,
            communicationAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedCommunicationAddress, "Postal address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add communication address of an identity with username")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/username/{username}/address/communication")
  public ResponseEntity<GenericResponse<CommunicationAddress>>
      addCommunicationAddressInformationWithUsername(
          @RequestHeader HttpHeaders httpHeaders,
          @PathVariable("username") String username,
          @RequestBody CommunicationAddress communicationAddress)
          throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final CommunicationAddress persistedCommunicationAddress =
        identityOperationManager.addCommunicationAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            username,
            null,
            communicationAddress);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedCommunicationAddress, "Communication address created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add communication address of an identity with mobile number")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/address/communication")
  public ResponseEntity<GenericResponse<CommunicationAddress>>
      addCommunicationAddressInformationWithMsisdn(
          @RequestHeader HttpHeaders httpHeaders,
          @PathVariable("msisdn") String msisdn,
          @RequestBody CommunicationAddress communicationAddress)
          throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_POSTAL_ADDRESS_PERMISSION);
    final CommunicationAddress persistedCommunicationAddress =
        identityOperationManager.addCommunicationAddressInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn,
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
      summary = "Add identification information of an identity with mobile number")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/msisdn/{msisdn}/identification")
  public ResponseEntity<GenericResponse<Identification>> addIdentificationInformationWithMsisdn(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("msisdn") String msisdn,
      @RequestBody Identification identification)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_IDENTIFICATION_PERMISSION);
    final Identification persistedIdentification =
        identityOperationManager.addIdentificationInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            msisdn,
            null,
            null,
            identification);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedIdentification, "Identification created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add identification information of an identity with username")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/username/{username}/identification")
  public ResponseEntity<GenericResponse<Identification>> addIdentificationInformationWithUsername(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("username") String username,
      @RequestBody Identification identification)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_IDENTITY_IDENTIFICATION_PERMISSION);
    final Identification persistedIdentification =
        identityOperationManager.addIdentificationInformation(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            username,
            null,
            identification);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            persistedIdentification, "Identification created successfully"));
  }

  @Operation(
      tags = {"Identity"},
      summary = "Add identification information of an identity with email address")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "id/email/{email}/identification")
  public ResponseEntity<GenericResponse<Identification>>
      addIdentificationInformationWithEmailAddress(
          @RequestHeader HttpHeaders httpHeaders,
          @PathVariable("email") String emailAddress,
          @RequestBody Identification identification)
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
            emailAddress,
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
}
