package com.shooterj.mybatis.support;

import java.util.function.Supplier;

public interface Loader<T> {
    public UpdateHandler<T> load(Supplier<T> supplier);
}
