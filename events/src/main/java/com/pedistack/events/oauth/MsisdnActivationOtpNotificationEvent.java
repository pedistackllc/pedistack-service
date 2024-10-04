package com.pedistack.events.oauth;

import com.pedistack.events.base.BaseApplicationEvent;

public final class MsisdnActivationOtpNotificationEvent extends BaseApplicationEvent {

  private final String mobileNumber;
  private final String activationOtp;

  public MsisdnActivationOtpNotificationEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String activationOtp) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.mobileNumber = emailAddress;
    this.activationOtp = activationOtp;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getActivationOtp() {
    return activationOtp;
  }
}
