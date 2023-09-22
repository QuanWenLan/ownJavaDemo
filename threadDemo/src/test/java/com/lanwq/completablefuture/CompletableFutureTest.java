package com.lanwq.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * @author Lan
 * @createTime 2023-09-21  14:56
 * T1 负责洗水壶、烧开水、泡茶这三道工序，T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，
 * 其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。
 **/
public class CompletableFutureTest {
    public static void main(String[] args) {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("T1:洗水壶...");
                TimeUnit.SECONDS.sleep(1);

                System.out.println("T1:洗水壶...");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> t2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("T2:洗茶壶...");
                TimeUnit.SECONDS.sleep(1);

                System.out.println("T2:洗茶杯...");
                TimeUnit.SECONDS.sleep(2);

                System.out.println("T2:拿茶叶...");
                TimeUnit.SECONDS.sleep(1);
                return "龙井";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // t1 需要等 t2 执行完
        CompletableFuture<String> f3_1 = t2.thenCombine(t1, new BiFunction<String, Void, String>() {
            @Override
            public String apply(String s, Void unused) {
                System.out.println("T1:拿到茶叶:" + s);
                System.out.println("T1:泡茶...");
                return "上茶: " + s;
            }
        });
        // 等待任务3执行结果
        System.out.println(f3_1.join());

        System.out.println("=================\n");
        CompletableFuture<Void> f3_2 = t1.thenCombine(t2, new BiFunction<Void, String, Void>() {
            @Override
            public Void apply(Void unused, String s) {
                System.out.println("T1:拿到茶叶:" + s);
                System.out.println("T1:泡茶...");
                return unused;
            }
        });
        // 等待任务3执行结果
        System.out.println(f3_2.join());

        System.out.println("=================\n");
        CompletableFuture<String> f3_3 = t1.thenCombine(t2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        // 等待任务3执行结果
        System.out.println(f3_3.join());
    }
}
