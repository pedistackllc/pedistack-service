package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.v1_0.common.CountryCode;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Identification implements Serializable {

  private String id;
  private String identificationNumber;
  private String issuer;
  private IdentificationType type;
  private CountryCode issuingCountryCode;

  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  private Date issueDate;

  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  private Date expiryDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public IdentificationType getType() {
    return type;
  }

  public void setType(IdentificationType type) {
    this.type = type;
  }

  public CountryCode getIssuingCountryCode() {
    return issuingCountryCode;
  }

  public void setIssuingCountryCode(CountryCode issuingCountryCode) {
    this.issuingCountryCode = issuingCountryCode;
  }

  public Date getIssueDate() {
    return issueDate;
  }

  public void setIssueDate(Date issueDate) {
    this.issueDate = issueDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
