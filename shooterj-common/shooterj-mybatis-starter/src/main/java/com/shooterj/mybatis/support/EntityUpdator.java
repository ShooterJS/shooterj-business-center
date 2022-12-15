package com.shooterj.mybatis.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.base.Preconditions;
import com.shooterj.common.validator.group.UpdateGroup;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class EntityUpdator<T> extends BaseEntityOperation implements UpdateHandler<T>, Executor<T>, Loader<T> {


    BaseMapper<T> baseMapper;
    private T entity;
    private Consumer<T> successHook = t -> System.out.println("update success");
    private Consumer<? super Throwable> errorHook = e -> e.printStackTrace();

    public EntityUpdator(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }


    @Override
    public Executor<T> update(Consumer<T> consumer) {
        Preconditions.checkArgument(Objects.nonNull(this.entity), "entity is null");
        consumer.accept(this.entity);
        return this;
    }

    @Override
    public UpdateHandler<T> load(Supplier<T> supplier) {
        this.entity = supplier.get();
        return this;
    }

    @Override
    public Optional<T> execute() {
        doValidate(this.entity, UpdateGroup.class);
        T save = Try.of(() -> {
            baseMapper.updateById(entity);
            return this.entity;
        })
                .onSuccess(successHook)
                .onFailure(errorHook).getOrNull();
        return Optional.ofNullable(save);
    }

    @Override
    public Executor<T> successHook(Consumer<T> consumer) {
        this.successHook = consumer;
        return this;
    }

    @Override
    public Executor<T> errorHook(Consumer<? super Throwable> consumer) {
        this.errorHook = consumer;
        return this;
    }


}
