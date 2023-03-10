package com.adrianluque.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GreetingsHandler extends BasicHandler implements HttpHandler {
    long timeStart;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();
        
        System.out.println('[' + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ']' + " Received "
                + "\"" + exchange.getRequestMethod()
                + "\" [" + exchange.getRequestURI() + "] from " + exchange.getRemoteAddress().getHostString());
        switch (exchange.getRequestMethod()) {
            case "GET": {
                handleGet(exchange);
            }
                break;

            default: {
                handleOther(exchange);
            }
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        try {
            JsonObject response = Json.createObjectBuilder()
                .add("header", Json.createObjectBuilder()
                    .add("api_name", "Users API")
                    .add("api_version", "0.1-BETA")
                    .add("api_user", "guest")
                    .add("api_endpoint", "api/greetings")
                    .add("http_request_method", "GET")
                    .add("http_response_status", 200)
                    .add("http_response_message", "ok")
                    .add("response_time", (System.currentTimeMillis() - timeStart))
                    .build())
                .add("body", Json.createObjectBuilder()
                    .add("response", "Greetings from our Users API!")
                    .build())
            .build();

            String responseStr = response.toString();
            int responseLength = responseStr.getBytes().length;

            exchange.sendResponseHeaders(200, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleOther(HttpExchange exchange) throws IOException {
        try {
            JsonObject response = Json.createObjectBuilder()
                .add("header", Json.createObjectBuilder()
                    .add("api_name", "Users API")
                    .add("api_version", "0.1-BETA")
                    .add("api_user", "guest")
                    .add("api_endpoint", "api/greetings")
                    .add("http_request_method", exchange.getRequestMethod())
                    .add("http_response_status", 405)
                    .add("http_response_message", "method not allowed")
                    .add("response_time", (System.currentTimeMillis() - timeStart))
                    .build())
                .add("body", Json.createObjectBuilder()
                    .add("response", JsonValue.NULL)
                    .build())
            .build();

            String responseStr = response.toString();
            int responseLength = response.toString().getBytes().length;

            exchange.sendResponseHeaders(405, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
