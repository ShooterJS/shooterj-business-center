package com.shooterj.sys.domain.model.course;

import com.shooterj.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * CourseDto对象。
 */
@ApiModel("CourseDto对象")
@Data
public class CourseDto {

    /**
     * 主键Id。
     */
    @ApiModelProperty(value = "主键Id", required = true)
    @NotNull(message = "数据验证失败，主键Id不能为空！", groups = {UpdateGroup.class})
    private Long courseId;

    /**
     * 课程名称。
     */
    @ApiModelProperty(value = "课程名称", required = true)
    @NotBlank(message = "数据验证失败，课程名称不能为空！")
    private String courseName;

    /**
     * 课程价格。
     */
    @ApiModelProperty(value = "课程价格", required = true)
    @NotNull(message = "数据验证失败，课程价格不能为空！")
    private BigDecimal price;

    /**
     * 课程描述。
     */
    @ApiModelProperty(value = "课程描述")
    private String description;

    /**
     * 课程难度(0: 容易 1: 普通 2: 很难)。
     */
    @ApiModelProperty(value = "课程难度(0: 容易 1: 普通 2: 很难)", required = true)
    @NotNull(message = "数据验证失败，课程难度不能为空！")
    private Integer difficulty;

    /**
     * 年级Id。
     */
    @ApiModelProperty(value = "年级Id", required = true)
    @NotNull(message = "数据验证失败，所属年级不能为空！")
    private Integer gradeId;

    /**
     * 学科Id。
     */
    @ApiModelProperty(value = "学科Id", required = true)
    @NotNull(message = "数据验证失败，所属学科不能为空！")
    private Integer subjectId;

    /**
     * 课时数量。
     */
    @ApiModelProperty(value = "课时数量", required = true)
    @NotNull(message = "数据验证失败，课时数量不能为空！")
    private Integer classHour;

    /**
     * 多张课程图片地址。
     */
    @ApiModelProperty(value = "多张课程图片地址", required = true)
    @NotBlank(message = "数据验证失败，课程图片不能为空！")
    private String pictureUrl;

    /**
     * 创建用户Id。
     */
    @ApiModelProperty(value = "创建用户Id")
    private Long createUserId;

    /**
     * 创建时间。
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改时间。
     */
    @ApiModelProperty(value = "最后修改时间")
    private Date updateTime;

    /**
     * price 范围过滤起始值(>=)。
     */
    @ApiModelProperty(value = "price 范围过滤起始值(>=)")
    private BigDecimal priceStart;

    /**
     * price 范围过滤结束值(<=)。
     */
    @ApiModelProperty(value = "price 范围过滤结束值(<=)")
    private BigDecimal priceEnd;

    /**
     * classHour 范围过滤起始值(>=)。
     */
    @ApiModelProperty(value = "classHour 范围过滤起始值(>=)")
    private Integer classHourStart;

    /**
     * classHour 范围过滤结束值(<=)。
     */
    @ApiModelProperty(value = "classHour 范围过滤结束值(<=)")
    private Integer classHourEnd;

    /**
     * updateTime 范围过滤起始值(>=)。
     */
    @ApiModelProperty(value = "updateTime 范围过滤起始值(>=)")
    private String updateTimeStart;

    /**
     * updateTime 范围过滤结束值(<=)。
     */
    @ApiModelProperty(value = "updateTime 范围过滤结束值(<=)")
    private String updateTimeEnd;
}
