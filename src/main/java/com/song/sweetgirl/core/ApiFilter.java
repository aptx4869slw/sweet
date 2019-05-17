package com.song.sweetgirl.core;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

@Configuration
public class ApiFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("执行过滤操作");
        chain.doFilter(request, response);
        System.out.println("过滤执行之后的操作");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

}
