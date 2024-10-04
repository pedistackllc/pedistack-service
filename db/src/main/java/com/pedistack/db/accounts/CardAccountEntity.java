package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.common.db.PersistenceEncryption;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.planet.CountryEntity;
import jakarta.persistence.*;

@Entity
@Table(schema = "accounts", name = "card_accounts")
public final class CardAccountEntity extends BaseEntity {

  private String cardName;

  @Column(unique = true)
  @Convert(converter = PersistenceEncryption.class)
  private String cardToken;

  @Column(unique = true)
  @Convert(converter = PersistenceEncryption.class)
  private String last4;

  @Column(nullable = false)
  private String cardType;

  @Convert(converter = PersistenceEncryption.class)
  private String cardBin;

  private String bank;
  private int expiryMonth;
  private int expiryYear;

  @Convert(converter = PersistenceEncryption.class)
  private String signature;

  @ManyToOne private CountryEntity country;

  @ManyToOne private UserEntity user;

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public String getCardToken() {
    return cardToken;
  }

  public void setCardToken(String cardToken) {
    this.cardToken = cardToken;
  }

  public String getLast4() {
    return last4;
  }

  public void setLast4(String last4) {
    this.last4 = last4;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getCardBin() {
    return cardBin;
  }

  public void setCardBin(String cardBin) {
    this.cardBin = cardBin;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public int getExpiryMonth() {
    return expiryMonth;
  }

  public void setExpiryMonth(int expiryMonth) {
    this.expiryMonth = expiryMonth;
  }

  public int getExpiryYear() {
    return expiryYear;
  }

  public void setExpiryYear(int expiryYear) {
    this.expiryYear = expiryYear;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public CountryEntity getCountry() {
    return country;
  }

  public void setCountry(CountryEntity country) {
    this.country = country;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
