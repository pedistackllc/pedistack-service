package com.pedistack.oauth.v1_0.operations;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.GenericResponse;
import com.pedistack.oauth.operation.managers.OauthOperationManager;
import com.pedistack.oauth.v1_0.OauthRequest;
import com.pedistack.oauth.v1_0.OauthResponse;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Oauth"})
@RequestMapping("v1")
@RestController
public class OauthOperations {

  private final OauthOperationManager oauthOperationManager;

  public OauthOperations(OauthOperationManager oauthOperationManager) {
    this.oauthOperationManager = oauthOperationManager;
  }

  @Operation(
      tags = {"Oauth"},
      summary = "Create a new access token")
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      value = "token")
  public ResponseEntity<GenericResponse<OauthResponse>> createAccessToken(
      @RequestBody OauthRequest oauthRequest) throws PedistackException {
    return ResponseEntity.ok(
        GenericResponse.createResponse(
            oauthOperationManager.authorize(oauthRequest), "Access token generated successfully"));
  }
}
