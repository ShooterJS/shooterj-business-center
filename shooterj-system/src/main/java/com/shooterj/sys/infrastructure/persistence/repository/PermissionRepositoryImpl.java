package com.shooterj.sys.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.sys.domain.model.permission.Permission;
import com.shooterj.sys.domain.model.permission.PermissionRepository;
import com.shooterj.sys.infrastructure.persistence.converter.PermissionConverter;
import com.shooterj.sys.infrastructure.persistence.entity.SysPermissionDO;
import com.shooterj.sys.infrastructure.persistence.mapper.SysPermissionMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PermissionRepositoryImpl extends ServiceImpl<SysPermissionMapper, SysPermissionDO> implements PermissionRepository, IService<SysPermissionDO> {
    @Override
    public List<Permission> queryList(Map<String, Object> params) {
        List<Permission> permissions = new ArrayList<>();
        List<SysPermissionDO> list = getBaseMapper().queryList(params);
        for(SysPermissionDO sysPermissionDO : list) {
            Permission permission = PermissionConverter.toPermission(sysPermissionDO, getParentPermission(sysPermissionDO.getParentId()),null);
            permissions.add(permission);
        }
        return permissions;
    }

    /**
     * 设置父权限
     *
     * @param parentId
     */
    private SysPermissionDO getParentPermission(String parentId) {
        SysPermissionDO parent = null;
        if (parentId != null) {
            parent = this.getById(parentId);
        }
        return parent;
    }
}
