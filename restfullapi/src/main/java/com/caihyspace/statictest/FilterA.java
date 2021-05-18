package com.caihyspace.statictest;

import org.springframework.stereotype.Component;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/26
 */
@Component
//@FilterConfig(chainKey = "A")
public class FilterA implements QuoteFilter{
    @Override
    public void filter(String key, String todo, FilterChainScope chainScope) {
        System.out.println("FilterA...........");
        chainScope.doFilter("",null,key, chainScope);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String getChainKey() {
        return "A";
    }
}
