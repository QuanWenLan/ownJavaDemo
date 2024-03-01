package asychronous_servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 公众号：路人甲java，专注于java干货分享
 * 个人博客：http://itsoku.com/
 * 已推出的系列有：【spring系列】、【java高并发系列】、【MySQL系列】、【MyBatis系列】、【Maven系列】
 * git地址：https://gitee.com/javacode2018
 */
//1.设置@WebServlet的asyncSupported属性为true，表示支持异步处理
@WebServlet(name = "AsyncServlet2",
        urlPatterns = "/asyncServlet2",
        asyncSupported = true
)
public class AsyncServlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long st = System.currentTimeMillis();
        System.out.println("主线程：" + Thread.currentThread() + "-" + System.currentTimeMillis() + "-start");
        //2、启动异步处理：调用req.startAsync(request,response)方法，获取异步处理上下文对象AsyncContext
        AsyncContext asyncContext = request.startAsync(request, response);
        //3、自定义一个线程来处理异步请求
        Thread thread = new Thread(() -> {
            long stSon = System.currentTimeMillis();
            System.out.println("子线程：" + Thread.currentThread() + "-" + System.currentTimeMillis() + "-start");
            try {
                //这里休眠2秒，模拟业务耗时
                TimeUnit.SECONDS.sleep(2);
                //这里是子线程，请求在这里被处理了
                asyncContext.getResponse().getWriter().write(System.currentTimeMillis() + ",ok");
                //4、调用complete()方法，表示异步请求处理完成
                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("子线程：" + Thread.currentThread() + "-" + System.currentTimeMillis() + "-end,耗时(ms):" + (System.currentTimeMillis() - stSon));
        });
        thread.setName("自定义线程");
        thread.start();
        System.out.println("主线程：" + Thread.currentThread() + "-" + System.currentTimeMillis() + "-end,耗时(ms):" + (System.currentTimeMillis() - st));
    }
}
