package com.shooterj.sys.application.impl;

import com.shooterj.sys.application.RegisterApplicationService;
import com.shooterj.sys.application.command.RegisterTenantCommand;
import com.shooterj.sys.domain.model.captcha.CaptchaCode;
import com.shooterj.sys.domain.model.captcha.CaptchaRepository;
import com.shooterj.sys.domain.model.captcha.Uuid;
import com.shooterj.sys.domain.model.permission.PermissionRepository;
import com.shooterj.sys.domain.model.role.RoleRepository;
import com.shooterj.sys.domain.model.tenant.TenantCode;
import com.shooterj.sys.domain.model.tenant.TenantName;
import com.shooterj.sys.domain.model.tenant.TenantRepository;
import com.shooterj.sys.domain.model.user.Mobile;
import com.shooterj.sys.domain.model.user.Password;
import com.shooterj.sys.domain.model.user.UserName;
import com.shooterj.sys.domain.model.user.UserRepository;
import com.shooterj.sys.domain.service.CaptchaValidateService;
import com.shooterj.sys.domain.service.TenantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 身份验证应用服务实现类
 *
 * @author shooterj
 * @date 2022-05-10
 **/
@Service
public class RegisterApplicationServiceImpl implements RegisterApplicationService {


    @Autowired
    private CaptchaRepository captchaRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void registerTenant(RegisterTenantCommand registerTenantCommand) {
        CaptchaValidateService captchaValidateService = new CaptchaValidateService(captchaRepository);

        if(!captchaValidateService.validate(new Uuid(registerTenantCommand.getUuid()), new CaptchaCode(registerTenantCommand.getCaptcha()))) {
            throw new RuntimeException("验证码不正确");
        }

        TenantRegisterService tenantRegisterService = new TenantRegisterService(tenantRepository, roleRepository, permissionRepository,userRepository);

        tenantRegisterService.registerTenant(
                new TenantName(registerTenantCommand.getTenantName()),
                new TenantCode(registerTenantCommand.getTenantCode()),
                new Mobile(registerTenantCommand.getMobile()),
                Password.create(registerTenantCommand.getPassword()),
                new UserName(registerTenantCommand.getUserName()));
    }

}
