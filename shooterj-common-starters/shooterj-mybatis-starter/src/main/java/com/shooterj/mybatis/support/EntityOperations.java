package com.shooterj.mybatis.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public  class EntityOperations {

    public static <T> EntityCreator<T> doCreate(BaseMapper<T> baseMapper){
        return new EntityCreator(baseMapper);
    }

    public static <T> EntityUpdator<T> doUpdate(BaseMapper<T> baseMapper){
        return new EntityUpdator(baseMapper);
    }

}
