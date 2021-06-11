package com.lanwq.java8.timeprocess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vin lan
 * @className SimpleDateFormatTest
 * @description TODO
 * @createTime 2020-10-29  15:27
 **/
public class SimpleDateFormatTest {
    public static void main(String[] args) throws ParseException {
//        formatDate();
//        System.out.println("------不安全-------");
//        forThreadNotSafetyTest();
//        System.out.println("------局部变量-----");
//        forThreadSafetyTest1();
//        System.out.println("------synchronized-----");
//        forThreadSafetyTest2();
//        System.out.println("------lock-----");
//        forThreadSafetyTest3();
        System.out.println("------ThreadLocal-----");
        forThreadSafetyTest4();
//        System.out.println("------DateTimeFormatter-----");
//        forThreadSafetyTest5();

    }

    public void formatGmtTime() {
        // 时间格式，使用play框架中的form表单来弄的，前端传过来的是形如 202002200000 的字符串，在通过以下注解转换成了timezone为 “GMT+8” 的时间格式
        /*
        import com.fasterxml.jackson.annotation.JsonFormat;
        import play.data.format.Formats;

        @Formats.DateTime(pattern = "yyyyMMddHHmmss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
        Date orderDateFrom;

        @Formats.DateTime(pattern = "yyyyMMddHHmmss")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "GMT+8")
        Date orderDateTo;*/
        // 然后再使用以下格式化成相对应的时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT+8")));
    }

    public static void formatDate() throws ParseException {
        Date currentDate = new Date();
        // 时间格式 MM/dd/yyyy
        String format = new SimpleDateFormat("MM/dd/yyyy").format(currentDate);
        System.out.println(format);
        System.out.println(currentDate.toString().split(" ")[1]);

        // 获取英文的月份，也是根据具体的pattern来的 ，具体的可以参考：https://blog.csdn.net/KingWTD/article/details/48089111
        SimpleDateFormat format2 = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        System.out.println(format2.format(currentDate));

        SimpleDateFormat format3 = new SimpleDateFormat("MMM yyy", Locale.ENGLISH);
        System.out.println(format3.format(currentDate));

        SimpleDateFormat format4 = new SimpleDateFormat("MMyy");
        System.out.println(format4.format(currentDate));
        // 2018-11-14
        SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM-dd");
        format5.setTimeZone(TimeZone.getDefault());
        Date parseDate = format5.parse("2018-12-14");
        System.out.println(parseDate);
    }

    /**
     * 作者：Java中文社群
     * 链接：https://juejin.cn/post/6963048249204670494
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static void forThreadNotSafetyTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> {
                Date date = new Date(finalI * 1000);
                // 希望打印出不重复的值，00:00 00:01----00:09，但是打印出了重复的值，从中可以看出这个是线程不安全的
                System.out.println(Thread.currentThread().getName() + ":" + simpleDateFormat.format(date));
            });
        }
        pool.shutdown();
        /* 原因，查看format方法的源码，可以看到calendar是共享数据（容易发生线程资源竞争，线程安全问题），设置时间的时候会出现
         *   // Convert input date to time field list
         *   // calendar.setTime(date);
         * 线程 1 执行了 calendar.setTime(date) 方法，将用户输入的时间转换成了后面格式化时所需要的时间；
         * 线程 1 暂停执行，线程 2 得到 CPU 时间片开始执行；
         * 线程 2 执行了 calendar.setTime(date) 方法，对时间进行了修改；
         * 线程 2 暂停执行，线程 1 得出 CPU 时间片继续执行，因为线程 1 和线程 2 使用的是同一对象，而时间已经被线程 2 修改了，所以此时当线程 1 继续执行的时候就会出现线程安全的问题了。
         */
    }


    /**
     * 解决方法1：将 SimpleDateFormat 定义为局部变量时，因为每个线程都是独享 SimpleDateFormat 对象的，相当于将多线程程序变成“单线程”程序了，所以不会有线程不安全的问题
     */
    public static void forThreadSafetyTest1() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> {
                Date date = new Date(finalI * 1000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                System.out.println(Thread.currentThread().getName() + " : " + simpleDateFormat.format(date));
            });
        }
        pool.shutdown();
    }

    /**
     * 解决方法2：使用 synchronized 关键字
     */
    public static void forThreadSafetyTest2() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.execute(() -> {
                Date date = new Date(finalI * 1000);
                String result;
                synchronized (simpleDateFormat) {
                    result = simpleDateFormat.format(date);
                }
                System.out.println(Thread.currentThread().getName() + " : " + result);
            });
        }
        pool.shutdown();
    }

    /**
     * 显示加锁，手动进行加锁
     */
    public static void forThreadSafetyTest3() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        // 创建 Lock 锁
        Lock lock = new ReentrantLock();
        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            pool.execute(() -> {
                // 创建时间对象
                Date date = new Date(finalI * 1000);
                // 定义格式化的结果
                String result = null;
                // 加锁
                lock.lock();
                try {
                    // 时间格式化
                    result = simpleDateFormat.format(date);
                } finally {
                    // 释放锁
                    lock.unlock();
                }
                // 打印结果
                System.out.println(result);
            });
        }
        pool.shutdown();
    }

    /**
     * 使用 ThreadLocal 来进行操作
     */
    public static void forThreadSafetyTest4() {
        ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());

        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            pool.execute(() -> {
                // 创建时间对象
                Date date = new Date(finalI * 1000);
                // 定义格式化的结果
                System.out.println(threadLocal.get().format(date));
            });
        }
        pool.shutdown();
    }

    /**
     * 使用 DateTimeFormatter 来进行操作
     */
    public static void forThreadSafetyTest5() {
        // 将date转换成LocalDate
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss");

        // 执行 10 次时间格式化
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            // 线程池执行任务
            pool.execute(() -> {
                // 创建时间对象
                Date date = new Date(finalI * 1000);
                // 定义格式化的结果
//                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                System.out.println(dateTimeFormatter.format(localDateTime));
            });
        }
        pool.shutdown();
    }
}
