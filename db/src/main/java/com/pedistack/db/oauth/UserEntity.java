package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseEntity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "oauth", name = "users")
public final class UserEntity extends BaseEntity {

  @Column(unique = true)
  private String emailAddress;

  @Column(unique = true)
  private String mobileNumber;

  @Column(unique = true)
  private String username;

  @Column(nullable = false)
  private String status;

  @Column(unique = true)
  private String clientId;

  @Temporal(TemporalType.DATE)
  private Date lastLoginDate;

  private Integer loginAttempts;

  private Boolean twoFactorAuthenticationStatus;

  @Column(nullable = false)
  private boolean emailVerificationStatus;

  @Column(nullable = false)
  private boolean msisdnVerificationStatus;

  @ManyToOne private ProfileEntity profile;

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public Integer getLoginAttempts() {
    return loginAttempts;
  }

  public void setLoginAttempts(Integer loginAttempts) {
    this.loginAttempts = loginAttempts;
  }

  public Boolean getTwoFactorAuthenticationStatus() {
    return twoFactorAuthenticationStatus;
  }

  public void setTwoFactorAuthenticationStatus(Boolean twoFactorAuthenticationStatus) {
    this.twoFactorAuthenticationStatus = twoFactorAuthenticationStatus;
  }

  public Boolean getEmailVerificationStatus() {
    return emailVerificationStatus;
  }

  public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
  }

  public boolean getMsisdnVerificationStatus() {
    return msisdnVerificationStatus;
  }

  public void setMsisdnVerificationStatus(boolean msisdnVerificationStatus) {
    this.msisdnVerificationStatus = msisdnVerificationStatus;
  }

  public ProfileEntity getProfile() {
    return profile;
  }

  public void setProfile(ProfileEntity profile) {
    this.profile = profile;
  }
}
