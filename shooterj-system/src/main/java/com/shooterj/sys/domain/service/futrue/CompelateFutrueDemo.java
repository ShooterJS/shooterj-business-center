package com.shooterj.sys.domain.service.futrue;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

/**
 * @author: ShooterJ
 * @description 异步编排demo
 * @date: 2023/6/27 10:22
 */
@Component
public class CompelateFutrueDemo {

   /* public static void main(String[] args) {
        Instant start = Instant.now(); // 记录开始时间
        // 异步执行任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 1";
        });
        // 异步执行任务2
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 2";
        });
        // 异步执行任务3
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 3";
        });
        // 等待所有任务完成
        CompletableFuture.allOf(future1, future2, future3).join();
        Instant end = Instant.now(); // 记录结束时间

        // 获取任务的结果
        String result1 = future1.join();
        String result2 = future2.join();
        String result3 = future3.join();

        // 打印任务的结果
        System.out.println("Result of Task 1: " + result1);
        System.out.println("Result of Task 2: " + result2);
        System.out.println("Result of Task 3: " + result3);
        // 统计运行时长
        Duration duration = Duration.between(start, end);
        System.out.println("Total duration: " + duration.toMillis() + " milliseconds");
    }*/

    public static void main(String[] args) {
        Instant start = Instant.now(); // 记录开始时间
        // 异步执行任务1
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 1";
        });
        // 异步执行任务2
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 2";
        });
        // 异步执行任务3
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            cusSleep();
            return "Result of Task 3";
        });

        // 等待第一个服务完成
        future1.thenComposeAsync(service1Result -> {
                    // 第一个服务完成后，同时执行后两个服务
                    CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future2, future3);
                    CompletableFuture<String> future = combinedFuture.thenApplyAsync(ignored -> service1Result);
                    return future;
                })
                .thenAcceptAsync(finalResult -> {
                    // 所有服务完成后的操作
                    System.out.println("Final result: " + finalResult);
                });

        // 等待所有服务完成
        CompletableFuture.allOf(future1, future2, future3).join();

        System.out.println("All services completed.");

        // 记录结束时间
        Instant end = Instant.now();

        // 获取任务的结果
        String result1 = future1.join();
        String result2 = future2.join();
        String result3 = future3.join();

        // 打印任务的结果
        System.out.println("Result of Task-1: " + result1);
        System.out.println("Result of Task-2: " + result2);
        System.out.println("Result of Task-3: " + result3);

        // 统计运行时长
        Duration duration = Duration.between(start, end);
        System.out.println("Total duration: " + duration.toMillis() + " milliseconds");
    }

    private static void cusSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

   


}
