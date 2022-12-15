package com.shooterj.sys.domain.model.role;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

import java.util.regex.Pattern;

/**
 * 角色编码
 **/
public class RoleCode implements ValueObject<RoleCode> {

    /**
     * 租户管理员角色编码
     */
    public static final String TENANT_ADMIN = "tenantAdmin";

    private String code;

    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    public RoleCode(final String code) {
        Preconditions.checkNotNull(code,"角色编码不能为空");
        Preconditions.checkArgument(VALID_PATTERN.matcher(code).matches(),"编码格式不正确");
        this.code = code;
    }

    public boolean isTenantAdmin() {
        return TENANT_ADMIN.equals(code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean sameValueAs(RoleCode other) {
        return other != null && this.code.equals(other.code);
    }

    @Override
    public String toString() {
        return code;
    }
}
