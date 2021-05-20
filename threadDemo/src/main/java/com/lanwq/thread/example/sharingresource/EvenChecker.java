package com.lanwq.thread.example.sharingresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vin lan
 * @className EvenChecker
 * @description 偶数检查器
 * @createTime 2021-03-15  15:20
 **/
public class EvenChecker implements Runnable {

    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel(); // cancels all evenCheckers
            }
        }
    }

    // test any type of IntGenerator
    public static void test(IntGenerator gp, int count) {
        System.out.println("press Control-C exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(gp, i));
        }
        executorService.shutdown();
    }

    // default value for count
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
/**
 * 在上面例子中可以被撤销的类不是Runnable，而所有依赖于IntGenerator对象的EvenChecker任务将测试它，
 * 以查看它是否已经被撤销。通过run方法中的这种方式，共享公共资源（IntGenerator）的任务可以观察
 * 该资源的终止信号。这可以消除所谓的竞争条件，即两个或更多的任务竞争的响应某个条件，因此产生冲突或
 * 不一致结果的情况。
 */
