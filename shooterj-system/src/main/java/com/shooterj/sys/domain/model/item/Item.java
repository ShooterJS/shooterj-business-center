package com.shooterj.sys.domain.model.item;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ShooterJ
 * @description
 */
@Data
@Builder
@TableName("t_item")
public class Item {
    @TableId
    Integer id;
    String name;
    int age;
}
