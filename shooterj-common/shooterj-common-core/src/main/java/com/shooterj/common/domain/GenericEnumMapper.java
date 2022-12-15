package com.shooterj.common.domain;


public class GenericEnumMapper {

    public String asString(StatusEnum statusEnum) {
        return statusEnum.getName();
    }

    public StatusEnum asEnumStatus(Integer code) {
        return StatusEnum.of(code).orElse(StatusEnum.ENABLE);
    }
}
