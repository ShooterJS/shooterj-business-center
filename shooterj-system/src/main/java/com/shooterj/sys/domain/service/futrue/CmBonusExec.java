package com.shooterj.sys.domain.service.futrue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 执行测算的奖金方案
 * <pre>
 * 描述：执行测算的奖金方案 实体对象
 * 构建组：x7
 * 作者:FangGan
 * 邮箱:PRD-Fang.Gan@Winwayworld.com
 * 日期:2020-08-26 17:59:28
 * 版权：深圳万威科技有限公司
 * </pre>
 */
@Setter
@Getter
@ToString
@ApiModel(value = "CmBonusExec", description = "执行测算的奖金方案")
public class CmBonusExec {

    @ApiModelProperty(value = "ID")
    protected String id;
    @ApiModelProperty(value = "奖金方案ID")
    protected String schemeId;
    @ApiModelProperty(value = "考核期ID")
    protected String periodId;
    @ApiModelProperty(value = "操作人编码")
    protected String operator;
    @ApiModelProperty(value = "初始化线程名")
    protected String initThreadName;
    @ApiModelProperty(value = "工作引擎计算线程名")
    protected String calThreadName;
    @ApiModelProperty(value = "状态（0：未开始，1：测算中，2：测算完成，3：测算失败）")
    protected Integer status;
    @ApiModelProperty(value = "版本")
    protected String version;

}