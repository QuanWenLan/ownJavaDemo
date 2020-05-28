package com.lanwq.thinkinginjavademo.thread.dossierThread;

/**
 * @ClassName DataRequester
 * @Description TODO 数据请求器
 * @Author lanwenquan
 * @Date 2020/4/24 9:25
 */
public abstract class DataRequester implements Runnable{
    protected String id;   // 请求器id

    protected String key;  // 搜索key值

    protected RequestContext context;



    public DataRequester() {
    }

    public DataRequester(String id, String key, RequestContext context) {
        this.id = id;
        this.key = key;
        this.context = context;
    }
}