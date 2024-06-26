package java8.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Lan
 * @createTime 2023-09-21  14:43
 * 烧水泡茶的例子：
 * 洗水壶1分钟->烧开水15分钟->泡茶
 * ^
 * |
 * 洗茶壶1分钟->洗茶杯2分钟 ->拿茶叶1分钟
 * 泡茶需要等待茶叶
 * <p>
 * 用两个线程 T1 和 T2 来完成烧水泡茶程序，
 * T1 负责洗水壶、烧开水、泡茶这三道工序，T2 负责洗茶壶、洗茶杯、拿茶叶三道工序，
 * 其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序。
 **/
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务T2的FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));

        // 线程T1执行任务ft2
        Thread T1 = new Thread(ft2);
        T1.start();
        // 线程T2执行任务ft1
        Thread T2 = new Thread(ft1);
        T2.start();
        // 等待线程T1执行结果
        System.out.println(ft1.get());

    }
}

// T1Task需要执行的任务：
// 洗水壶、烧开水、泡茶
class T1Task implements Callable<String> {
    FutureTask<String> ft2;

    // T1任务需要T2任务的FutureTask
    T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1:洗水壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1:烧开水...");
        TimeUnit.SECONDS.sleep(15);
        // 获取T2线程的茶叶
        String tf = ft2.get();
        System.out.println("T1:拿到茶叶:" + tf);

        System.out.println("T1:泡茶...");
        return "上茶:" + tf;
    }
}

// T2Task需要执行的任务:
// 洗茶壶、洗茶杯、拿茶叶
class T2Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("T2:洗茶壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2:洗茶杯...");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("T2:拿茶叶...");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}

