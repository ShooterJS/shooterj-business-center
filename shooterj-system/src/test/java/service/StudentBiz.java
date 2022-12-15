package service;

import com.shooterj.common.constants.BaseEnum;
import com.shooterj.extension.executor.BizScene;

import java.util.Optional;

public enum StudentBiz implements BaseEnum<StudentBiz>, BizScene {
    LOCAL(1, "local"),
    REMOTE(2, "remote");

    private Integer code;
    private String name;

    StudentBiz(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String getBizId() {
        return this.name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<StudentBiz> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(StudentBiz.class, code));
    }
}
