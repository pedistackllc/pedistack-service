package com.pedistack.oauth.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.oauth.v1_0.OauthRequest;
import com.pedistack.oauth.v1_0.OauthResponse;

public interface OauthOperationManager {

  OauthResponse authorize(OauthRequest oauthRequest) throws PedistackException;
}
