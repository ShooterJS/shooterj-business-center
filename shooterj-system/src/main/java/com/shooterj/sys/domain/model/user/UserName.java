package com.shooterj.sys.domain.model.user;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户名
 *
 * @author shooterj
 **/
public class UserName implements ValueObject<UserName> {

    /**
     * 用户名
     */
    private String name;

    public UserName(String name) {
        Preconditions.checkArgument(StringUtils.isBlank(name),"用户名不能为空");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(UserName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
