package com.shooterj.extension.executor;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ServiceExecutor {

  /**
   * 执行void 方法
   *
   * @param clazz
   * @param bizScene
   * @param consumer
   * @param <S>
   */
  <S> void execute( BizScene bizScene, Consumer<S> consumer);

  /**
   * 执行有返回值的方法
   *
   * @param clazz
   * @param bizScene
   * @param function
   * @param <R>
   * @param <S>
   * @return
   */
  <R, S> R execute( BizScene bizScene, Function<S, R> function);

}
