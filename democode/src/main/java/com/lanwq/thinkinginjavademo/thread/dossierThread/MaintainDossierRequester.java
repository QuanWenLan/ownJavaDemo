package com.lanwq.thinkinginjavademo.thread.dossierThread;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BsDossierRequester
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/24 9:33
 */
public class MaintainDossierRequester extends DataRequester {

    private String name; // 名字
    // 数据请求完之后将数据添加到队列中，然后数据收集器收集数据
    protected BlockingQueue<DossierData> resultQueue = new LinkedBlockingQueue<DossierData>();

    public MaintainDossierRequester(String name, RequestContext context) {
        super("maintain-dossier-2", "2000000", context);
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("当前线程任务执行：" + Thread.currentThread().getName() + " " + MaintainDossierRequester.class.getSimpleName());
        for (int i = 0; i < 4; i++) {
            DossierData dossierData = new DossierData(i + "", "维护数据", Arrays.asList(i + 1, i + 2, i + 3), true, "");
            resultQueue.add(dossierData);
        }
        System.out.println(MaintainDossierRequester.class.getSimpleName() + "： 数据请求结束。。。");
        System.out.println(MaintainDossierRequester.class.getSimpleName() + "数据收集开始");
        try {
            while (resultQueue.size() > 0) {
                DossierData dossierData = resultQueue.poll(10, TimeUnit.SECONDS);
                if (dossierData != null) {
                    DossierResult dossierResult = new DossierResult(dossierData.getId(), dossierData.getName(), dossierData.getDatas(), dossierData.isRequestSuccess(), dossierData.getErrorMessage());

                    context.getCollector().collect(dossierResult);

                }
            }
            System.out.println(context.getCount().decrementAndGet());

            context.setComplete(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MaintainDossierRequester.class.getSimpleName() + "数据收集结束");
    }
}