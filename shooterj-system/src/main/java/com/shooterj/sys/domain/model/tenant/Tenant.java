package com.shooterj.sys.domain.model.tenant;

import com.shooterj.common.domain.Entity;
import com.shooterj.common.domain.StatusEnum;
import com.shooterj.sys.domain.model.user.UserId;

public class Tenant implements Entity<Tenant> {

    private TenantId tenantId;

    private TenantCode tenantCode;

    private TenantName tenantName;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 创建者Id
     */
    private UserId creatorId;

    public Tenant(TenantCode tenantCode, TenantName tenantName) {
        this.tenantCode = tenantCode;
        this.tenantName = tenantName;
    }

    public Tenant(TenantId tenantId, TenantCode tenantCode, TenantName tenantName, StatusEnum status, UserId creatorId) {
        this.tenantId = tenantId;
        this.tenantCode = tenantCode;
        this.tenantName = tenantName;
        this.status = status;
        this.creatorId = creatorId;
    }

    public TenantCode getTenantCode() {
        return tenantCode;
    }

    public TenantName getTenantName() {
        return tenantName;
    }

    public TenantId getTenantId() {
        return tenantId;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public UserId getCreatorId() {
        return creatorId;
    }

    @Override
    public boolean sameIdentityAs(Tenant other) {
        return other != null && tenantId.sameValueAs(other.tenantId);
    }
}
