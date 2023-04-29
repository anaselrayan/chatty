package com.anas.chatty.utils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    public static Map<String, String> extractParams(String url) {
        var params = new HashMap<String, String>();
        url = url.split("\\?")[1];
        String[] paramList = url.split("&");
        for (String param : paramList) {
            String pName = param.split("=")[0];
            String pValue = param.split("=")[1];
            params.put(pName, pValue);
        }
        return params;
    }
}
