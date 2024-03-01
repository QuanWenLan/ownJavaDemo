package asychronous_servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公众号：路人甲java，专注于java干货分享
 * 个人博客：http://itsoku.com/
 * 已推出的系列有：【spring系列】、【java高并发系列】、【MySQL系列】、【MyBatis系列】、【Maven系列】
 * git地址：https://gitee.com/javacode2018
 */
//1.设置@WebServlet的asyncSupported属性为true，表示支持异步处理
@WebServlet(name = "AsyncServlet7",
        urlPatterns = "/asyncServlet7",
        asyncSupported = true
)
public class AsyncServlet7 extends HttpServlet {
    Map<String, AsyncContext> orderIdAsyncContextMap = new ConcurrentHashMap<>();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        String result = request.getParameter("result");
        AsyncContext async;
        if (orderId != null && result != null && (async = orderIdAsyncContextMap.get(orderId)) != null) {
            async.getResponse().getWriter().write(String.format("<br/>" +
                    "%s:%s:result:%s", Thread.currentThread(), System.currentTimeMillis(), result));
            async.complete();
        } else {
            AsyncContext asyncContext = request.startAsync(request, response);
            orderIdAsyncContextMap.put("1", asyncContext);
            asyncContext.getResponse().setContentType("text/html;charset=utf-8");
            asyncContext.getResponse().getWriter().write(String.format("%s:%s:%s", Thread.currentThread(), System.currentTimeMillis(), "start"));
        }
    }
}
