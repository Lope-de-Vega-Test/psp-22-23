
//package com.victoraljama;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

abstract class BasicHandler implements HttpHandler {

    public DataStorage store;

    public BasicHandler(DataStorage store) {
        this.store = store;
    }

    /**
     *
     * @param query
     * @return
     */
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();

        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
