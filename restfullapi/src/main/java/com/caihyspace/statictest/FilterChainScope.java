package com.caihyspace.statictest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FilterChainScope {

    public static Map<String, List<QuoteFilter>> map = new HashMap<>();
    public static Map<String, Integer> indexMap = new HashMap<>();


    public static void addAll(String key, List<QuoteFilter> all) {
        map.put(key, all);
        indexMap.put(key, 0);
    }

    public void doFilter(String input, String output, String key, FilterChainScope chain) {
        List<QuoteFilter> filters = map.get(key);
        Integer index = indexMap.get(key);
        if (filters == null) {
            // todo 打印日志
            return;
        }
        if (index == filters.size()) {
            // todo 打印结束日志
            indexMap.put(key, 0);
            return;
        }
        QuoteFilter filter = filters.get(index);
        indexMap.put(key, ++index);
        filter.filter(key, output, chain);
    }

}
