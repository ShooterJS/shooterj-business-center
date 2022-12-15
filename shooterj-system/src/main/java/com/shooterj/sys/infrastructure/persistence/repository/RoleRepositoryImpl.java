package com.shooterj.sys.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.sys.domain.model.permission.PermissionId;
import com.shooterj.sys.domain.model.role.Role;
import com.shooterj.sys.domain.model.role.RoleId;
import com.shooterj.sys.domain.model.role.RoleRepository;
import com.shooterj.sys.infrastructure.persistence.converter.RoleConverter;
import com.shooterj.sys.infrastructure.persistence.entity.SysRoleDO;
import com.shooterj.sys.infrastructure.persistence.entity.SysRolePermissionDO;
import com.shooterj.sys.infrastructure.persistence.mapper.SysRoleMapper;
import com.shooterj.sys.infrastructure.persistence.mapper.SysRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepositoryImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements RoleRepository, IService<SysRoleDO> {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;



    @Override
    public RoleId store(Role role) {
        SysRoleDO sysRoleDO = RoleConverter.INSTANCE.entityToDo(role);
        this.saveOrUpdate(sysRoleDO);
        String roleId = sysRoleDO.getId();
        //先删除角色与菜单关系
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleId);
        sysRolePermissionMapper.deleteByRoleIds(roleIds);
        List<PermissionId> permissionIds = role.getPermissionIds();
        if(permissionIds != null && !permissionIds.isEmpty()) {
            //保存角色与菜单关系
            for(PermissionId permissionId : permissionIds){
                SysRolePermissionDO sysRolePermissionDO = new SysRolePermissionDO();
                sysRolePermissionDO.setPermissionId(permissionId.getId());
                sysRolePermissionDO.setRoleId(roleId);
                sysRolePermissionMapper.insert(sysRolePermissionDO);
            }
        }
        return new RoleId(sysRoleDO.getId());
    }
}
