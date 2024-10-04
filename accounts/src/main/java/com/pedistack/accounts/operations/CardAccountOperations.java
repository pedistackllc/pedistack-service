package com.pedistack.accounts.operations;

import com.pedistack.accounts.operation.managers.CardAccountOperationManager;
import com.pedistack.accounts.v1_0.CardAccountRequest;
import com.pedistack.accounts.v1_0.common.CardAccount;
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
public class CardAccountOperations {

  private final CardAccountOperationManager cardAccountOperationManager;
  private final AuthorizationOperationManager authorizationOperationManager;

  public CardAccountOperations(
      CardAccountOperationManager cardAccountOperationManager,
      AuthorizationOperationManager authorizationOperationManager) {
    this.cardAccountOperationManager = cardAccountOperationManager;
    this.authorizationOperationManager = authorizationOperationManager;
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Register a new card")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "account/card")
  public ResponseEntity<GenericResponse<CardAccount>> create(
      @RequestHeader HttpHeaders httpHeaders, @RequestBody CardAccountRequest cardAccountRequest)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.CREATE_CARD_ACCOUNT_PERMISSION);
    final CardAccount cardAccount =
        cardAccountOperationManager.create(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            cardAccountRequest);
    return ResponseEntity.ok(
        GenericResponse.createResponse(cardAccount, "Card account registered successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get card information")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/card/{id}")
  public ResponseEntity<GenericResponse<CardAccount>> get(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String cardIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_CARD_ACCOUNT_PERMISSION);
    final CardAccount cardAccount =
        cardAccountOperationManager.get(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders),
            cardIdentifier);
    return ResponseEntity.ok(
        GenericResponse.createResponse(cardAccount, "Card account fetched successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Get the list of cards of an authorized user")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/cards")
  public ResponseEntity<GenericResponse<List<CardAccount>>> list(
      @RequestHeader HttpHeaders httpHeaders) throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.GET_CARD_ACCOUNTS_PERMISSION);
    final List<CardAccount> cardAccounts =
        cardAccountOperationManager.userCardAccounts(
            null,
            authorizationOperationManager.sessionUserIdentifier(httpHeaders),
            authorizationOperationManager.sessionReference(httpHeaders));
    return ResponseEntity.ok(
        GenericResponse.createResponse(cardAccounts, "Card accounts fetched successfully"));
  }

  @Operation(
      tags = {"Accounts"},
      summary = "Delete card information")
  @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "account/card/{id}")
  public ResponseEntity<GenericResponse<CardAccount>> delete(
      @RequestHeader HttpHeaders httpHeaders, @PathVariable("id") String cardIdentifier)
      throws PedistackException {
    authorizationOperationManager.validateAuthorizationPermissions(
        httpHeaders, AuthorizationPermissions.DELETE_CARD_ACCOUNT_PERMISSION);
    cardAccountOperationManager.delete(
        null,
        authorizationOperationManager.sessionUserIdentifier(httpHeaders),
        authorizationOperationManager.sessionReference(httpHeaders),
        cardIdentifier);
    return ResponseEntity.ok(GenericResponse.createResponse("Card account removed successfully"));
  }
}
