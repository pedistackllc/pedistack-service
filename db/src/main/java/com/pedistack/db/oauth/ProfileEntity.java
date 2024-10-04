package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseEntity;
import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(schema = "oauth", name = "profiles")
public final class ProfileEntity extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private String type;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "profile_permissions", schema = "oauth")
  private List<String> permissions;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "profile_additional_information", schema = "oauth")
  private Map<String, String> additionalInformation;

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
