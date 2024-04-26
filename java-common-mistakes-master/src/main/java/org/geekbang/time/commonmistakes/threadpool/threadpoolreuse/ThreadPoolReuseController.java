package org.geekbang.time.commonmistakes.threadpool.threadpoolreuse;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("threadpoolreuse")
@Slf4j
public class ThreadPoolReuseController {

    private AtomicInteger count = new AtomicInteger(0);

    //    ThreadPoolExecutor threadPool = ThreadPoolHelper.getRightThreadPool();
    @GetMapping("wrong")
    public String wrong() throws InterruptedException {
        log.info("wrong: " + count.getAndIncrement());
        // 错误的用法：这个用法导致线程池一直在扩容，导致 OOM 问题
//        ThreadPoolExecutor threadPool = ThreadPoolHelper.getThreadPool();
        // 正确的用法应该是：
        ThreadPoolExecutor threadPool = ThreadPoolHelper.getRightThreadPool();
        // 如果我们不小心每次都创建了这样一个自定义的线程池（10 核心线程，50 最大线程，2 秒回收的），反复执行测试接口线程，最终可以被回收吗？会出现 OOM 问题吗？
        // 答案是：会出现 OOM 问题。即使是自定义线程池，核心线程是不会回收的，每次需要10个线
        //程，刚好是核心线程数，因此每次请求都会创建10个核心线程数的线程池，请求次数多了。 ThreadPoolExecutor回收不了，可以看看其源码，工作线程Worker是内部类，只要它
        //活着，换句话说线程在跑，就会阻止ThreadPoolExecutor回收，所以其实ThreadPoolExecutor
        //是无法回收的，并不能认为ThreadPoolExecutor没有引用就能回收
//        ThreadPoolExecutor threadPool = ThreadPoolHelper.getRightThreadPool2();
        IntStream.rangeClosed(1, 10).forEach(i -> {
            threadPool.execute(() -> {
                String payload = IntStream.rangeClosed(1, 1000000)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                }
                log.debug(payload);
                log.info("Thread: " + Thread.currentThread().getName() + " - done");
            });
        });
        return "OK";
    }

    static class ThreadPoolHelper {
        private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 50,
                2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(350),
                new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get());

        public static ThreadPoolExecutor getThreadPool() {
            return (ThreadPoolExecutor) Executors.newCachedThreadPool();
        }

        static ThreadPoolExecutor getRightThreadPool() {
            return threadPoolExecutor;
        }

        static ThreadPoolExecutor getRightThreadPool2() {
            return new ThreadPoolExecutor(
                    10, 50,
                    2, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get());
        }
    }

    @PostConstruct
    public void init() {
//        printStats(threadPool);
    }

    private void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

            log.info("=========================");
        }, 0, 1, TimeUnit.SECONDS);
    }
}
