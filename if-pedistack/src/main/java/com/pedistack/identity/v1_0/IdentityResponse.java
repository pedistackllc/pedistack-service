package com.pedistack.identity.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.identity.v1_0.common.IdentityStatus;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class IdentityResponse implements Serializable {

  private String internalIdentity;
  private String msisdn;
  private String username;
  private String emailAddress;
  private IdentityStatus status;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public IdentityStatus getStatus() {
    return status;
  }

  public void setStatus(IdentityStatus status) {
    this.status = status;
  }

  public String getInternalIdentity() {
    return internalIdentity;
  }

  public void setInternalIdentity(String internalIdentity) {
    this.internalIdentity = internalIdentity;
  }
}
