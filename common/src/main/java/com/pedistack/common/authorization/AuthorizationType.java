package com.pedistack.common.authorization;

public enum AuthorizationType {
  BEARER("Bearer"),
  BASIC("Basic");

  private final String authorizationType;

  AuthorizationType(String authorizationType) {
    this.authorizationType = authorizationType;
  }

  public String getAuthorizationType() {
    return authorizationType;
  }
}
