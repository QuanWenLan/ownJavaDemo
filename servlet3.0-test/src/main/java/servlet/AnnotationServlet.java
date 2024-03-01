package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 若对于servlet采用了两种方式同时进行注册（注解和xml文件）则需要注意：
 * 1： 若两种方式的url-pattern值相同，则应用启动会报错
 * 2：若两种方式的url-pattern值不相同，那么相当于该servlet有多个url-pattern
 * 一个servletName,可以有多个映射。value和 URIPatterns不能同时使用
 *
 */
@WebServlet(name = "AnnotationServlet", value = {"/annotationServlet1", "/annotationServlet2"},
            initParams = {@WebInitParam(name="company", value = "中国公司"),
                          @WebInitParam(name="name", value = "权哥")
            },
        loadOnStartup = -1 // 启动时创建servlet的优先级
)
public class AnnotationServlet extends HttpServlet {

    public AnnotationServlet() {
        System.out.println("应用启动时创建。。。");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serverName = this.getServletName();
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print("servlet3.0 <br>");
        writer.print("serverName = " + serverName + "<hr>");
        writer.print("hello");

//        获取初始化的参数
        Enumeration<String> initParameterNames = this.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = this.getInitParameter(name);
            writer.print("name: " + name + " , value: " + value);
        }
        writer.print("hello2");
    }
}
