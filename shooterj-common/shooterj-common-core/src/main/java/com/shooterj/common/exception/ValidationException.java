package com.shooterj.common.exception;

import com.shooterj.common.model.ValidateResult;
import lombok.Getter;

import java.util.List;

/**
 * @author shooterj
 */
public class ValidationException extends RuntimeException {
  @Getter
  private List<ValidateResult> result;
  public ValidationException(List<ValidateResult> list){
    super();
    this.result = list;
  }
}
