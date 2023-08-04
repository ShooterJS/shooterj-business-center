package com.shooterj.sys.domain.model.user;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;

/**
 * 账号ID
 *
 * @author shooterj
 **/
public class AccountId implements ValueObject<AccountId> {

    private String id;

    public AccountId(final String id) {
        Preconditions.checkNotNull(id,"账号id不能为空");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean sameValueAs(AccountId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
