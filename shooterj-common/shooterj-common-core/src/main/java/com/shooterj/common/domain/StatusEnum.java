package com.shooterj.common.domain;

import com.shooterj.common.constants.BaseEnum;

import java.util.Optional;

public enum StatusEnum implements BaseEnum<StatusEnum> {

    /**
     * 有效
     */
    ENABLE(0, "有效"),

    /**
     * 禁用
     */
    DISABLE(1, "禁用");


    private Integer code;
    private String name;

    StatusEnum(Integer code, String value) {
        this.code = code;
        this.name = value;
    }







    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Optional<StatusEnum> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(StatusEnum.class, code));
    }
}
