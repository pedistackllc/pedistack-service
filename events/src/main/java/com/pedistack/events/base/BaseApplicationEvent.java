package com.pedistack.events.base;

import org.springframework.context.ApplicationEvent;

public class BaseApplicationEvent extends ApplicationEvent {

  private final String tenant;
  private final String sessionUserIdentifier;
  private final String sessionReference;

  public BaseApplicationEvent(
      Object source, String tenant, String sessionUserIdentifier, String sessionReference) {
    super(source);
    this.tenant = tenant;
    this.sessionUserIdentifier = sessionUserIdentifier;
    this.sessionReference = sessionReference;
  }

  public String getTenant() {
    return tenant;
  }

  public String getSessionUserIdentifier() {
    return sessionUserIdentifier;
  }

  public String getSessionReference() {
    return sessionReference;
  }
}
