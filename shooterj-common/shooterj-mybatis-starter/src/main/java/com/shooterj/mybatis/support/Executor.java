package com.shooterj.mybatis.support;

import java.util.Optional;
import java.util.function.Consumer;

public interface Executor<T> {

     public Optional<T> execute();
     public Executor<T> successHook(Consumer<T> consumer);

     Executor<T> errorHook(Consumer<? super Throwable> consumer);
}
