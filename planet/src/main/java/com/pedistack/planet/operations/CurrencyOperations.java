package com.pedistack.planet.operations;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.common.io.PageResponse;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import com.pedistack.planet.operation.managers.CurrencyOperationManager;
import com.pedistack.planet.v1_0.CurrencyRequest;
import com.pedistack.planet.v1_0.common.Currency;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Planet"})
@RestController
public class CurrencyOperations {

  private final CurrencyOperationManager currencyOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public CurrencyOperations(
      CurrencyOperationManager currencyOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.currencyOperationManager = currencyOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Planet"},
      summary = "Create a new currency")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "currency")
  public ResponseEntity<GenericResponse<Currency>> create(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody CurrencyRequest currencyRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_CURRENCY_PERMISSION);
    final Currency currency =
        currencyOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            currencyRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(currency, "Currency created successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get the currency information by identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "currency/{id}")
  public ResponseEntity<GenericResponse<Currency>> get(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String currencyIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_CURRENCY_PERMISSION);
    final Currency currency =
        currencyOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            currencyIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(currency, "Currency fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get the currency information by code")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "currency/code/{code}")
  public ResponseEntity<GenericResponse<Currency>> findByCode(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("code") String currencyCode)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_CURRENCY_PERMISSION);
    final Currency currency =
        currencyOperationManager.findByCode(
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            currencyCode);
    return ResponseEntity.ok(
        GenericResponse.createResponse(currency, "Currency fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get the list of supported currencies")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "currencies")
  public ResponseEntity<GenericResponse<PageResponse<Currency>>> list(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam("page") int page,
      @RequestParam("size") int size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_CURRENCIES_PERMISSION);
    final PageResponse<Currency> currencyPageResponse =
        currencyOperationManager.list(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            page,
            size);
    return ResponseEntity.ok(
        GenericResponse.createResponse(currencyPageResponse, "Currencies fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Update currency information")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "currency/{id}")
  public ResponseEntity<GenericResponse<Currency>> create(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String currencyIdentifier,
      @RequestBody CurrencyRequest currencyRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_CURRENCY_PERMISSION);
    final Currency currency =
        currencyOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            currencyIdentifier,
            currencyRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(currency, "Currency updated successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Delete the currency information by identifier")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "currency/{id}")
  public ResponseEntity<GenericResponse<Void>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String currencyIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_CURRENCY_PERMISSION);
    currencyOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        currencyIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Currency removed successfully"));
  }
}
