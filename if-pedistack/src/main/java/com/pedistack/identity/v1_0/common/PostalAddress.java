package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.v1_0.common.CountryCode;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PostalAddress implements Serializable {

  private String id;
  private String addressLine;

  private String streetName;
  private String buildingNumber;
  private String postCode;
  private String town;
  private String province;
  private CountryCode countryCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
}
