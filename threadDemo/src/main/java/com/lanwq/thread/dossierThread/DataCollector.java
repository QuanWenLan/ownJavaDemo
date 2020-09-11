package com.lanwq.thread.dossierThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DataCollector
 * @Description TODO 数据收集器
 * @Author lanwenquan
 * @Date 2020/4/24 9:39
 */
public class DataCollector implements Callable {

    protected BlockingQueue<DossierResult> resultQueue = new LinkedBlockingQueue<DossierResult>();

    protected RequestContext context;
    public static AtomicInteger count = new AtomicInteger(2);


    public RequestContext getContext() {
        return context;
    }

    public void setContext(RequestContext context) {
        this.context = context;
    }

    @Override
    public Object call() {
        System.out.println("数据收集器开始执行数据收集...");
        do {
            for (DossierResult result :
                    resultQueue) {
                System.out.println("结果数据：" + result);
            }
        } while (context.getCount().get() > 0);

        System.out.println("数据收集器数据收集完成....");
        return "99999";
    }

    public void collect(DossierResult data) {

        this.resultQueue.add(data);
    }


}