package com.lanwq.thinkinginjavademo.thread.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * TODO 分而治之,它可以将一个大任务拆解为若干个小任务并行执行, 提高系统吞吐量
 *
 * @ClassName MasterWorker
 * @Description Master-Worker模式
 * 该模式核心思想是系统由两类进行协助工作: Master进程, Worker进程.
 * <p>
 * Master负责接收与分配任务, Worker负责处理任务. 当各个Worker处理完成后,
 * <p>
 * 将结果返回给Master进行归纳与总结.
 * @Author lanwenquan
 * @Date 2020/06/18 16:19
 */
// 假设一个场景, 需要计算100个任务, 并对结果求和, Master持有10个子进程
public class MasterWorker {
    // 盛装任务的集合
    private ConcurrentLinkedQueue<TaskDemo> workQueue = new ConcurrentLinkedQueue<TaskDemo>();
    // 所有worker
    private HashMap<String, Thread> workers = new HashMap<>();
    // 每一个worker并行执行任务的结果
    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public MasterWorker(WorkerDemo worker, int workerCount) {
        // 每个worker对象都需要持有queue的引用, 用于领任务与提交结果
        worker.setResultMap(resultMap);
        worker.setWorkQueue(workQueue);
        for (int i = 0; i < workerCount; i++) {
            workers.put("子节点: " + i, new Thread(worker));
        }
    }

    // 提交任务
    public void submit(TaskDemo task) {
        workQueue.add(task);
    }

    // 启动所有的子任务
    public void execute() {
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            entry.getValue().start();
        }
    }

    // 判断所有的任务是否执行结束
    public boolean isComplete() {
        for (Map.Entry<String, Thread> entry : workers.entrySet()) {
            if (entry.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }

        return true;
    }

    // 获取最终汇总的结果
    public int getResult() {
        int result = 0;
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            result += Integer.parseInt(entry.getValue().toString());
        }

        return result;
    }

    public static void main(String[] args) {
        MasterWorker master = new MasterWorker(new WorkerDemo(), 10);
        for (int i = 0; i < 100; i++) {
            TaskDemo task = new TaskDemo();
            task.setId(i);
            task.setName("任务" + i);
            task.setPrice(new Random().nextInt(10000));
            master.submit(task);
        }

        master.execute();

        while (true) {
            if (master.isComplete()) {
                System.out.println("执行的结果为: " + master.getResult());
                break;
            }
        }
    }
}
