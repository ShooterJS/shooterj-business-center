package com.shooterj.sys.infrastructure.persistence.converter;

import com.shooterj.common.domain.StatusEnum;
import  com.shooterj.sys.domain.model.tenant.Tenant;
import  com.shooterj.sys.domain.model.tenant.TenantCode;
import  com.shooterj.sys.domain.model.tenant.TenantId;
import  com.shooterj.sys.domain.model.tenant.TenantName;
import  com.shooterj.sys.domain.model.user.UserId;
import com.shooterj.sys.infrastructure.persistence.entity.SysTenantDO;

/**
 * 租户Converter
 **/
public class TenantConverter {

    public static Tenant toTenant(SysTenantDO sysTenantDO) {
        if(sysTenantDO == null) {
            return null;
        }
        TenantId tenantId = null;
        if(sysTenantDO.getId() != null) {
            tenantId = new TenantId(sysTenantDO.getId());
        }
        UserId creatorId = null;
        if(sysTenantDO.getCreatorId() != null) {
            creatorId = new UserId(sysTenantDO.getCreatorId());
        }
        Tenant tenant = new Tenant(tenantId, new TenantCode(sysTenantDO.getTenantCode()), new TenantName(sysTenantDO.getTenantName()), StatusEnum.of(sysTenantDO.getStatus()).get(),creatorId);
        return tenant;
    }

    public static SysTenantDO getSysTenantDO(Tenant tenant) {
        if(tenant == null) {
            throw new RuntimeException("租户不存在");
        }
        SysTenantDO sysTenantDO = new SysTenantDO();
        sysTenantDO.setId(tenant.getTenantId()==null?null:tenant.getTenantId().getId());
        sysTenantDO.setTenantCode(tenant.getTenantCode()==null?null:tenant.getTenantCode().getCode());
        sysTenantDO.setTenantName(tenant.getTenantName()==null?null:tenant.getTenantName().getName());
        sysTenantDO.setStatus(tenant.getStatus()==null?null:tenant.getStatus().getCode());
        sysTenantDO.setCreatorId(tenant.getCreatorId() == null ?null:tenant.getCreatorId().getId());
        return sysTenantDO;
    }
}
