package com.pedistack.accounts.operations;

import com.pedistack.accounts.operation.managers.BankAccountOperationManager;
import com.pedistack.accounts.v1_0.BankAccountRequest;
import com.pedistack.accounts.v1_0.common.BankAccount;
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

@Api(tags = {"Accounts"})
@RestController
@RequestMapping("v1")
public class BankAccountOperations {

  private final BankAccountOperationManager bankAccountOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public BankAccountOperations(
      BankAccountOperationManager bankAccountOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.bankAccountOperationManager = bankAccountOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Add/link a bank account")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account/bank")
  public ResponseEntity<GenericResponse<BankAccount>> addBankAccount(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody BankAccountRequest bankAccountRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_BANK_ACCOUNT_PERMISSION);
    final BankAccount bankAccount =
        bankAccountOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            bankAccountRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(bankAccount, "Bank account registered successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get bank account information by identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/bank/{id}")
  public ResponseEntity<GenericResponse<BankAccount>> get(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String bankAccountIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_BANK_ACCOUNT_PERMISSION);
    final BankAccount bankAccount =
        bankAccountOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            bankAccountIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(bankAccount, "Bank account fetched successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Fetch the list of user bank accounts")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/banks")
  public ResponseEntity<GenericResponse<PageResponse<BankAccount>>> list(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam("page") int page,
      @RequestParam("size") int size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_BANK_ACCOUNTS_PERMISSION);
    final PageResponse<BankAccount> bankAccountPageResponse =
        bankAccountOperationManager.list(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            page,
            size);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            bankAccountPageResponse, "Bank accounts fetched successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Update bank account")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account/bank/{id}")
  public ResponseEntity<GenericResponse<BankAccount>> updateBankAccount(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String bankAccountIdentifier,
      @RequestBody BankAccountRequest bankAccountRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_BANK_ACCOUNT_PERMISSION);
    final BankAccount bankAccount =
        bankAccountOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            bankAccountIdentifier,
            bankAccountRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(bankAccount, "Bank account updated successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Delete bank account information by identifier")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/bank/{id}")
  public ResponseEntity<GenericResponse<Void>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String bankAccountIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_BANK_ACCOUNT_PERMISSION);
    bankAccountOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        bankAccountIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Bank account removed successfully"));
  }
}
