package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * servlet3.0 以后版本同时支持注解和配置文件两种方式
 * 若对于filter采用了两种方式同时进行注册，则需要注意：无论url-pattern的值是否相同，其都是作为两个独立的filter存在的。
 */

// 使用value，是一个数组，拦截指定的servlet的映射路径
//@WebFilter(filterName = "AnnotationFilter", value = {"/*"})
// 或者是用servletNames,拦截制定的servlet
@WebFilter(filterName = "AnnotationFilter", servletNames = {"AnnotationServlet"})
public class AnnotationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("..... before .......");
        chain.doFilter(req, resp);
        System.out.println("===== after =======");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

