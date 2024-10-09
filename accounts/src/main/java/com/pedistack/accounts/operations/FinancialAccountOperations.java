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
      summary = "Register a new financial account")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account")
  public ResponseEntity<GenericResponse<List<FinancialAccount>>> addAccount(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody List<String> currencyCodes)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.ADD_USER_ACCOUNT_PERMISSION);
    final List<FinancialAccount> financialAccounts =
        financialAccountOperationManager.addUserAccount(
            tenant,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            currencyCodes);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            financialAccounts, "Financial account registered successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get list of user accounts of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/accounts")
  public ResponseEntity<GenericResponse<List<FinancialAccount>>> msisdnAccounts(
      @RequestHeader("X-Tenant-Name") String tenant, @RequestHeader HttpHeaders httpHeaders)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_USER_ACCOUNTS_PERMISSION);
    final List<FinancialAccount> financialAccounts =
        financialAccountOperationManager.userAccounts(
            tenant,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(financialAccounts, "Accounts listed successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Close the financial account with an identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/account/{id}/close")
  public ResponseEntity<GenericResponse<Void>> closeFinancialAccount(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String accountIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CLOSE_USER_ACCOUNT_PERMISSION);
    financialAccountOperationManager.closeFinancialAccount(
        tenant,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        accountIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Accounts closed successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Block the financial account with an identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/account/{id}/block")
  public ResponseEntity<GenericResponse<Void>> blockFinancialAccount(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String accountIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.BLOCK_USER_ACCOUNT_PERMISSION);
    financialAccountOperationManager.blockFinancialAccount(
        tenant,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        accountIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Accounts blocked successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Unblock the financial account with an identifier")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/account/{id}/block")
  public ResponseEntity<GenericResponse<Void>> unblockFinancialAccount(
      @RequestHeader("X-Tenant-Name") String tenant,
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String accountIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UNBLOCK_USER_ACCOUNT_PERMISSION);
    financialAccountOperationManager.unblockFinancialAccount(
        tenant,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        accountIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Accounts unblocked successfully"));
  }
}
