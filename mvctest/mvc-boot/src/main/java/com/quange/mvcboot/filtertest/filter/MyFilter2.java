package com.quange.mvcboot.filtertest.filter;

/**
 * @author Lan
 * @createTime 2023-08-16  11:41
 **/
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@Order(2)
@WebFilter(filterName = "MyFilter2", urlPatterns = "/filterTest/test2")
public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("My filter log 2");
        chain.doFilter(request, response);
    }
}

