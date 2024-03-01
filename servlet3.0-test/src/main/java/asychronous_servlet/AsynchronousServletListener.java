package asychronous_servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@WebListener()
public class AsynchronousServletListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public AsynchronousServletListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      // 在这边初始化线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100));
        sce.getServletContext().setAttribute("executor", threadPoolExecutor);
        System.out.println("contextInitialized!!!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

}
