package com.pedistack.identity.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SocialMedia implements Serializable {

  private String facebookAccountUsername;
  private String twitterAccountUsername;
  private String instagramAccountUsername;
  private String mediumAccountUsername;
  private String linkedInAccountUrl;

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
