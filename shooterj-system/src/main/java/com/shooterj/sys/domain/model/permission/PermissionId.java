package com.shooterj.sys.domain.model.permission;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

public class PermissionId implements ValueObject<PermissionId> {
    private String id;

    public PermissionId(final String id) {
        Preconditions.checkNotNull(id,"权限id不能为空");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(PermissionId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
