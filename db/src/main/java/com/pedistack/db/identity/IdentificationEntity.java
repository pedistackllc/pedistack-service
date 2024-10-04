package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "ident", name = "identification_identities")
public final class IdentificationEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String identificationNumber;

  private String issuer;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String issuingCountryCode;

  @Temporal(TemporalType.DATE)
  private Date issueDate;

  @Temporal(TemporalType.DATE)
  private Date expiryDate;

  @Column(nullable = false)
  private String verificationStatus;

  @ManyToOne private UserEntity user;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getIssuingCountryCode() {
    return issuingCountryCode;
  }

  public void setIssuingCountryCode(String issuingCountryCode) {
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
