package com.pedistack.common.db;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(schema = "base", name = "base_entity")
public class BaseEntity implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id
  @Column(nullable = false, updatable = false, unique = true)
  private String id;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDateTime;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastAccessedDateTime;

  @CreatedBy private String createdUserId;

  @Version private Integer version = 0;

  public BaseEntity() {
    creationDateTime = Date.from(Instant.now());
    id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(Date creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  public Date getLastAccessedDateTime() {
    return lastAccessedDateTime;
  }

  public void setLastAccessedDateTime(Date lastAccessedDateTime) {
    this.lastAccessedDateTime = lastAccessedDateTime;
  }

  public Integer getVersion() {
    return Optional.ofNullable(version).orElse(0);
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getCreatedUserId() {
    return createdUserId;
  }

  public void setCreatedUserId(String createdUserId) {
    this.createdUserId = createdUserId;
  }
}
