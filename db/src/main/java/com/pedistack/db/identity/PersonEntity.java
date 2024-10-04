package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(schema = "ident", name = "personal_identities")
public final class PersonEntity extends BaseEntity {

  private String title;
  private String suffix;
  private String firstName;
  private String middleName;
  private String otherNames;
  private String lastName;
  private String maidenName;
  private String gender;
  private String maritalStatus;
  private Date birthDate;
  private String birthProvince;
  private String birthCountryCode;
  private String languageCode;
  private String profession;
  private String employingCompany;
  private String taxationCountryCode;
  private String businessFunction;
  @ManyToOne private UserEntity user;

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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String maritalStatus) {
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

  public String getBirthCountryCode() {
    return birthCountryCode;
  }

  public void setBirthCountryCode(String birthCountryCode) {
    this.birthCountryCode = birthCountryCode;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
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

  public String getTaxationCountryCode() {
    return taxationCountryCode;
  }

  public void setTaxationCountryCode(String taxationCountryCode) {
    this.taxationCountryCode = taxationCountryCode;
  }

  public String getBusinessFunction() {
    return businessFunction;
  }

  public void setBusinessFunction(String businessFunction) {
    this.businessFunction = businessFunction;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
