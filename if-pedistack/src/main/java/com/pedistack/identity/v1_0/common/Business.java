package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.v1_0.common.CountryCode;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Business implements Serializable {

  private String name;
  private String tradingName;
  private BusinessType businessType;
  private Industry industry;
  private PostalAddress postalAddress;
  private CommunicationAddress communicationAddress;
  private RegistrationType registrationType;
  private String registrationNumber;
  private String taxIdentificationNumber;
  private String website;
  private CountryCode residentCountryCode;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
  private Date registrationDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTradingName() {
    return tradingName;
  }

  public void setTradingName(String tradingName) {
    this.tradingName = tradingName;
  }

  public BusinessType getBusinessType() {
    return businessType;
  }

  public void setBusinessType(BusinessType businessType) {
    this.businessType = businessType;
  }

  public Industry getIndustry() {
    return industry;
  }

  public void setIndustry(Industry industry) {
    this.industry = industry;
  }

  public PostalAddress getPostalAddress() {
    return postalAddress;
  }

  public void setPostalAddress(PostalAddress postalAddress) {
    this.postalAddress = postalAddress;
  }

  public CommunicationAddress getCommunicationAddress() {
    return communicationAddress;
  }

  public void setCommunicationAddress(CommunicationAddress communicationAddress) {
    this.communicationAddress = communicationAddress;
  }

  public RegistrationType getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(RegistrationType registrationType) {
    this.registrationType = registrationType;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public String getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  public void setTaxIdentificationNumber(String taxIdentificationNumber) {
    this.taxIdentificationNumber = taxIdentificationNumber;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public CountryCode getResidentCountryCode() {
    return residentCountryCode;
  }

  public void setResidentCountryCode(CountryCode residentCountryCode) {
    this.residentCountryCode = residentCountryCode;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }
}
