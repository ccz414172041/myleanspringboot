package com.ccz.starter.json.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/5/19
 */
@ConfigurationProperties(prefix = "nobody.json")
public class MyJsonProperties {

    public static final String DEFAULT_PREFIX_NAME = "@";

    public static final String DEFAULT_SUFFIX_NAME = "@";

    private String suffixName = DEFAULT_SUFFIX_NAME;

    private String prefixName = DEFAULT_PREFIX_NAME;

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }



}
