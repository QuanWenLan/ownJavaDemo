package com.lanwq.thread.example.delayQueueDemo;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Vin lan
 * @className TimedTaskDelayedQueue
 * @description
 * @createTime 2022-10-24  09:05
 **/
public class TimedTaskDelayedQueue implements Delayed {
    private long defaultDelayTime;
    private String preId;
    private long startTime;
    private long expireTime;
    private Date now = new Date();

    public TimedTaskDelayedQueue() {
    }

    public TimedTaskDelayedQueue(String preId, long startTime, long expireTime) {
        this.preId = preId;
        this.expireTime = startTime + expireTime;
        this.startTime = startTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public long getDefaultDelayTime() {
        return defaultDelayTime;
    }

    public void setDefaultDelayTime(long defaultDelayTime) {
        this.defaultDelayTime = defaultDelayTime;
    }

    public String getPreId() {
        return preId;
    }

    public void setPreId(String preId) {
        this.preId = preId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
}
