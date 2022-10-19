package com.shooterj.sys.domain.customer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Customer implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String grade;

    private Integer userStatus;


    public void init() {
        setUserStatus(1);
    }

    public void doUpdate(CustomerUpdater updater) {
        setUsername(updater.getUsername());
    }
}
