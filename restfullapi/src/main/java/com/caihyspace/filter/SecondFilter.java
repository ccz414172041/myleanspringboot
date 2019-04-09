package com.caihyspace.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(2)
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "SecondFilter")
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        log.info("第二个Filter，我的路径：222222" + servletRequest.getRequestURI());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
