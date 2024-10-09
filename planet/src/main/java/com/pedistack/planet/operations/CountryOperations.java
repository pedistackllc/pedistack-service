package com.pedistack.planet.operations;

import com.pedistack.common.authorization.AuthorizationPermissions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.common.io.PageResponse;
import com.pedistack.oauth.operation.managers.AuthorizationOperationManager;
import com.pedistack.planet.operation.managers.CountryOperationManager;
import com.pedistack.planet.v1_0.CountryRequest;
import com.pedistack.planet.v1_0.common.Country;
import com.pedistack.v1_0.common.LanguageCode;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Planet"})
@RestController
public class CountryOperations {

  private final CountryOperationManager countryOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public CountryOperations(
      CountryOperationManager countryOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.countryOperationManager = countryOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Planet"},
      summary = "Create a new country")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "country")
  public ResponseEntity<GenericResponse<Country>> create(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody CountryRequest countryRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            countryRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country registered successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get country information by identifier")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "country/{id}")
  public ResponseEntity<GenericResponse<Country>> countryIdentifier(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String identifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            identifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get country information by alpha2Code")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "country/alpha2Code/{alpha2Code}")
  public ResponseEntity<GenericResponse<Country>> countryAlpha2Code(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("alpha2Code") String alpha2Code)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.findByAlpha2Code(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            alpha2Code);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get country information by alpha3Code")
  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      value = "country/alpha3Code/{alpha3Code}")
  public ResponseEntity<GenericResponse<Country>> countryAlpha3Code(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("alpha3Code") String alpha3Code)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.findByAlpha3Code(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            alpha3Code);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country fetched successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Get list of available countries")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "countries")
  public ResponseEntity<GenericResponse<PageResponse<Country>>> countryIdentifier(
      @RequestHeader HttpHeaders httpHeaders,
      @RequestParam("page") int page,
      @RequestParam("size") int size)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_COUNTRIES_PERMISSION);
    final PageResponse<Country> countryPageResponse =
        countryOperationManager.list(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            page,
            size);
    return ResponseEntity.ok(
        GenericResponse.createResponse(countryPageResponse, "Countries listed successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Create a new country")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "country/{id}")
  public ResponseEntity<GenericResponse<Country>> update(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String countryIdentifier,
      @RequestBody CountryRequest countryRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.update(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            countryIdentifier,
            countryRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country updated successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Delete country information by identifier")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "country/{id}")
  public ResponseEntity<GenericResponse<Country>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String identifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_COUNTRY_PERMISSION);
    countryOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        identifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Country removed successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Update the supported currencies of a country")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "country/{id}/currency")
  public ResponseEntity<GenericResponse<Country>> updateSupportedCurrencies(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String identifier,
      @RequestParam("status") boolean status,
      @RequestBody List<String> currencyCodes)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.updateSupportedCurrencies(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            identifier,
            currencyCodes,
            status);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country updated successfully"));
  }

  @Operation(
      tags = {"Planet"},
      summary = "Update the supported languages of a country")
  @PutMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "country/{id}/language")
  public ResponseEntity<GenericResponse<Country>> updateSupportedLanguages(
      @RequestHeader HttpHeaders httpHeaders,
      @PathVariable("id") String identifier,
      @RequestParam("status") boolean status,
      @RequestBody List<LanguageCode> languageCodes)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.UPDATE_COUNTRY_PERMISSION);
    final Country country =
        countryOperationManager.updateSupportedLanguageCodes(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            identifier,
            languageCodes,
            status);
    return ResponseEntity.ok(
        GenericResponse.createResponse(country, "Country updated successfully"));
  }
}
