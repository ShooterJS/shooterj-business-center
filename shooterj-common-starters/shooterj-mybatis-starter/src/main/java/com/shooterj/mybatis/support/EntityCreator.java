package com.shooterj.mybatis.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.base.Preconditions;
import com.shooterj.common.validator.CreateGroup;
import io.vavr.control.Try;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EntityCreator<T> extends BaseEntityOperation implements UpdateHandler<T>, Executor<T> {

    BaseMapper baseMapper;
    private Consumer<T> successHook = t -> System.out.println("create success");
    private Consumer<? super Throwable> errorHook = e -> e.printStackTrace();


    public EntityCreator(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    private T t;

    public UpdateHandler<T> create(Supplier<T> supplier) {
        this.t = supplier.get();
        return this;
    }

    @Override
    public Executor<T> update(Consumer<T> consumer) {
        Preconditions.checkArgument(Objects.nonNull(this.t), "entity must supply");
        consumer.accept(this.t);
        return this;
    }


    @Override
    public Optional<T> execute() {
        doValidate(this.t, CreateGroup.class);
        T save = Try.of(() -> {
            baseMapper.insert(this.t);
            return this.t;
        })
                .onSuccess(successHook)
                .onFailure(errorHook).getOrNull();

        return Optional.ofNullable(save);
    }

    public Executor<T> successHook(Consumer<T> consumer) {
        this.successHook = consumer;
        return this;
    }

    public Executor<T> errorHook(Consumer<? super Throwable> consumer) {
        this.errorHook = consumer;
        return this;
    }


}
