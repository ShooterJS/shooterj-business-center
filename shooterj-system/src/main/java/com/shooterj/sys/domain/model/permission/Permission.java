package com.shooterj.sys.domain.model.permission;

import com.shooterj.common.domain.Entity;
import com.shooterj.common.domain.StatusEnum;

import java.util.List;

public class Permission implements Entity<Permission> {

    public final static String ROOT_ID = "0";

    /**
     * id
     */
    private PermissionId permissionId;

    /**
     * 权限名称
     */
    private PermissionName permissionName;

    /**
     * 权限类型
     */
    private PermissionTypeEnum permissionType;

    /**
     * 权限级别
     */
    private PermissionLevelEnum permissionLevel;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 权限编码
     */
    private PermissionCodes permissionCodes;

    /**
     * 排序
     */
    private int orderNum;

    /**
     * 菜单URL
     */
    private MenuUrl menuUrl;

    /**
     * 父权限
     */
    private Permission parent;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 子权限
     */
    private List<Permission> subList;

    public Permission(PermissionId permissionId) {
        this.permissionId = permissionId;
    }

    public Permission(PermissionId permissionId, PermissionName permissionName, PermissionTypeEnum permissionType, PermissionLevelEnum permissionLevel, String menuIcon, PermissionCodes permissionCodes, int orderNum, MenuUrl menuUrl, Permission parent, StatusEnum status, List<Permission> subList) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionType = permissionType;
        this.permissionLevel = permissionLevel;
        this.menuIcon = menuIcon;
        this.permissionCodes = permissionCodes;
        this.orderNum = orderNum;
        this.menuUrl = menuUrl;
        this.parent = parent;
        this.status = status;
        this.subList = subList;
    }

    @Override
    public boolean sameIdentityAs(Permission other) {
        return other != null && permissionId.sameValueAs(other.permissionId);
    }


    public PermissionId getPermissionId() {
        return permissionId;
    }

    public PermissionName getPermissionName() {
        return permissionName;
    }

    public PermissionTypeEnum getPermissionType() {
        return permissionType;
    }

    public PermissionLevelEnum getPermissionLevel() {
        return permissionLevel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public PermissionCodes getPermissionCodes() {
        return permissionCodes;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public MenuUrl getMenuUrl() {
        return menuUrl;
    }

    public Permission getParent() {
        return parent;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public List<Permission> getSubList() {
        return subList;
    }

}
