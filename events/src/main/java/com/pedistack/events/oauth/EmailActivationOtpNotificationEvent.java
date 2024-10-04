package com.pedistack.events.oauth;

import com.pedistack.events.base.BaseApplicationEvent;

public final class EmailActivationOtpNotificationEvent extends BaseApplicationEvent {

  private final String emailAddress;
  private final String activationOtp;

  public EmailActivationOtpNotificationEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String activationOtp) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.emailAddress = emailAddress;
    this.activationOtp = activationOtp;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getActivationOtp() {
    return activationOtp;
  }
}
