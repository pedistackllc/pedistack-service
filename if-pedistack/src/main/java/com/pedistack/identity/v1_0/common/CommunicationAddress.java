package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CommunicationAddress implements Serializable {

  private String emailAddress;
  private String phoneNumber;
  private String mobileNumber;
  private String faxNumber;
  private String telexNumber;
  private String urlAddress;

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getFaxNumber() {
    return faxNumber;
  }

  public void setFaxNumber(String faxNumber) {
    this.faxNumber = faxNumber;
  }

  public String getTelexNumber() {
    return telexNumber;
  }

  public void setTelexNumber(String telexNumber) {
    this.telexNumber = telexNumber;
  }

  public String getUrlAddress() {
    return urlAddress;
  }

  public void setUrlAddress(String urlAddress) {
    this.urlAddress = urlAddress;
  }
}
