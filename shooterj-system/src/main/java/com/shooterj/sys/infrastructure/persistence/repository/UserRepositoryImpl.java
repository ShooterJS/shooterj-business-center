package com.shooterj.sys.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shooterj.sys.domain.model.user.Mobile;
import com.shooterj.sys.domain.model.user.User;
import com.shooterj.sys.domain.model.user.UserId;
import com.shooterj.sys.domain.model.user.UserRepository;
import com.shooterj.sys.infrastructure.persistence.entity.SysUserDO;
import com.shooterj.sys.infrastructure.persistence.mapper.SysUserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements UserRepository, IService<SysUserDO> {

    @Override
    public UserId store(User user) {
        return null;
    }

    @Override
    public List<User> find(Mobile mobile) {
        return null;
    }
}
