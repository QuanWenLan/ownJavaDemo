package asychronous_servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步servlet
 * @author lan
 */
@WebServlet(name = "AsynchronousServlet", value = {"/asynchronousServlet"}, asyncSupported = true)
public class AsynchronousServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(("主线程开始。。。"));

        long startTime = System.currentTimeMillis();
        System.out.println("AsynchronousServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId());

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        String time = request.getParameter("time");
        int secs = Integer.parseInt(time);
        // max 10 seconds
        if (secs > 10000) {
            secs = 10000;
        }

        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AsynchronousThreadListener());
        // 异步servlet的超时时间,异步Servlet有对应的超时时间，如果在指定的时间内没有执行完操作，response依然会走原来Servlet的结束逻辑，后续的异步操作执行完再写回的时候，可能会遇到异常。
        asyncCtx.setTimeout(9000);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                .getServletContext().getAttribute("executor");

        executor.execute(new AsyncRequestProcessor(asyncCtx, secs));
        // 或者使用new Thread()进行启动一个线程
//        new Thread(new AsyncRequestProcessor(asyncCtx, secs)).start();

        long endTime = System.currentTimeMillis();
        System.out.println("AsynchronousServlet End::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");
        System.out.println(("主线程结束。。。"));
    }
}
