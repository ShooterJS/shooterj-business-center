package com.shooterj.sys.domain.model.permission;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

/**
 * 权限名称
 **/
public class PermissionName implements ValueObject<PermissionName> {

    private String name;

    public PermissionName(final String name) {
        Preconditions.checkNotNull(name,"权限名称不能为空");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(PermissionName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
