package com.shooterj.sys.infrastructure.persistence.converter;

import com.shooterj.common.domain.StatusEnum;
import com.shooterj.sys.domain.model.permission.*;
import com.shooterj.sys.infrastructure.persistence.entity.SysPermissionDO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
/**
 * 权限Converter
 **/
public class PermissionConverter {

    public static Permission toPermission(SysPermissionDO sysPermissionDO, SysPermissionDO parentPermissionDO, List<SysPermissionDO> subPermissionDOList) {
        if(sysPermissionDO == null) {
            throw new RuntimeException("未找到权限");
        }
        MenuUrl menuUrl = null;
        if(sysPermissionDO.getMenuUrl() != null) {
            menuUrl = new MenuUrl(sysPermissionDO.getMenuUrl());
        }
        Permission parent = PermissionConverter.toPermission(parentPermissionDO);
        List<Permission> subPermissions = null;
        if(subPermissionDOList != null && !subPermissionDOList.isEmpty()) {
            subPermissions = new ArrayList<>();
            for(SysPermissionDO subSysPermissionDO : subPermissionDOList) {
                Permission subPermission = PermissionConverter.toPermission(subSysPermissionDO);
                subPermissions.add(subPermission);
            }
        }
        Permission permission = new Permission(new PermissionId(sysPermissionDO.getId()),new PermissionName(sysPermissionDO.getPermissionName()),
                PermissionTypeEnum.getMenuTypeEnum(sysPermissionDO.getPermissionType()),
                PermissionLevelEnum.getMenuLevelEnum(sysPermissionDO.getPermissionLevel()),
                sysPermissionDO.getMenuIcon(), toPermissionCodes(Collections.singletonList(sysPermissionDO.getPermissionCodes())),
                sysPermissionDO.getOrderNum(),
                menuUrl, parent, StatusEnum.of(sysPermissionDO.getStatus()).get(), subPermissions);
        return permission;
    }


    public static Permission toPermission(SysPermissionDO sysPermissionDO) {
        if(sysPermissionDO == null) {
            return null;
        }
        MenuUrl menuUrl = null;
        if(sysPermissionDO.getMenuUrl() != null) {
            menuUrl = new MenuUrl(sysPermissionDO.getMenuUrl());
        }
        Permission permission = new Permission(new PermissionId(sysPermissionDO.getId()),new PermissionName(sysPermissionDO.getPermissionName()), PermissionTypeEnum.getMenuTypeEnum(sysPermissionDO.getPermissionType()),
                PermissionLevelEnum.getMenuLevelEnum(sysPermissionDO.getPermissionLevel()),sysPermissionDO.getMenuIcon(), toPermissionCodes(Collections.singletonList(sysPermissionDO.getPermissionCodes())),sysPermissionDO.getOrderNum(),
                menuUrl, null, StatusEnum.of(sysPermissionDO.getStatus()).get(), null);
        return permission;
    }


    public static PermissionCodes toPermissionCodes(List<String> permsList) {
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        PermissionCodes permissionCodes = null;
        if(!permsSet.isEmpty()) {
            permissionCodes = new PermissionCodes(permsSet);
        }
        return permissionCodes;
    }


}
