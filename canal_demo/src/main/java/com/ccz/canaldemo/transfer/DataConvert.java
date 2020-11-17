package com.ccz.canaldemo.transfer;

import java.util.Map;

public interface DataConvert {

    Map<String, String> convert(String tbName, Map<String, String> source);

}
