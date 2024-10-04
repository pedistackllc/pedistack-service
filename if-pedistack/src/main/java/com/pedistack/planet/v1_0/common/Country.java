package com.pedistack.planet.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pedistack.v1_0.common.LanguageCode;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Country implements Serializable {

  private String id;
  private String name;
  private String alpha2Code;
  private String alpha3Code;
  private List<LanguageCode> languageCodes;
  private List<String> currencyCodes;

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

  public String getAlpha2Code() {
    return alpha2Code;
  }

  public void setAlpha2Code(String alpha2Code) {
    this.alpha2Code = alpha2Code;
  }

  public String getAlpha3Code() {
    return alpha3Code;
  }

  public void setAlpha3Code(String alpha3Code) {
    this.alpha3Code = alpha3Code;
  }

  public List<LanguageCode> getLanguageCodes() {
    return languageCodes;
  }

  public void setLanguageCodes(List<LanguageCode> languageCodes) {
    this.languageCodes = languageCodes;
  }

  public List<String> getCurrencyCodes() {
    return currencyCodes;
  }

  public void setCurrencyCodes(List<String> currencyCodes) {
    this.currencyCodes = currencyCodes;
  }
}
