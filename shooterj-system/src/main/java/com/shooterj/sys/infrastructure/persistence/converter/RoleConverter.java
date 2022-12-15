package com.shooterj.sys.infrastructure.persistence.converter;

import  com.shooterj.sys.domain.model.role.Role;
import com.shooterj.sys.infrastructure.persistence.entity.SysRoleDO;
import com.shooterj.common.mapper.GenericEnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = GenericEnumMapper.class)
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    public  SysRoleDO entityToDo(Role role) ;


}
