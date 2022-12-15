package com.shooterj.common.validator.group;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author shooterj
 */
//@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface ValidateGroup {

}
