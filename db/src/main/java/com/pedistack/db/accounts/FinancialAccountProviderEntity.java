package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.planet.CountryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "accounts", name = "financial_account_providers")
public final class FinancialAccountProviderEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false, unique = true)
  private String code;

  @ManyToOne private CountryEntity country;

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public CountryEntity getCountry() {
    return country;
  }

  public void setCountry(CountryEntity country) {
    this.country = country;
  }
}
