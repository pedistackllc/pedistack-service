package com.pedistack.db.admins;

import com.pedistack.common.db.BaseEntity;
import com.pedistack.db.oauth.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "admins", name = "admin_users")
public final class AdminUserEntity extends BaseEntity {

  private String firstName;
  private String lastName;
  @ManyToOne private UserEntity user;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
