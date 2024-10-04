package com.pedistack.accounts.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.accounts.v1_0.common.FinancialAccountProviderType;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FinancialAccountProviderRequest implements Serializable {

  private String name;
  private FinancialAccountProviderType type;
  private String description;
  private String code;
  private String countryCode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FinancialAccountProviderType getType() {
    return type;
  }

  public void setType(FinancialAccountProviderType type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}
