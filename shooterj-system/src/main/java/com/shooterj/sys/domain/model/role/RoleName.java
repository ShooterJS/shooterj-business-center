package com.shooterj.sys.domain.model.role;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
/**
 * 角色名称
 **/
public class RoleName implements ValueObject<RoleName> {

    /**
     * 租户管理员角色名称
     */
    public static final String TENANT_ADMIN = "租户管理";

    private String name;

    public RoleName(final String name) {
        Preconditions.checkNotNull(name,"角色名称不能为空");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean sameValueAs(RoleName other) {
        return other != null && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
