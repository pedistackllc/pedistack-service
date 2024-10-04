package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "accounts", name = "bank_accounts")
public final class BankAccountEntity extends BaseEntity {

  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private String accountNumber;

  @ManyToOne private FinancialAccountProviderEntity accountProvider;

  @Column(nullable = false)
  private String status;

  @ManyToOne private UserEntity user;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public FinancialAccountProviderEntity getAccountProvider() {
    return accountProvider;
  }

  public void setAccountProvider(FinancialAccountProviderEntity accountProvider) {
    this.accountProvider = accountProvider;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
