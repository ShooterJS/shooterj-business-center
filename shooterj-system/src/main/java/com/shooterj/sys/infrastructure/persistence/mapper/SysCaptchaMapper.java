package com.shooterj.sys.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shooterj.sys.infrastructure.persistence.entity.SysCaptchaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码Mapper
 **/
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptchaDO> {
}
