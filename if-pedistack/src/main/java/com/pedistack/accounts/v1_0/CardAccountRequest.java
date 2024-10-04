package com.pedistack.accounts.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.accounts.v1_0.common.CardAccountType;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CardAccountRequest implements Serializable {

  private String name;
  private String token;
  private String last4;
  private CardAccountType type;
  private String bin;
  private String bank;
  private int month;
  private int year;
  private String signature;
  private String countryCode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getLast4() {
    return last4;
  }

  public void setLast4(String last4) {
    this.last4 = last4;
  }

  public CardAccountType getType() {
    return type;
  }

  public void setType(CardAccountType type) {
    this.type = type;
  }

  public String getBin() {
    return bin;
  }

  public void setBin(String bin) {
    this.bin = bin;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
}
