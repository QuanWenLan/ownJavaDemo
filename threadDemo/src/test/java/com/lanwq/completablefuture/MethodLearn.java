package com.lanwq.completablefuture;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Lan
 * @createTime 2023-09-21  11:16
 * CompletableFuture 常见方法及使用
 * 而CompletableFuture是对Future的扩展和增强。CompletableFuture实现了Future接口，并在此基础上进行了丰富的扩展，完美弥补了Future的局限性，
 * 同时CompletableFuture实现了对任务编排的能力。借助这项能力，可以轻松地组织不同任务的运行顺序、规则以及方式。从某种程度上说，这项能力是它的核心能力。
 * 而在以往，虽然通过CountDownLatch等工具类也可以实现任务的编排，但需要复杂的逻辑处理，不仅耗费精力且难以维护。
 * <p>
 * CompletionStage接口定义了任务编排的方法，执行某一阶段，可以向下执行后续阶段。异步执行的，默认线程池是ForkJoinPool.commonPool()，
 * 但为了业务之间互不影响，且便于定位问题，强烈推荐使用自定义线程池。
 * <p>
 * 默认情况下CompletableFuture会使用公共的ForkJoinPool线程池，这个线程池默认创建的线程数是 CPU 的核数
 * （也可以通过 JVM option:-Djava.util.concurrent.ForkJoinPool.common.parallelism 来设置ForkJoinPool线程池的线程数）。
 * 如果所有CompletableFuture共享一个线程池，那么一旦有任务执行一些很慢的 I/O 操作，就会导致线程池中所有线程都阻塞在 I/O 操作上，从而造成线程饥饿，
 * 进而影响整个系统的性能。所以，强烈建议你要根据不同的业务类型创建不同的线程池，以避免互相干扰。
 * <p>
 * 版权声明：本文为CSDN博主「sermonlizhi」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：<a href="https://blog.csdn.net/sermonlizhi/article/details/123356877">CompletableFuture使用详解</a>
 * 可参考学习：<a href="https://juejin.cn/post/7168261825165787149">简述CompletableFuture异步任务编排</a>
 **/
