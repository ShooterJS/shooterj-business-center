package com.shooterj.sys.domain.model.user;

import cn.hutool.crypto.digest.MD5;
import com.shooterj.common.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

public class Password implements ValueObject<Password> {

    public static final String DEFAULT = "123456";

    /**
     * 密码
     */
    private String password;

    public Password(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        this.password = password;
    }

    public static Password create(String passwordStr) {
        String password = MD5.create().digestHex(passwordStr);
        return new Password(password);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean sameValueAs(Password other) {
        return other != null && this.password.equals(other.password);
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                '}';
    }
}
