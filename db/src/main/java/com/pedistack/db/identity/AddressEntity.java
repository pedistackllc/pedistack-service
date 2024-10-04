package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "ident", name = "address_identities")
public final class AddressEntity extends BaseEntity {

  @Column(nullable = false)
  private String addressType;

  @Column(columnDefinition = "TEXT")
  private String addressLine;

  private String streetName;
  private String buildingNumber;
  private String postCode;
  private String town;
  private String province;
  private String countryCode;

  private String emailAddress;
  private String phoneNumber;
  private String mobileNumber;
  private String faxNumber;
  private String telexNumber;
  private String urlAddress;

  @ManyToOne private UserEntity user;

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
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

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
