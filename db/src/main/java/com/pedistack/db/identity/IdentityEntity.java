package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(schema = "ident", name = "identities")
public final class IdentityEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String internalIdentity;

  @ManyToOne private UserEntity user;
  @ManyToOne private PersonEntity person;

  @ManyToOne private BusinessEntity business;

  @ManyToMany
  @JoinTable(schema = "ident", name = "identity_postal_addresses")
  private List<AddressEntity> postalAddresses;

  @ManyToMany
  @JoinTable(schema = "ident", name = "identity_communication_addresses")
  private List<AddressEntity> communicationAddresses;

  @ManyToMany
  @JoinTable(schema = "ident", name = "identity_identifications")
  private List<IdentificationEntity> identifications;

  @ManyToOne private SocialMediaAccountEntity socialMediaAccount;
  @ManyToOne private DeveloperInformationEntity developerInformation;

  @ManyToOne private IdentityEntity parentIdentity;

  @Column(nullable = false)
  private String status;

  public String getInternalIdentity() {
    return internalIdentity;
  }

  public void setInternalIdentity(String internalIdentity) {
    this.internalIdentity = internalIdentity;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public PersonEntity getPerson() {
    return person;
  }

  public void setPerson(PersonEntity person) {
    this.person = person;
  }

  public BusinessEntity getBusiness() {
    return business;
  }

  public void setBusiness(BusinessEntity business) {
    this.business = business;
  }

  public List<AddressEntity> getPostalAddresses() {
    return postalAddresses;
  }

  public void setPostalAddresses(List<AddressEntity> postalAddresses) {
    this.postalAddresses = postalAddresses;
  }

  public List<AddressEntity> getCommunicationAddresses() {
    return communicationAddresses;
  }

  public void setCommunicationAddresses(List<AddressEntity> communicationAddresses) {
    this.communicationAddresses = communicationAddresses;
  }

  public List<IdentificationEntity> getIdentifications() {
    return identifications;
  }

  public void setIdentifications(List<IdentificationEntity> identifications) {
    this.identifications = identifications;
  }

  public SocialMediaAccountEntity getSocialMediaAccount() {
    return socialMediaAccount;
  }

  public void setSocialMediaAccount(SocialMediaAccountEntity socialMediaAccount) {
    this.socialMediaAccount = socialMediaAccount;
  }

  public DeveloperInformationEntity getDeveloperInformation() {
    return developerInformation;
  }

  public void setDeveloperInformation(DeveloperInformationEntity developerInformation) {
    this.developerInformation = developerInformation;
  }

  public IdentityEntity getParentIdentity() {
    return parentIdentity;
  }

  public void setParentIdentity(IdentityEntity parentIdentity) {
    this.parentIdentity = parentIdentity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
