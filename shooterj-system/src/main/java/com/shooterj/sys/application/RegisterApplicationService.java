package com.shooterj.sys.application;

import com.shooterj.sys.application.command.RegisterTenantCommand;

/**
 * 身份验证应用服务接口
 **/
public interface RegisterApplicationService {

    /**
     * 注册租户
     *
     * @param registerTenantCommand
     */
    void registerTenant(RegisterTenantCommand registerTenantCommand);

}
