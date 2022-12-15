package com.shooterj.sys.domain.model.role;

import com.shooterj.common.domain.StatusEnum;
import  com.shooterj.sys.domain.model.permission.PermissionId;

import java.util.List;

public class Role {
    /**
     * roleId
     */
    private RoleId roleId;

    /**
     * 角色编码
     */
    private RoleCode roleCode;

    /**
     * 角色名称
     */
    private RoleName roleName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 权限列表
     */
    private List<PermissionId> permissionIds;

    public Role(RoleId roleId, RoleCode roleCode, RoleName roleName, String remarks, StatusEnum status, List<PermissionId> permissionIds) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.remarks = remarks;
        this.status = status;
        this.permissionIds = permissionIds;
    }

    public Role(RoleCode roleCode, RoleName roleName, List<PermissionId> permissionIds) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.permissionIds = permissionIds;
    }

    public RoleId getRoleId() {
        return roleId;
    }

    public RoleCode getRoleCode() {
        return roleCode;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public String getRemarks() {
        return remarks;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public List<PermissionId> getPermissionIds() {
        return permissionIds;
    }
}
