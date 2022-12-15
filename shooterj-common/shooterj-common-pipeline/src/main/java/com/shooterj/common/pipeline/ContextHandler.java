package com.shooterj.common.pipeline;

/**
 * 定义上下文处理器
 */
public interface ContextHandler<T extends PipelineContext> {

    boolean handle(T context);
}
