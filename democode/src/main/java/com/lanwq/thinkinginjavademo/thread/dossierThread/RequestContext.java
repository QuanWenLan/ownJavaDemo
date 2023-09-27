package com.lanwq.thinkinginjavademo.thread.dossierThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RequestContext
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/24 10:22
 */
public class RequestContext {
    private DataCollector collector;

    private Boolean isComplete;

    private AtomicInteger count=new AtomicInteger(2);

    public RequestContext(DataCollector collector) {
        this.collector = collector;
        collector.setContext(this);
    }

    public DataCollector getCollector() {
        return collector;
    }

    public void setCollector(DataCollector collector) {
        this.collector = collector;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}