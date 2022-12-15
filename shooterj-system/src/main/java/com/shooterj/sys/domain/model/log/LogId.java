package com.shooterj.sys.domain.model.log;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

/**
 * 日志ID
 **/
public class LogId implements ValueObject<LogId> {

    private String id;

    public LogId(final String id) {
        Preconditions.checkNotNull(id,"账号id不能为空");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(LogId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
