package com.pedistack.accounts.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FinancialAccount implements Serializable {

  private String id;
  private String name;
  private String description;
  private FinancialAccountType type;
  private String accountNumber;
  private BigDecimal availableBalance;
  private BigDecimal reservedBalance;
  private BigDecimal committedBalance;
  private FinancialAccountStatus status;
  private String currencyCode;
  private String externalAccountNumber;
  private String externalAccountProviderName;
  private String externalAccountProviderCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public FinancialAccountType getType() {
    return type;
  }

  public void setType(FinancialAccountType type) {
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

  public FinancialAccountStatus getStatus() {
    return status;
  }

  public void setStatus(FinancialAccountStatus status) {
    this.status = status;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getExternalAccountNumber() {
    return externalAccountNumber;
  }

  public void setExternalAccountNumber(String externalAccountNumber) {
    this.externalAccountNumber = externalAccountNumber;
  }

  public String getExternalAccountProviderName() {
    return externalAccountProviderName;
  }

  public void setExternalAccountProviderName(String externalAccountProviderName) {
    this.externalAccountProviderName = externalAccountProviderName;
  }

  public String getExternalAccountProviderCode() {
    return externalAccountProviderCode;
  }

  public void setExternalAccountProviderCode(String externalAccountProviderCode) {
    this.externalAccountProviderCode = externalAccountProviderCode;
  }
}
