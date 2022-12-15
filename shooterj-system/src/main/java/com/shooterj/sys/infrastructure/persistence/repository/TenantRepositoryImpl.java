package com.shooterj.sys.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.common.util.TenantContext;
import com.shooterj.sys.domain.model.tenant.*;
import com.shooterj.sys.infrastructure.persistence.converter.TenantConverter;
import com.shooterj.sys.infrastructure.persistence.entity.SysTenantDO;
import com.shooterj.sys.infrastructure.persistence.mapper.SysTenantMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TenantRepositoryImpl extends ServiceImpl<SysTenantMapper, SysTenantDO> implements TenantRepository, IService<SysTenantDO> {

    @Override
    public Tenant find(TenantId tenantId) {
        SysTenantDO sysTenantDO = this.getById(tenantId.getId());
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    public Tenant find(TenantName tenantName) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_name", tenantName.getName()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    public Tenant find(TenantCode tenantCode) {
        SysTenantDO sysTenantDO = this.getOne(new QueryWrapper<SysTenantDO>().eq("tenant_code", tenantCode.getCode()));
        if(sysTenantDO == null) {
            return null;
        }
        Tenant tenant = TenantConverter.toTenant(sysTenantDO);
        return tenant;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TenantId store(Tenant tenant) {
        SysTenantDO sysTenantDO = TenantConverter.getSysTenantDO(tenant);
        this.saveOrUpdate(sysTenantDO);
        if(TenantContext.getTenantId() == null) {
            TenantContext.setTenantId(sysTenantDO.getId());
        }
        return new TenantId(sysTenantDO.getId());
    }
}
