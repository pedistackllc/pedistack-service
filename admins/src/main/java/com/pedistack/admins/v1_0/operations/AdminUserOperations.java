package com.pedistack.admins.v1_0.operations;

import com.pedistack.admins.operation.managers.AdminUserOperationManager;
import com.pedistack.admins.v1_0.AdminUserRequest;
import com.pedistack.admins.v1_0.common.AdminUser;
import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.common.io.PageResponse;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Admins"})
@RestController
public class AdminUserOperations {

  private final AdminUserOperationManager adminUserOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public AdminUserOperations(
      AdminUserOperationManager adminUserOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.adminUserOperationManager = adminUserOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Admin"},
      summary = "Create a new admin account")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "admin")
  public ResponseEntity<GenericResponse<AdminUser>> create(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody AdminUserRequest adminUserRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_ADMIN_PERMISSION);
    final AdminUser admin =
        adminUserOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            adminUserRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(admin, "Admin user created successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Get admin account information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}")
  public ResponseEntity<GenericResponse<AdminUser>> get(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_ADMIN_PERMISSION);
    final AdminUser admin =
        adminUserOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            adminIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(admin, "Admin user fetched successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Get list of admin accounts")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admins")
  public ResponseEntity<GenericResponse<PageResponse<AdminUser>>> list(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam("page") Integer page,
      @RequestParam("size") Integer size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_ADMINS_PERMISSION);
    final PageResponse<AdminUser> adminPageResponse =
        adminUserOperationManager.list(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            page,
            size);
    return ResponseEntity.ok(
        GenericResponse.createResponse(adminPageResponse, "Admin users fetched successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Update admin account information")
  @PutMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "admin/{id}")
  public ResponseEntity<GenericResponse<AdminUser>> update(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String adminIdentifier,
      @RequestBody AdminUserRequest adminUserRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_ADMIN_PERMISSION);
    final AdminUser admin =
        adminUserOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            adminIdentifier,
            adminUserRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(admin, "Admin user updated successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Delete admin account information")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}")
  public ResponseEntity<GenericResponse<Void>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_ADMIN_PERMISSION);
    adminUserOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        adminIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Admin user deleted successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Block admin account information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}/block")
  public ResponseEntity<GenericResponse<Void>> block(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.BLOCK_ADMIN_PERMISSION);
    adminUserOperationManager.blockAdminUser(
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        adminIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Admin user blocked successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Unblock admin account information")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}/block")
  public ResponseEntity<GenericResponse<Void>> unblock(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UNBLOCK_ADMIN_PERMISSION);
    adminUserOperationManager.unblockAdminUser(
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        adminIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Admin user unblocked successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Close admin account information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}/close")
  public ResponseEntity<GenericResponse<Void>> close(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CLOSE_ADMIN_PERMISSION);
    adminUserOperationManager.closeAdminUser(
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        adminIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Admin user closed successfully"));
  }

  @Operation(
      tags = {"Admin"},
      summary = "Activate admin account information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "admin/{id}/activate")
  public ResponseEntity<GenericResponse<Void>> activate(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String adminIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ACTIVATE_ADMIN_PERMISSION);
    adminUserOperationManager.activateAdminUser(
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        adminIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Admin user activated successfully"));
  }
}
