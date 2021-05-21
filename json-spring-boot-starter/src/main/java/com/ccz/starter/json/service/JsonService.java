package com.ccz.starter.json.service;

import com.alibaba.fastjson.JSON;

/**
 * @author Cai Haoyun
 * @Description:
 * @date 2021/5/19
 */
public class JsonService {

    private String prefixName;

    private String suffixName;

    public String object2MyJson(Object o) {
        return prefixName + JSON.toJSONString(o) + suffixName;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }
}
