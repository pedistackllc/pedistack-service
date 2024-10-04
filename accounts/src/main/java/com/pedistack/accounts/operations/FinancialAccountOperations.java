package com.pedistack.accounts.operations;

import com.pedistack.accounts.operation.managers.FinancialAccountOperationManager;
import com.pedistack.accounts.v1_0.common.FinancialAccount;
import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Accounts"})
@RequestMapping("v1")
@RestController
public class FinancialAccountOperations {

  private final FinancialAccountOperationManager financialAccountOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public FinancialAccountOperations(
      FinancialAccountOperationManager financialAccountOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.financialAccountOperationManager = financialAccountOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get list of user accounts with mobile number")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/user/msisdn/{msisdn}/accounts")
  public ResponseEntity<GenericResponse<List<FinancialAccount>>> msisdnAccounts(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("msisdn") String msisdn)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_USER_ACCOUNTS_PERMISSION);
    final List<FinancialAccount> financialAccounts =
        financialAccountOperationManager.userAccounts(
            tenant,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            msisdn,
            null);
    return ResponseEntity.ok(
        GenericResponse.createResponse(financialAccounts, "Accounts listed successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get list of user accounts with email address")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "/user/email/{emailAddress}/accounts")
  public ResponseEntity<GenericResponse<List<FinancialAccount>>> emailAddressAccounts(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("emailAddress") String emailAddress)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_USER_ACCOUNTS_PERMISSION);
    final List<FinancialAccount> financialAccounts =
        financialAccountOperationManager.userAccounts(
            tenant,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            emailAddress,
            null,
            null);
    return ResponseEntity.ok(
        GenericResponse.createResponse(financialAccounts, "Accounts listed successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get list of user accounts with username")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "/user/username/{username}/accounts")
  public ResponseEntity<GenericResponse<List<FinancialAccount>>> usernameAccounts(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("username") String username)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_USER_ACCOUNTS_PERMISSION);
    final List<FinancialAccount> financialAccounts =
        financialAccountOperationManager.userAccounts(
            tenant,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            null,
            null,
            username);
    return ResponseEntity.ok(
        GenericResponse.createResponse(financialAccounts, "Accounts listed successfully"));
  }
}
