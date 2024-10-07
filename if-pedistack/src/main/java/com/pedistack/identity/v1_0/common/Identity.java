package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Identity implements Serializable {

  private String internalIdentity;
  private String msisdn;
  private String emailAddress;
  private String username;

  private Person person;
  private Business business;
  private List<PostalAddress> postalAddresses;
  private List<CommunicationAddress> communicationAddresses;
  private List<Identification> identifications;
  private SocialMedia socialMedia;
  private Developer developer;

  @JsonProperty(required = true)
  private IdentityType type;

  private IdentityStatus status;

  public String getInternalIdentity() {
    return internalIdentity;
  }

  public void setInternalIdentity(String internalIdentity) {
    this.internalIdentity = internalIdentity;
  }

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

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
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

  public IdentityStatus getStatus() {
    return status;
  }

  public void setStatus(IdentityStatus status) {
    this.status = status;
  }
}