public class MethodLearn {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactory() {
        private final AtomicInteger THREAD_NUM = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            // 设置为守护线程，main线程结束就跟着一起结束，否则main函数结束jvm还在
            t.setDaemon(true);
            t.setName("completable-future-test-Thread-" + THREAD_NUM.incrementAndGet());
            return t;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            String stringToPrint = "Educative";
            System.out.println("----\nsupplyAsync first future - " + stringToPrint);
            executionThread();
            return stringToPrint;
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            String stringToPrint = "Edpresso";
            System.out.println("----\nsupplyAsync second future - " + stringToPrint);
            executionThread();
            return stringToPrint;
        });

        BiConsumer<String, String> stringBiConsumer = (res1, res2) -> System.out.printf("---\ncompletableFuture1 result - %s\ncompletableFuture2 result - %s", res1, res2);

        completableFuture1.thenAcceptBoth(completableFuture2, stringBiConsumer);
        sleep(3000);
    }

    @Test
    public void createAsyncTask() {
        Supplier<String> supplier = () -> "supplier";
        System.out.println(supplier.get());
        // ====== 创建任务 ======
        // runAsync 无返回结果
        Runnable runnable = () -> System.out.println("无返回结果的异步任务");
        CompletableFuture.runAsync(runnable, THREAD_POOL_EXECUTOR);

        // supplyAsync() 以Supplier函数式接口类型为参数，返回结果类型为U；Supplier接口的 get()是有返回值的(会阻塞)
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello world";
        });

        // ====== 获取结果 ======
        // 获取用来获取CompletableFuture异步之后的返回值
        // 1 使用 get() 方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        // 2 使用 join() 抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出
        System.out.println(future.join());


    }

    /**
     * ====== 结果处理 ======
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，我们可以执行特定的 Action；
     * 1 Action的类型是BiConsumer<? super T,? super Throwable>，它可以处理正常的计算结果，或者异常情况。
     * 2 方法不以Async结尾，意味着Action使用相同的线程执行，而Async可能会使用其它的线程去执行(如果使用相同的线程池，也可能会被同一个线程选中执行)。
     * 3 这几个方法都会返回CompletableFuture，当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常
     */
    @Test
    public void handleResult() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束！");
            return "test";
        });
        // 任务完成或异常方法完成时执行该方法
        // 如果出现了异常,任务结果为null
        future.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable action) {
                System.out.println(t + " 执行完成！");
            }
        });
        // 出现异常时先执行该方法
        CompletableFuture<String> exceptionallyFuture = future.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable t) {
                System.out.println("执行失败：" + t.getMessage());
                return "异常xxxx";
            }
        });

        try {
            System.out.println(exceptionallyFuture.get());

            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取结果
     */
    public void testComplete() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "abc";
        });
        try {
            // 等待一段时间，但是实际的异步任务并没有完成
            TimeUnit.SECONDS.sleep(1);
            System.out.println(completableFuture.get());
            completableFuture.get(2L, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        System.out.println(completableFuture.join());
        // 如果任务还没有完成，返回一个替代值
        completableFuture.getNow("xxx");
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());
    }


    /**
     * ===== 结果转换，对结果进行处理 =====
     * 计算结果存在依赖关系
     * 将上一段任务的执行结果作为下一阶段任务的入参参与重新计算，产生新的结果。
     * thenApply转换的是泛型中的类型，返回的是同一个CompletableFuture；
     * thenCompose将内部的CompletableFuture调用展开来并使用上一个CompletableFuture调用的结果在下一步的CompletableFuture调用中进行运算，
     * 是生成一个新的CompletableFuture。
     * thenRun(Runnable runnable)       	任务 A 执行完执行 B，并且 B 不需要 A 的结果
     * thenAccept(Consumer action)	任务 A 执行完执行 B，B 需要 A 的结果，但是任务 B 无返回值
     * thenApply(Function fn)			任务 A 执行完执行 B，B 需要 A 的结果，同时任务 B 有返回值
     */
    @Test
    public void testThenApply() {
        // thenApply 或 thenApplyAsync 接收一个函数作为参数，使用该函数处理上一个CompletableFuture调用的结果，并返回一个具有处理结果的Future对象。
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        });
        System.out.println(future);

        CompletableFuture<Integer> future2 = future.thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        }).handle((value, e)->{
            // thenApply 中途出现错误了之后会走不下去，handle 则可以继续往下走
            System.out.println("handle 情况");
            return value + 100;
        });
        try {
            System.out.println("future == future2: " + (future == future2));
            System.out.println(future.get());
            System.out.println(future2.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testThenCompose() {
        // thenCompose or thenComposeAsync 的参数为一个返回CompletableFuture实例的函数，该函数的参数是先前计算步骤的结果。
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(30);
                System.out.println("第一次运算：" + number);
                return number;
            }
        });
        CompletableFuture<Integer> future4 = future3.thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                System.out.println(param);
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = param * 2;
                        System.out.println("第二次运算：" + number);
                        return number;
                    }
                });
            }
        });
        try {
            System.out.println("future3 == future4 : " + (future3 == future4));
            System.out.println(future3.get());
            System.out.println(future4.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ===== 结果消费 =====
     * 结果消费系列函数只对结果执行 action，而不返回新的计算值
     */
    @Test
    public void testThenAccept() {
        // thenAccept()：对单个结果进行消费
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一次运算：" + number);
            return number;
        }).thenAccept(number -> System.out.println("第二次运算：" + number * 5));
    }

    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void executionThread() {
        System.out.println("Thread execution - " + Thread.currentThread().getName());
    }

    @Test
    public void testThenAcceptBoth() {
        // thenAcceptBoth()：对两个结果进行消费，
        // 当两个CompletionStage都正常完成计算的时候，就会执行提供的action消费两个异步的结果。
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                System.out.println(number);
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1结果：" + number);
            return number;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(3) + 1;
            try {
                System.out.println(number);
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务2结果：" + number);
            return number;
        });
        // 注意需要等待结果执行完毕，如果不等待的话则会没有结果输出
        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (x, y) -> System.out.println("最终结果：" + (x + y)));
        future.join();
    }

    @Test
    public void testThenRun() {
        // thenRun()：不关心结果，只对结果执行Action，与thenAccept不同的是，thenRun会在上一阶段 CompletableFuture计算完成的时候执行一个Runnable，
        // 而Runnable并不使用该CompletableFuture计算的结果。
        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            System.out.println("第一阶段：" + number);
            return number;
        }).thenRun(() -> System.out.println("thenRun 执行"));
    }

    /**
     * ===== 结果组合 =====
     * runAfterBoth：不会把执行结果当做方法入参，且没有返回值
     * thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
     * thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
     */
    @Test
    public void testThenCombine() {
        // 合并两个线程任务的结果，并进一步处理。
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("任务1结果：" + number);
                return number;
            }
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                System.out.println("任务2结果：" + number);
                return number;
            }
        });
        CompletableFuture<Integer> result = future1.thenCombine(future2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer x, Integer y) {
                return x + y;
            }
        });

        try {
            System.out.println("组合后结果：" + result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ===== 任务交互 =====
     * 线程交互指将两个线程任务获取结果的速度相比较，按一定的规则进行下一步处理
     * runAfterEither：不会把执行结果当做方法入参，且没有返回值
     * acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
     * applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
     */
    @Test
    public void testApplyToEither() {
        // 两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的转化操作。
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务1结果:" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务2结果:" + number);
                return number;
            }
        });

        Integer result = future1.applyToEither(future2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer number) {
                System.out.println("最快结果：" + number);
                return number * 2;
            }
        }).join();
        System.out.println(result);
    }

    @Test
    public void testAcceptEither() throws ExecutionException, InterruptedException {
        // 两个线程任务相比较，先获得执行结果的，就对该结果进行下一步的消费操作。
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第一阶段：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(10) + 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二阶段：" + number);
                return number;
            }
        });

        future1.acceptEither(future2, new Consumer<Integer>() {
            @Override
            public void accept(Integer number) {
                System.out.println("最快结果：" + number);
            }
        }).get();
        // 无返回值
    }


    @Test
    public void testRunAfterEither() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(5);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务1结果：" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(5);
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务2结果:" + number);
                return number;
            }
        });

        future1.runAfterEither(future2, new Runnable() {
            @Override
            public void run() {
                System.out.println("已经有一个任务完成了");
            }
        }).join();
        // 无返回值
    }

    /**
     * allOf：等待所有任务完成
     * anyOf：只要有一个任务完成
     */
    @Test
    public void testAnyOf() {
        Random random = new Random();
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(random.nextInt(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });
        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAllOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成！";
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2);
        try {
            combinedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void te() {
        System.out.println(true ? 98 : 'a');
        System.out.println(false ? 1.0 : 'a');
    }
}
