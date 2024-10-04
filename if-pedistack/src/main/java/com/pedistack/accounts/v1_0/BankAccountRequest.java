package com.pedistack.accounts.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BankAccountRequest implements Serializable {

  private String name;
  private String accountNumber;
  private String description;
  private String accountProviderCode;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAccountProviderCode() {
    return accountProviderCode;
  }

  public void setAccountProviderCode(String accountProviderCode) {
    this.accountProviderCode = accountProviderCode;
  }
}
