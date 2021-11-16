package com.lanwq.bingfazhimei.chapter2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Vin lan
 * @className ReentrantLockList
 * @description
 * @createTime 2021-11-03  11:20
 **/
public class ReentrantLockList {
    /**
     * 线程不安全的 list
     */
    private ArrayList<String> array = new ArrayList<>();

    /**
     * 独占锁
     */
    private volatile ReentrantLock lock = new ReentrantLock();

    private volatile ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();
    private final Lock readLock = lock2.readLock();
    private final Lock writeLock = lock2.writeLock();

    /**
     * 添加元素
     */
    public void add(String e) {
        lock.lock();
//        writeLock.lock();
        try {
            array.add(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
//            writeLock.unlock();
        }
    }

    /**
     * 删除
     */
    public void remove(String e) {
        lock.lock();
//        writeLock.lock();
        try {
            array.remove(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
//            writeLock.unlock();
        }
    }

    /**
     * 获取数据
     */
    public String add(int index) {
        lock.lock();
//        readLock.lock();
        try {
            return array.get(index);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
//            readLock.unlock();
        }
        return null;
    }
}
