package com.shooterj.sys.domain.model.tenant;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class TenantName implements ValueObject<TenantName> {

    private String name;

    public TenantName(String name) {
        Preconditions.checkArgument(StringUtils.isBlank(name),"租户名不能为空");
        this.name = name;
    }

    @Override
    public boolean sameValueAs(TenantName other) {
        return other.name!=null && this.name.equals(other.name);
    }
}
