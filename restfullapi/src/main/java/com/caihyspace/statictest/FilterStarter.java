package com.caihyspace.statictest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/4/27
 */
@Component
public class FilterStarter {
    @Autowired
    List<QuoteFilter> filters;

    @PostConstruct
    void init() {
        List<QuoteFilter> list = new ArrayList<>();
        Map<String, List<QuoteFilter>> map = filters.stream().collect(Collectors.groupingBy(QuoteFilter::getChainKey));
        System.out.println("------------------");
        for(String key: map.keySet()) {
            List<QuoteFilter> quoteFilters = map.get(key);
            quoteFilters = quoteFilters.stream().sorted(Comparator.comparingInt(QuoteFilter::getOrder)).collect(Collectors.toList());
            FilterChainScope.map.put(key, quoteFilters);
        }
        System.out.println("==================");
    }

}
