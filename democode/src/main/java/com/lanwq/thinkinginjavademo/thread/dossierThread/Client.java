package com.lanwq.thinkinginjavademo.thread.dossierThread;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Client
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/4/24 10:46
 */
public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
//        context.start();

        DataService dataService = (DataService) context.getBean("dataService");
        dataService.request("请求啦", new DataCollector());
        context.stop();
    }
}