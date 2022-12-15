package com.shooterj.sys.domain.model.tenant;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class TenantCode implements ValueObject<TenantCode> {

    private String code;

    public String getCode() {
        return code;
    }

    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    public TenantCode(String code) {
        Preconditions.checkArgument(StringUtils.isBlank(code), "租户编码不能为空");

        Preconditions.checkArgument(VALID_PATTERN.matcher(code).matches(), "编码格式不正确");

        this.code = code;
    }

    @Override
    public boolean sameValueAs(TenantCode other) {
        return other != null && this.code.equals(other.code);
    }
}
