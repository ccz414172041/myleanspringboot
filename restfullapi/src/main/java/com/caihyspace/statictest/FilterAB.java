package com.caihyspace.statictest;

import org.springframework.stereotype.Component;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/26
 */
@Component
//@FilterConfig(chainKey = "B", order = 1)
public class FilterAB implements QuoteFilter{
    @Override
    public void filter(String key, String todo, FilterChainScope chainScope) {
        System.out.println("FilterB1...........");
        chainScope.doFilter("",null,key, chainScope);
    }

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public String getChainKey() {
        return "B";
    }
}
