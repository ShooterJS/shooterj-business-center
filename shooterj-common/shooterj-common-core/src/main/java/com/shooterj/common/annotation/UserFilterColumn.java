package com.shooterj.common.annotation;

import java.lang.annotation.*;

/**
 * 主要用于标记数据权限中基于UserId进行过滤的字段。
 *
 * @author Shooterj
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserFilterColumn {

}
