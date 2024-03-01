package asychronous_servlet;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: javaDemo->SubThreadServlet
 * @description: servlet的子线程
 * @author: lanwenquan
 * @date: 2020-03-17 20:13
 */
public class AsyncRequestProcessor implements Runnable {

    private AsyncContext asyncContext;
    private long time;

    public AsyncRequestProcessor(AsyncContext asyncContext, long time) {
        this.asyncContext = asyncContext;
        this.time = time;
    }

    public AsyncRequestProcessor() {
    }

    /**
     * 如果使用了servlet中的对象，request或者response对象，线程就会阻塞
     */
    @Override
    public void run() {
        System.out.println("Async Supported? "
                + asyncContext.getRequest().isAsyncSupported());

        try {
            Thread.sleep(time);

            /*PrintWriter writer = asyncContext.getResponse().getWriter();
            writer.print("Processing done for " + time + " milliseconds!!");*/
            HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
            request.getSession().setAttribute("name", "兰文权");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 通知异步线程执行完毕,用于 结束异步操作，并将与当前异步对象相关的request与response对象销毁
        asyncContext.complete();
        // 在结束异步操作的同时，会将参数所制定的页面内容包含到当前异步对象相关的标准输出流中
//        asyncContext.dispatch();
    }
}
