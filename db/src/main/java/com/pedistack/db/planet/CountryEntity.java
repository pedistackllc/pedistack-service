package com.pedistack.db.planet;

import com.pedistack.common.db.BaseEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "planet", name = "countries")
public final class CountryEntity extends BaseEntity {

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true)
  private String alpha2Code;

  @Column(unique = true)
  private String alpha3Code;

  @ElementCollection
  @CollectionTable(schema = "planet", name = "country_languages")
  private List<String> languageCodes;

  @ManyToMany
  @JoinTable(schema = "planet", name = "country_currencies")
  private List<CurrencyEntity> currencies;

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

  public List<String> getLanguageCodes() {
    return languageCodes;
  }

  public void setLanguageCodes(List<String> languageCodes) {
    this.languageCodes = languageCodes;
  }

  public List<CurrencyEntity> getCurrencies() {
    return currencies;
  }

  public void setCurrencies(List<CurrencyEntity> currencies) {
    this.currencies = currencies;
  }
}
