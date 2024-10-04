package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "ident", name = "social_media_accounts")
public class SocialMediaAccountEntity extends BaseEntity {

  @ManyToOne private UserEntity user;

  @Column(unique = true)
  private String facebookAccountUsername;

  @Column(unique = true)
  private String twitterAccountUsername;

  @Column(unique = true)
  private String instagramAccountUsername;

  @Column(unique = true)
  private String mediumAccountUsername;

  @Column(unique = true)
  private String linkedInAccountUrl;

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public String getFacebookAccountUsername() {
    return facebookAccountUsername;
  }

  public void setFacebookAccountUsername(String facebookAccountUsername) {
    this.facebookAccountUsername = facebookAccountUsername;
  }

  public String getTwitterAccountUsername() {
    return twitterAccountUsername;
  }

  public void setTwitterAccountUsername(String twitterAccountUsername) {
    this.twitterAccountUsername = twitterAccountUsername;
  }

  public String getInstagramAccountUsername() {
    return instagramAccountUsername;
  }

  public void setInstagramAccountUsername(String instagramAccountUsername) {
    this.instagramAccountUsername = instagramAccountUsername;
  }

  public String getMediumAccountUsername() {
    return mediumAccountUsername;
  }

  public void setMediumAccountUsername(String mediumAccountUsername) {
    this.mediumAccountUsername = mediumAccountUsername;
  }

  public String getLinkedInAccountUrl() {
    return linkedInAccountUrl;
  }

  public void setLinkedInAccountUrl(String linkedInAccountUrl) {
    this.linkedInAccountUrl = linkedInAccountUrl;
  }
}
