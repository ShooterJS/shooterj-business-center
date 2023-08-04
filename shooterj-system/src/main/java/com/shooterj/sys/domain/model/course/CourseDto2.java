package com.shooterj.sys.domain.model.course;

import com.shooterj.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * CourseDto对象。
 */
@ApiModel("CourseDto对象")
@Data
public class CourseDto2 {
    /**
     * 主键Id。
     */
    @ApiModelProperty(value = "主键Id", required = true)
    @NotNull(message = "数据验证失败，主键Id不能为空！", groups = {UpdateGroup.class})
    private Long id;

    /**
     * 课程名称。
     */
    @ApiModelProperty(value = "课程名称", required = true)
    @NotBlank(message = "数据验证失败，课程名称不能为空！")
    private String courseName;
}
