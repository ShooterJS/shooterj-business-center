package com.shooterj.common.model;


import java.io.Serializable;

public abstract class AbstractJpaResponse implements Serializable {

  private Long id;

  private Long createdAt;

  private Long updatedAt;

  private Integer version;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }



}
