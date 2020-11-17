package com.ccz.canaldemo.transfer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UserDataConvert implements DataConvert{
    @Override
    public Map<String, String> convert(String tbName, Map<String, String> source) {
        return source;
    }
}
