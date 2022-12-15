package com.shooterj.common.mapper;


import com.shooterj.common.constants.ValidStatus;

public class GenericEnumMapper {

  public Integer asInteger(ValidStatus status) {
    return status.getCode();
  }

  public ValidStatus asValidStatus(Integer code) {
    return ValidStatus.of(code).orElse(ValidStatus.INVALID);
  }
}