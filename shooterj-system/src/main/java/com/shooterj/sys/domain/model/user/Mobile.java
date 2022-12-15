package com.shooterj.sys.domain.model.user;

import com.google.common.base.Preconditions;
import com.shooterj.common.domain.ValueObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Data
public class Mobile implements ValueObject<Mobile> {

    /**
     * 有效性正则
     */
    private static final Pattern VALID_PATTERN = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

    private String mobile;

    public Mobile(String mobile) {
        Preconditions.checkArgument(StringUtils.isBlank(mobile), "手机号不能为空");

        Preconditions.checkArgument(VALID_PATTERN.matcher(mobile).matches(), "手机号格式不正确");

        this.mobile = mobile;
    }

    @Override
    public boolean sameValueAs(Mobile other) {
        return other != null && this.mobile.equals(other.mobile);
    }
}
