package com.caihyspace.statictest;

import org.springframework.stereotype.Component;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/26
 */
@Component
//@FilterConfig(chainKey = "B", order = 0)
public class FilterB implements QuoteFilter {
    @Override
    public void filter(String key, String todo, FilterChainScope chainScope) {
        System.out.println("FilterB...........");
        chainScope.doFilter("", null, key, chainScope);

    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public String getChainKey() {
        return "B";
    }
}
