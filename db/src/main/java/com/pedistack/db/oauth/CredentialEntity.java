package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.common.db.PersistenceEncryption;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "oauth", name = "credentials")
public final class CredentialEntity extends BaseEntity {

  @Column(nullable = false)
  @Convert(converter = PersistenceEncryption.class)
  private String credential;

  @Column(nullable = false)
  private String type;

  @Column(unique = true)
  private String credentialReference;

  @Column(nullable = false)
  private String status;

  @ManyToOne private UserEntity user;

  @Temporal(TemporalType.TIMESTAMP)
  private Date expiryDate;

  public String getCredential() {
    return credential;
  }

  public void setCredential(String credential) {
    this.credential = credential;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCredentialReference() {
    return credentialReference;
  }

  public void setCredentialReference(String credentialReference) {
    this.credentialReference = credentialReference;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
