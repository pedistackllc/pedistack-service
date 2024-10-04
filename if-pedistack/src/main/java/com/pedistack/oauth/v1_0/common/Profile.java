package com.pedistack.oauth.v1_0.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Profile implements Serializable {

  private String id;
  private String name;
  private String description;
  private ProfileType type;
  private List<String> permissions;
  private Map<String, String> additionalInformation;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProfileType getType() {
    return type;
  }

  public void setType(ProfileType type) {
    this.type = type;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public Map<String, String> getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(Map<String, String> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }
}
