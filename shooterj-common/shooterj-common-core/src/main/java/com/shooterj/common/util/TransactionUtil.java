package com.shooterj.common.util;

/**
 * @author: ShooterJ
 * @description 编程式事务工具类
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Consumer;

@Component
public class TransactionUtil {

    private final PlatformTransactionManager transactionManager;

    @Autowired
    public TransactionUtil(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public TransactionStatus begin() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        return transactionManager.getTransaction(transactionDefinition);
    }

    public void commit(TransactionStatus transactionStatus) {
        transactionManager.commit(transactionStatus);
    }

    public void rollback(TransactionStatus transactionStatus) {
        transactionManager.rollback(transactionStatus);
    }

    public static <T> void applyWithNewTransaction(Consumer<T> consumer, T value) {
        PlatformTransactionManager platformTransactionManager = AppUtil.getBean(PlatformTransactionManager.class);
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new TransactionTemplate(platformTransactionManager, definition));
        try {
            consumer.accept(value);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        } finally {
            platformTransactionManager.commit(transactionStatus);
        }
    }

}

