package com.shooterj.mybatis.support;

import java.util.function.Consumer;

public interface UpdateHandler<T> {

    public Executor<T> update(Consumer<T> consumer);

}
