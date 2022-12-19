package com.shooterj.sys.api;

import com.shooterj.common.log.SysLog;
import com.shooterj.common.pipeline.InstanceBuildContext;
import com.shooterj.common.pipeline.PipelineRouteConfig;
import com.shooterj.common.validator.ValidatorUtils;
import com.shooterj.common.validator.group.AddGroup;
import com.shooterj.common.web.util.Result;
import com.shooterj.sys.application.RegisterApplicationService;
import com.shooterj.sys.application.command.RegisterTenantCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 注册Controller
 **/
@Api(tags = "注册")
@RestController
public class RegisterController {

    @Autowired
    private RegisterApplicationService registerApplicationService;


    /**
     * 注册租户
     */
    @ApiOperation("注册租户")
    @SysLog("注册租户")
    @PostMapping("/sys/registerTenant")
    public Result registerTenantAndUser(@RequestBody RegisterTenantCommand registerTenantCommand) {
        //前端校验
        ValidatorUtils.validateEntity(registerTenantCommand, AddGroup.class);
        //应用层编排
        registerApplicationService.registerTenant(registerTenantCommand);
        return Result.ok();
    }
}
