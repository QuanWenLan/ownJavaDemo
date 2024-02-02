package lan.mvc;

/**
 * @author Vin lan
 * @className MyWebApplicationInitializer
 * @description https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-container-config
 * 另一种启动配置方法
 * @createTime 2022-04-13  10:49
 * WebApplicationInitializer 有一个抽象类:AbstractDispatcherServletInitializer
 **/
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        // 需要这样子写才有用
        appContext.setConfigLocation("/WEB-INF/springmvc.xml");

        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}

/*class a extends AbstractDispatcherServletInitializer {

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}*/
