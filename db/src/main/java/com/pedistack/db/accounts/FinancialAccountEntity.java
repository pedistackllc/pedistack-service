package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.planet.CurrencyEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(schema = "accounts", name = "financial_accounts")
public final class FinancialAccountEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false, unique = true)
  private String accountNumber;

  @Column(precision = 32, scale = 2)
  private BigDecimal availableBalance;

  @Column(precision = 32, scale = 2)
  private BigDecimal reservedBalance;

  @Column(precision = 32, scale = 2)
  private BigDecimal committedBalance;

  @Column(nullable = false)
  private String status;

  @ManyToOne private CurrencyEntity currency;

  @ManyToOne private UserEntity user;

  @Column(nullable = false)
  private boolean systemStatus;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastAccessedDateTime;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BigDecimal getAvailableBalance() {
    return availableBalance;
  }

  public void setAvailableBalance(BigDecimal availableBalance) {
    this.availableBalance = availableBalance;
  }

  public BigDecimal getReservedBalance() {
    return reservedBalance;
  }

  public void setReservedBalance(BigDecimal reservedBalance) {
    this.reservedBalance = reservedBalance;
  }

  public BigDecimal getCommittedBalance() {
    return committedBalance;
  }

  public void setCommittedBalance(BigDecimal committedBalance) {
    this.committedBalance = committedBalance;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CurrencyEntity getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEntity currency) {
    this.currency = currency;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public boolean isSystemStatus() {
    return systemStatus;
  }

  public void setSystemStatus(boolean systemStatus) {
    this.systemStatus = systemStatus;
  }

  @Override
  public Date getLastAccessedDateTime() {
    return lastAccessedDateTime;
  }

  @Override
  public void setLastAccessedDateTime(Date lastAccessedDateTime) {
    this.lastAccessedDateTime = lastAccessedDateTime;
  }
}
