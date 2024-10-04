package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "ident", name = "business_identities")
public final class BusinessEntity extends BaseEntity {

  @Column(unique = true)
  private String name;

  @Column(unique = true)
  private String tradingName;

  @ManyToOne private AddressEntity postalAddress;
  @ManyToOne private AddressEntity communicationAddress;
  private String businessType;
  private String industry;
  private String registrationType;

  @Column(unique = true)
  private String registrationNumber;

  private String taxIdentificationNumber;
  private String website;
  private String residentCountryCode;

  private String logoUrl;

  @Temporal(TemporalType.DATE)
  private Date registrationDate;

  @Column(nullable = false)
  private String verificationStatus;

  @ManyToOne private UserEntity user;

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

  public AddressEntity getPostalAddress() {
    return postalAddress;
  }

  public void setPostalAddress(AddressEntity address) {
    this.postalAddress = address;
  }

  public AddressEntity getCommunicationAddress() {
    return communicationAddress;
  }

  public void setCommunicationAddress(AddressEntity communicationAddress) {
    this.communicationAddress = communicationAddress;
  }

  public String getBusinessType() {
    return businessType;
  }

  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
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

  public String getLogoUrl() {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl) {
    this.logoUrl = logoUrl;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getVerificationStatus() {
    return verificationStatus;
  }

  public void setVerificationStatus(String verificationStatus) {
    this.verificationStatus = verificationStatus;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
