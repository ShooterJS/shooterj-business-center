package com.shooterj.sys.domain.model.tenant;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 租户id
 **/
public class TenantId implements ValueObject<TenantId> {

    /**
     * 平台租户
     */
    public static final String PLATFORM_TENANT = "1";

    private String id;

    public TenantId(final String id) {
        Preconditions.checkArgument(StringUtils.isBlank(id),"租户id不能为空");
        this.id = id;
    }

    public boolean isPlatformId() {
        return id != null && PLATFORM_TENANT.equals(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(TenantId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
