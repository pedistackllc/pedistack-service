package com.pedistack.accounts.operations;

import com.pedistack.accounts.operation.managers.FinancialAccountProviderOperationManager;
import com.pedistack.accounts.v1_0.FinancialAccountProviderRequest;
import com.pedistack.accounts.v1_0.common.FinancialAccountProvider;
import com.pedistack.accounts.v1_0.common.FinancialAccountProviderType;
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
public class FinancialAccountProviderOperations {

  private final FinancialAccountProviderOperationManager financialAccountProviderOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public FinancialAccountProviderOperations(
      FinancialAccountProviderOperationManager financialAccountProviderOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.financialAccountProviderOperationManager = financialAccountProviderOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Create a new financial provider")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account/provider")
  public ResponseEntity<GenericResponse<FinancialAccountProvider>> create(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestBody FinancialAccountProviderRequest financialAccountProviderRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_FINANCIAL_ACCOUNT_PROVIDER_PERMISSION);
    final FinancialAccountProvider financialAccountProvider =
        financialAccountProviderOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            financialAccountProviderRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            financialAccountProvider, "Financial account provider created successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Fetch the account provider information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/provider/{id}")
  public ResponseEntity<GenericResponse<FinancialAccountProvider>> get(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String financialAccountProviderIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_FINANCIAL_ACCOUNT_PROVIDER_PERMISSION);
    final FinancialAccountProvider financialAccountProvider =
        financialAccountProviderOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            financialAccountProviderIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            financialAccountProvider, "Financial account provider fetched successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Fetch the list account provider information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/providers")
  public ResponseEntity<GenericResponse<List<FinancialAccountProvider>>> list(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam(value = "countryCode", required = false) String countryCode,
      @RequestParam(value = "type", required = false)
          FinancialAccountProviderType financialAccountProviderType,
      @RequestParam("page") int page,
      @RequestParam("size") int size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_FINANCIAL_ACCOUNT_PROVIDERS_PERMISSION);
    List<FinancialAccountProvider> financialAccountProviders;
    if (countryCode != null && financialAccountProviderType != null) {
      financialAccountProviders =
          financialAccountProviderOperationManager.listByFinancialAccountProviderType(
              null,
              authorizationOperationManager.sessionUserIdentifier(httpHeaders),
              authorizationOperationManager.sessionReference(httpHeaders),
              financialAccountProviderType,
              countryCode);
    } else if (countryCode != null) {
      financialAccountProviders =
          financialAccountProviderOperationManager.listByFinancialAccountProviderType(
              null,
              authorizationOperationManager.sessionUserIdentifier(httpHeaders),
              authorizationOperationManager.sessionReference(httpHeaders),
              countryCode);
    } else {
      financialAccountProviders =
          financialAccountProviderOperationManager
              .list(
                  null,
                  authorizationOperationManager.sessionUserIdentifier(httpHeaders),
                  authorizationOperationManager.sessionReference(httpHeaders),
                  page,
                  size)
              .getPageContents();
    }
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            financialAccountProviders, "Financial account providers listed successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Update financial provider information")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account/provider/{id}")
  public ResponseEntity<GenericResponse<FinancialAccountProvider>> update(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String identifier,
      @RequestBody FinancialAccountProviderRequest financialAccountProviderRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_FINANCIAL_ACCOUNT_PROVIDER_PERMISSION);
    final FinancialAccountProvider financialAccountProvider =
        financialAccountProviderOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            identifier,
            financialAccountProviderRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            financialAccountProvider, "Financial account provider updated successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Delete the account provider information")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/provider/{id}")
  public ResponseEntity<GenericResponse<Void>> delete(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String financialAccountProviderIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_FINANCIAL_ACCOUNT_PROVIDER_PERMISSION);
    financialAccountProviderOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        financialAccountProviderIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse("Financial account provider removed successfully"));
  }
}
