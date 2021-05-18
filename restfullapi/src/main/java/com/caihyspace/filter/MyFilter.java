//package com.caihyspace.filter;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Slf4j
//@WebFilter(urlPatterns = "/*", filterName = "MyFilter")
//@Order(1)
//public class MyFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest servletRequest = (HttpServletRequest) request;
//        log.info("第一个Filter，我的路径：" + servletRequest.getRequestURI());
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
