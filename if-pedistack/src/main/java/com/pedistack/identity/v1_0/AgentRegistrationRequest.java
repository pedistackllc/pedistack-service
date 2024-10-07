package com.pedistack.identity.v1_0;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.identity.v1_0.common.BusinessType;
import com.pedistack.identity.v1_0.common.GenderCode;
import com.pedistack.identity.v1_0.common.Industry;
import com.pedistack.identity.v1_0.common.RegistrationType;
import com.pedistack.v1_0.common.CountryCode;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AgentRegistrationRequest implements Serializable {

  private String msisdn;
  private String emailAddress;
  private String username;
  private String password;

  private String firstName;
  private String lastName;
  private GenderCode gender;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date birthDate;

  private String businessName;
  private String businessTradingName;
  private BusinessType businessType;
  private Industry industry;

  private RegistrationType registrationType;
  private String registrationNumber;
  private String taxIdentificationNumber;
  private String website;
  private CountryCode residentCountryCode;

  private String addressLine;
  private String streetName;
  private String buildingNumber;
  private String postCode;
  private String town;
  private String province;
  private CountryCode countryCode;

  private String parentAgentMsisdn;
  private String parentAgentUsername;
  private String parentAgentEmailAddress;

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

  public GenderCode getGender() {
    return gender;
  }

  public void setGender(GenderCode gender) {
    this.gender = gender;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getBusinessTradingName() {
    return businessTradingName;
  }

  public void setBusinessTradingName(String businessTradingName) {
    this.businessTradingName = businessTradingName;
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

  public CountryCode getResidentCountryCode() {
    return residentCountryCode;
  }

  public void setResidentCountryCode(CountryCode residentCountryCode) {
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

  public CountryCode getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(CountryCode countryCode) {
    this.countryCode = countryCode;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getParentAgentMsisdn() {
    return parentAgentMsisdn;
  }

  public void setParentAgentMsisdn(String parentAgentMsisdn) {
    this.parentAgentMsisdn = parentAgentMsisdn;
  }

  public String getParentAgentUsername() {
    return parentAgentUsername;
  }

  public void setParentAgentUsername(String parentAgentUsername) {
    this.parentAgentUsername = parentAgentUsername;
  }

  public String getParentAgentEmailAddress() {
    return parentAgentEmailAddress;
  }

  public void setParentAgentEmailAddress(String parentAgentEmailAddress) {
    this.parentAgentEmailAddress = parentAgentEmailAddress;
  }
}
