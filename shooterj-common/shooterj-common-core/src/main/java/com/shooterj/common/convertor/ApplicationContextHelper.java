package com.shooterj.common.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationContextHelper implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextHelper.class);
    private static DefaultListableBeanFactory springFactory;
    private static ApplicationContext context;

    public ApplicationContextHelper() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        setContext(applicationContext);
        if (applicationContext instanceof AbstractRefreshableApplicationContext) {
            AbstractRefreshableApplicationContext springContext = (AbstractRefreshableApplicationContext) applicationContext;
            setFactory((DefaultListableBeanFactory) springContext.getBeanFactory());
        } else if (applicationContext instanceof GenericApplicationContext) {
            GenericApplicationContext springContext = (GenericApplicationContext) applicationContext;
            setFactory(springContext.getDefaultListableBeanFactory());
        }

    }

    private static void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    private static void setFactory(DefaultListableBeanFactory springFactory) {
        ApplicationContextHelper.springFactory = springFactory;
    }

    public static DefaultListableBeanFactory getSpringFactory() {
        return springFactory;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> void asyncInstanceSetter(Class<T> type, Object target, String setterMethod) {
        if (!setByMethod(type, target, setterMethod)) {
            AtomicInteger counter = new AtomicInteger(0);
            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, (r) -> {
                return new Thread(r, "sync-setter");
            });
            executorService.scheduleAtFixedRate(() -> {
                boolean success = setByMethod(type, target, setterMethod);
                if (success) {
                    executorService.shutdown();
                } else if (counter.addAndGet(1) > 240) {
                    LOGGER.error("Setter field [{}] in [{}] failure because timeout.", setterMethod, target.getClass().getName());
                    executorService.shutdown();
                }

            }, 0L, 1L, TimeUnit.SECONDS);
        }
    }

    public static void asyncStaticSetter(Class<?> type, Class<?> target, String targetField) {
        if (!setByField(type, target, targetField)) {
            AtomicInteger counter = new AtomicInteger(0);
            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, (r) -> {
                return new Thread(r, "sync-setter");
            });
            executorService.scheduleAtFixedRate(() -> {
                boolean success = setByField(type, target, targetField);
                if (success) {
                    executorService.shutdown();
                } else if (counter.addAndGet(1) > 240) {
                    LOGGER.error("Setter field [{}] in [{}] failure because timeout.", targetField, target.getName());
                    executorService.shutdown();
                }

            }, 0L, 1L, TimeUnit.SECONDS);
        }
    }

    private static boolean setByMethod(Class<?> type, Object target, String targetMethod) {
        if (getContext() != null) {
            try {
                Object obj = getContext().getBean(type);
                Method method = target.getClass().getDeclaredMethod(targetMethod, type);
                method.setAccessible(true);
                method.invoke(target, obj);
                LOGGER.info("Async set field [{}] in [{}] success by method.", targetMethod, target.getClass().getName());
                return true;
            } catch (NoSuchMethodException var5) {
                LOGGER.error("Not found method [{}] in [{}].", new Object[]{targetMethod, target.getClass().getName(), var5});
            } catch (NoSuchBeanDefinitionException var6) {
                LOGGER.error("Not found bean [{}] for [{}].", new Object[]{type.getName(), target.getClass().getName(), var6});
            } catch (Exception var7) {
                LOGGER.error("Async set field [{}] in [{}] failure by method.", new Object[]{targetMethod, target.getClass().getName(), var7});
            }
        }

        return false;
    }

    private static boolean setByField(Class<?> type, Class<?> target, String targetField) {
        if (getContext() != null) {
            try {
                Object obj = getContext().getBean(type);
                Field field = target.getDeclaredField(targetField);
                field.setAccessible(true);
                field.set(target, obj);
                LOGGER.info("Async set field [{}] in [{}] success by field.", targetField, target.getName());
                return true;
            } catch (NoSuchFieldException var5) {
                LOGGER.error("Not found field [{}] in [{}].", new Object[]{targetField, target.getName(), var5});
            } catch (NoSuchBeanDefinitionException var6) {
                LOGGER.error("Not found bean [{}] for [{}].", new Object[]{type.getName(), target.getName(), var6});
            } catch (Exception var7) {
                LOGGER.error("Async set field [{}] in [{}] failure by field.", new Object[]{targetField, target.getName(), var7});
            }
        }

        return false;
    }
}
