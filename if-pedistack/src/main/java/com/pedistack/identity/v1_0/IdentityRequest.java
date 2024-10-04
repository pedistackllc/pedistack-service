package com.pedistack.identity.v1_0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedistack.identity.v1_0.common.*;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class IdentityRequest implements Serializable {

  private String msisdn;
  private String emailAddress;
  private String username;
  private String password;

  private Person person;
  private List<Business> businesses;
  private List<PostalAddress> postalAddresses;
  private List<CommunicationAddress> communicationAddresses;
  private List<Identification> identifications;
  private SocialMedia socialMedia;
  private Developer developer;

  @JsonProperty(required = true)
  private IdentityType type;

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public List<Business> getBusinesses() {
    return businesses;
  }

  public void setBusinesses(List<Business> businesses) {
    this.businesses = businesses;
  }

  public List<PostalAddress> getPostalAddresses() {
    return postalAddresses;
  }

  public void setPostalAddresses(List<PostalAddress> postalAddresses) {
    this.postalAddresses = postalAddresses;
  }

  public List<CommunicationAddress> getCommunicationAddresses() {
    return communicationAddresses;
  }

  public void setCommunicationAddresses(List<CommunicationAddress> communicationAddresses) {
    this.communicationAddresses = communicationAddresses;
  }

  public List<Identification> getIdentifications() {
    return identifications;
  }

  public void setIdentifications(List<Identification> identifications) {
    this.identifications = identifications;
  }

  public SocialMedia getSocialMedia() {
    return socialMedia;
  }

  public void setSocialMedia(SocialMedia socialMedia) {
    this.socialMedia = socialMedia;
  }

  public Developer getDeveloper() {
    return developer;
  }

  public void setDeveloper(Developer developer) {
    this.developer = developer;
  }

  public IdentityType getType() {
    return type;
  }

  public void setType(IdentityType type) {
    this.type = type;
  }
}
