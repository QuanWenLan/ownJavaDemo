package com.lanwq.thinkinginjavademo.thread.dossierThread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @ClassName DataService
 * @Description TODO 数据服务类
 * @Author lanwenquan
 * @Date 2020/4/24 9:36
 */
public class DataService {

    private ThreadPoolTaskExecutor executor;

    public void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void request(String id, DataCollector collector)  {

        RequestContext context = new RequestContext(collector);
        List<DataRequester> requesters = new ArrayList<>();

        requesters.add(new BsDossierRequester("业务数据请求器", context));
        requesters.add(new MaintainDossierRequester("维护数据请求器", context));

        for (DataRequester requester : requesters) {
            executor.execute(requester);
//            executor.submit(requester);
        }

        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // 执行数据收集
        Future result = executor.submit(collector);
        try {
            if( result.get().toString().equals("99999")){
                executor.destroy();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}