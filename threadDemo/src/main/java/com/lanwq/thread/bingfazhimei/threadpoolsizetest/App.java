package com.lanwq.thread.bingfazhimei.threadpoolsizetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Lan
 * @createTime 2024-04-15  16:18
 * 测试CPU密集型、IO密集型
 * 的线程数量
 **/
public class App {
    // 初始化线程池，将线程数量修改为 2、4、8、16、32 查看每个结果
    /**
     * CPU核数 16，i7-10700
     * CPU密集型测试结果
     * 睡眠1s
     * 2
     * 平均每个线程整体花费时间： 31266
     * 平均每个线程执行花费时间： 1227
     * 4
     * 平均每个线程整体花费时间： 15913
     * 平均每个线程执行花费时间： 1224
     * 8
     * 平均每个线程整体花费时间： 8452
     * 平均每个线程执行花费时间： 1251
     * 16
     * 平均每个线程整体花费时间： 4816
     * 平均每个线程执行花费时间： 1311
     * 17
     * 平均每个线程整体花费时间： 4518
     * 平均每个线程执行花费时间： 1293
     * 32
     * 平均每个线程整体花费时间： 3234
     * 平均每个线程执行花费时间： 1527
     * 40
     * 平均每个线程整体花费时间： 3096
     * 平均每个线程执行花费时间： 1708
     * 注释了睡眠1s
     * 2
     * 平均每个线程整体花费时间： 5366
     * 平均每个线程执行花费时间： 209
     * 4
     * 平均每个线程整体花费时间： 3050
     * 平均每个线程执行花费时间： 239
     * 6
     * 平均每个线程整体花费时间： 2009
     * 平均每个线程执行花费时间： 227
     * 8
     * 平均每个线程整体花费时间： 1672
     * 平均每个线程执行花费时间： 262
     * 16
     * 平均每个线程整体花费时间： 1333
     * 平均每个线程执行花费时间： 369
     * 17
     * 平均每个线程整体花费时间： 1336
     * 平均每个线程执行花费时间： 393
     * 32
     * 平均每个线程整体花费时间： 1633
     * 平均每个线程执行花费时间： 740
     * 40
     * 平均每个线程整体花费时间： 1420
     * 平均每个线程执行花费时间： 746
     */
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(40, 40,
            10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        int cores = Runtime.getRuntime().availableProcessors();

        int requestNum = 100;
        System.out.println("CPU核数 " + cores);

        List<Future<?>> futureList = new ArrayList<Future<?>>();

        Vector<Long> wholeTimeList = new Vector<Long>();
        Vector<Long> runTimeList = new Vector<Long>();

        for (int i = 0; i < requestNum; i++) {
            Future<?> future = threadPool.submit(new CPUTypeTest(runTimeList, wholeTimeList));

            //Future<?> future = threadPool.submit(new IOTypeTest(runTimeList, wholeTimeList));
            futureList.add(future);
        }

        for (Future<?> future : futureList) {
            //获取线程执行结果
            future.get(requestNum, TimeUnit.SECONDS);
        }

        long wholeTime = 0;
        for (int i = 0; i < wholeTimeList.size(); i++) {
            wholeTime = wholeTimeList.get(i) + wholeTime;
        }

        long runTime = 0;
        for (int i = 0; i < runTimeList.size(); i++) {
            runTime = runTimeList.get(i) + runTime;
        }

        System.out.println("平均每个线程整体花费时间： " + wholeTime / wholeTimeList.size());
        System.out.println("平均每个线程执行花费时间： " + runTime / runTimeList.size());
    }
}
