package com.adrianluque.handlers;

import java.util.HashMap;
import java.util.Map;

public abstract class BasicHandler {

    public static Map<String, Object> toKVMap(String str) throws NullPointerException {
        Map<String, Object> result = new HashMap<String, Object>();
        for (String param : str.split("&")) {
            String[] pair = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], null);
            }
        }
        return result;
    }
}
