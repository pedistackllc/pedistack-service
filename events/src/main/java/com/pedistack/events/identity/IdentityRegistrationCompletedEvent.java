package com.pedistack.events.identity;

import com.pedistack.events.base.BaseApplicationEvent;
import com.pedistack.identity.v1_0.IdentityRequest;

public final class IdentityRegistrationCompletedEvent extends BaseApplicationEvent {

  private final String internalIdentity;
  private final IdentityRequest identityRequest;

  public IdentityRegistrationCompletedEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String internalIdentity,
      IdentityRequest identityRequest) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.internalIdentity = internalIdentity;
    this.identityRequest = identityRequest;
  }

  public String getInternalIdentity() {
    return internalIdentity;
  }

  public IdentityRequest getIdentityRegistrationRequest() {
    return identityRequest;
  }
}
