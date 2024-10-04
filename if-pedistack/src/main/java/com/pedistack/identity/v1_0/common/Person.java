package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.v1_0.common.CountryCode;
import com.pedistack.v1_0.common.LanguageCode;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Person implements Serializable {

  private String title;
  private String suffix;
  private String firstName;
  private String middleName;
  private String otherNames;
  private String lastName;
  private String maidenName;
  private GenderCode gender;
  private MaritalStatus maritalStatus;
  private Date birthDate;
  private String birthProvince;
  private CountryCode birthCountryCode;
  private LanguageCode languageCode;
  private String profession;
  private String employingCompany;
  private CountryCode taxationCountryCode;
  private String businessFunction;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getOtherNames() {
    return otherNames;
  }

  public void setOtherNames(String otherNames) {
    this.otherNames = otherNames;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMaidenName() {
    return maidenName;
  }

  public void setMaidenName(String maidenName) {
    this.maidenName = maidenName;
  }

  public GenderCode getGender() {
    return gender;
  }

  public void setGender(GenderCode gender) {
    this.gender = gender;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getBirthProvince() {
    return birthProvince;
  }

  public void setBirthProvince(String birthProvince) {
    this.birthProvince = birthProvince;
  }

  public CountryCode getBirthCountryCode() {
    return birthCountryCode;
  }

  public void setBirthCountryCode(CountryCode birthCountryCode) {
    this.birthCountryCode = birthCountryCode;
  }

  public LanguageCode getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(LanguageCode languageCode) {
    this.languageCode = languageCode;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getEmployingCompany() {
    return employingCompany;
  }

  public void setEmployingCompany(String employingCompany) {
    this.employingCompany = employingCompany;
  }

  public CountryCode getTaxationCountryCode() {
    return taxationCountryCode;
  }

  public void setTaxationCountryCode(CountryCode taxationCountryCode) {
    this.taxationCountryCode = taxationCountryCode;
  }

  public String getBusinessFunction() {
    return businessFunction;
  }

  public void setBusinessFunction(String businessFunction) {
    this.businessFunction = businessFunction;
  }
}
