package mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("hit_tag_record")
@ApiModel(value = "HitTagRecord对象", description = "")
public class HitTagRecordDo implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name="账号")
    private String account;

    @ApiModelProperty(name="姓名")
    private String userName;

    @ApiModelProperty(name="岗位编码")
    private String postCode;

    @ApiModelProperty(name="标签编码")
    private Integer tagCode;

    @ApiModelProperty(name="标签名称")
    private String tagName;

    @ApiModelProperty(name="功能编码")
    private String funcCode;

    @ApiModelProperty(name="标签目的编码")
    private String targetCode;

    @ApiModelProperty(name="创建时间")
    @JsonIgnore
    private LocalDateTime createTime;


}
