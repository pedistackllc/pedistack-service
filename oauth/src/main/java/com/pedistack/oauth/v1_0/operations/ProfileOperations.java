package com.pedistack.oauth.v1_0.operations;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.common.io.PageResponse;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import com.pedistack.oauth.operation.managers.ProfileOperationManager;
import com.pedistack.oauth.v1_0.ProfileRequest;
import com.pedistack.oauth.v1_0.common.Profile;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Oauth"})
@RestController
public class ProfileOperations {

  private final AuthorizationOperationManager authorizationOperationManager;
  private final ProfileOperationManager profileOperationManager;

  public ProfileOperations(
      AuthorizationOperationManager authorizationOperationManager,
      ProfileOperationManager profileOperationManager) {
    this.authorizationOperationManager = authorizationOperationManager;
    this.profileOperationManager = profileOperationManager;
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Create a new authentication profile")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "profile")
  public ResponseEntity<GenericResponse<Profile>> create(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody ProfileRequest profileRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_PROFILE_PERMISSION);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            profileOperationManager.create(
                null,
                authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                authorizationOperationManager.sessionReference(httpHeaders),
                profileRequest),
            "Profile created successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Fetch the authentication profile information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "profile/{id}")
  public ResponseEntity<GenericResponse<Profile>> get(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String profileIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_PROFILE_PERMISSION);
    final Profile profile =
        profileOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            profileIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(profile, "Profile fetched successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "List authentication profiles")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "profiles")
  public ResponseEntity<GenericResponse<PageResponse<Profile>>> list(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam("page") int page,
      @RequestParam("size") int size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_PROFILES_PERMISSION);
    final PageResponse<Profile> profilePageResponse =
        profileOperationManager.list(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            page,
            size);
    return ResponseEntity.ok(
        GenericResponse.createResponse(profilePageResponse, "Profiles listed successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Update authentication profile information")
  @PutMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "profile/{id}")
  public ResponseEntity<GenericResponse<Profile>> update(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String profileIdentifier,
      @RequestBody ProfileRequest profileRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_PROFILE_PERMISSION);
    final Profile profile =
        profileOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            profileIdentifier,
            profileRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(profile, "Profile updated successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Delete the authentication profile information")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "profile/{id}")
  public ResponseEntity<GenericResponse<Void>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String profileIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_PROFILE_PERMISSION);
    profileOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        profileIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Profile removed successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Add or remove a permission(s) from an authentication profile")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "profile/{id}/permission")
  public ResponseEntity<GenericResponse<Profile>> addOrRemovePermission(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String profileIdentifier,
      @RequestBody List<String> permissions,
      @RequestParam("status") Boolean status)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_PROFILE_PERMISSION_PERMISSION);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            profileOperationManager.updateProfilePermissions(
                authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                authorizationOperationManager.sessionReference(httpHeaders),
                profileIdentifier,
                permissions,
                status),
            "Profile permission updated successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Add or remove a permission(s) from an authentication profile")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "profile/{id}/additionalinfo")
  public ResponseEntity<GenericResponse<Profile>> addOrRemoveAdditionalInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String profileIdentifier,
      @RequestBody List<String> permissions,
      @RequestParam("status") Boolean status)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_PROFILE_PERMISSION_PERMISSION);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            profileOperationManager.updateProfilePermissions(
                authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                authorizationOperationManager.sessionReference(httpHeaders),
                profileIdentifier,
                permissions,
                status),
            "Profile permission updated successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Add additional information to an authentication profile")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "profile/{id}/additionalinformation")
  public ResponseEntity<GenericResponse<Profile>> addRoleAdditionalInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String profileIdentifier,
      @RequestBody Map<String, String> additionalInformation)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_PROFILE_ADDITIONAL_INFORMATION_PERMISSION);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            profileOperationManager.addProfileAdditionalInformation(
                authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                authorizationOperationManager.sessionReference(httpHeaders),
                profileIdentifier,
                additionalInformation),
            "Profile additional information updated successfully"));
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Remove additional information to an authentication profile")
  @DeleteMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "profile/{id}/additionalinformation")
  public ResponseEntity<GenericResponse<Profile>> removeRoleAdditionalInformation(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String profileIdentifier,
      @RequestBody List<String> additionalInformation)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_PROFILE_ADDITIONAL_INFORMATION_PERMISSION);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            profileOperationManager.removeProfileAdditionalInformation(
                authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                authorizationOperationManager.sessionReference(httpHeaders),
                profileIdentifier,
                additionalInformation),
            "Profile additional information updated successfully"));
  }
}
