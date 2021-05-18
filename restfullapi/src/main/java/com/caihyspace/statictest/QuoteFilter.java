package com.caihyspace.statictest;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/26
 */
public interface QuoteFilter {

    void filter(String key, String todo, FilterChainScope chain);

    int getOrder();

    String getChainKey();

}
