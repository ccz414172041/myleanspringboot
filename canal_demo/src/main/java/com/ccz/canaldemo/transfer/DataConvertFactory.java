package com.ccz.canaldemo.transfer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DataConvertFactory {

    private static final String USER_PREFIX = "convert";
    @Autowired
    UserDataConvert userDataConvert;

    public Map<String, String> transferData(Map<String, String> data, String tbName) {
        if (tbName.startsWith(USER_PREFIX)) {
            return userDataConvert.convert(tbName, data);
        } else {
            return data;
        }
    }

}
