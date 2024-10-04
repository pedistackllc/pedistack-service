package com.pedistack.identity.v1_0;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.identity.v1_0.common.BusinessType;
import com.pedistack.identity.v1_0.common.Industry;
import com.pedistack.identity.v1_0.common.RegistrationType;
import com.pedistack.v1_0.common.CountryCode;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BusinessRegistrationRequest implements Serializable {

  private String msisdn;
  private String emailAddress;
  private String username;
  private String password;

  private String firstName;
  private String lastName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date birthDate;

  private String name;
  private String tradingName;
  private BusinessType businessType;
  private Industry industry;
  private RegistrationType registrationType;
  private String registrationNumber;
  private String taxIdentificationNumber;
  private String website;
  private String residentCountryCode;

  private String addressLine;
  private String streetName;
  private String buildingNumber;
  private String postCode;
  private String town;
  private String province;
  private String countryCode;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

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

  public String getResidentCountryCode() {
    return residentCountryCode;
  }

  public void setResidentCountryCode(String residentCountryCode) {
    this.residentCountryCode = residentCountryCode;
  }

  public String getAddressLine() {
    return addressLine;
  }

  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getBuildingNumber() {
    return buildingNumber;
  }

  public void setBuildingNumber(String buildingNumber) {
    this.buildingNumber = buildingNumber;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }
}
