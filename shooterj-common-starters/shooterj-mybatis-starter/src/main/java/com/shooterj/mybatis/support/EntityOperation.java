package com.shooterj.mybatis.support;

import com.shooterj.common.validator.ValidateGroup;

public interface EntityOperation {
    public <T> void doValidate(T t, Class<? extends ValidateGroup> group);
}
