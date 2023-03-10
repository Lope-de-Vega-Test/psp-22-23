package com.mycompany.api;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class ByeHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
            "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);
        long timeStart = System.currentTimeMillis();
        OutputStream output = exchange.getResponseBody();

        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                JsonObject response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                        .add("name", "MrDamian API")
                        .add("api_version", "3.0.0")
                        .add("api_user", "guest")
                        .add("api_endpoint", "api/bye")
                        .add("http_request_method", "GET")
                        .add("api_user", "guest")
                        .add("http_response_status", 200)
                        .add("http_response_message", "OK")
                        .add("response_time", (System.currentTimeMillis() - timeStart))
                        .build())

                    .add("body", Json.createObjectBuilder()
                        .add("mensaje", "Bye bye, nos vemos")
                        .build())

                    .build();

                exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                exchange.getResponseBody().write(response.toString().getBytes());
            } catch (Exception e) {

            }
        } else {
            try {
                JsonObject response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                        .add("name", "MrDamian API")
                        .add("api_version", "3.0.0")
                        .add("api_user", "guest")
                        .add("api_endpoint", "api/bye")
                        .add("http_request_method", "GET")
                        .add("api_user", "guest")
                        .add("http_response_status", 405)
                        .add("http_response_message", "OK")
                        .add("response_time", (System.currentTimeMillis() - timeStart))
                        .build())

                    .add("body", Json.createObjectBuilder()
                        .add("mensaje", "Bad method request")
                        .build())

                    .build();

                exchange.sendResponseHeaders(405, response.toString().getBytes().length);
                exchange.getResponseBody().write(response.toString().getBytes());
            } catch (Exception e) {

            }
        }

        exchange.close();
    }
}
