package com.pedistack.events.identity;

import com.pedistack.common.identity.IdentityStatus;
import com.pedistack.events.base.BaseApplicationEvent;

public final class IdentityActivationEvent extends BaseApplicationEvent {

  private final String userIdentifier;
  private final IdentityStatus identityStatus;

  public IdentityActivationEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      IdentityStatus identityStatus) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.userIdentifier = userIdentifier;
    this.identityStatus = identityStatus;
  }

  public String getUserIdentifier() {
    return userIdentifier;
  }

  public IdentityStatus getIdentityStatus() {
    return identityStatus;
  }
}
