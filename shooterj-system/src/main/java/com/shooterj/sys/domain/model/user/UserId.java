package com.shooterj.sys.domain.model.user;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import lombok.Data;

/**
 * 用户ID
 **/
@Data
public class UserId implements ValueObject<UserId> {

    /**
     * 超级管理员角色
     */
    public static final String SYS_ADMIN = "1";

    private String id;

    public UserId(final String id) {
        Preconditions.checkNotNull(id,"用户id不能为空");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isSysAdmin() {
        return SYS_ADMIN.equals(id);
    }

    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
