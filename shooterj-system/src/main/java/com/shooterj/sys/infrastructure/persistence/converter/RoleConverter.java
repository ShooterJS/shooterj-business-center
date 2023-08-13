package com.shooterj.sys.infrastructure.persistence.converter;

import com.shooterj.sys.domain.model.role.Role;
import com.shooterj.sys.infrastructure.persistence.entity.SysRoleDO;

public interface RoleConverter {



    public static SysRoleDO fromRole(Role role) {
        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setId(role.getRoleId() == null ? null : role.getRoleId().getId());
        sysRoleDO.setRoleCode(role.getRoleCode() == null ? null : role.getRoleCode().getCode());
        sysRoleDO.setRoleName(role.getRoleName() == null ? null : role.getRoleName().getName());
        sysRoleDO.setRemarks(role.getRemarks());
        sysRoleDO.setStatus(role.getStatus() == null ? null : role.getStatus().getName());
        return sysRoleDO;
    }


}
