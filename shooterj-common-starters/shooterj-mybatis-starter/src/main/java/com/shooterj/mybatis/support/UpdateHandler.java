package com.shooterj.mybatis.support;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface UpdateHandler<T> {

    public Executor<T> update(Consumer<T> consumer);

}
