package com.pedistack.db.identity;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "ident", name = "identity_developers")
public final class DeveloperInformationEntity extends BaseEntity {

  private String callbackUrl;
  private String webhookUrl;
  private String apiUrl;

  @ManyToOne private UserEntity user;

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getWebhookUrl() {
    return webhookUrl;
  }

  public void setWebhookUrl(String webhookUrl) {
    this.webhookUrl = webhookUrl;
  }

  public String getApiUrl() {
    return apiUrl;
  }

  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
