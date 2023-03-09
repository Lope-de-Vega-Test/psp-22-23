package com.victoraljama;

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

public class GreetingHandler implements HttpHandler{
    
    public GreetingHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);
        long timeStart=System.currentTimeMillis();
        
        String respuesta = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                JsonObject response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                .add("name", "api Victor Aljama")
                                .add("api_version", "1.0.0")
                                .add("api_user", "guest")
                                .add("api_endpoint", "api/greeting")
                                .add("http_request_method", "GET")
                                .add("api_user", "guest")
                                .add("http_response_status", 200)
                                .add("http_response_message", "OK")
                                .add("response_time",(System.currentTimeMillis()-timeStart))
                            .build())
                        
                            .add("body", Json.createObjectBuilder()
                                .add("mensaje", "Hola, gracias por usar esta api")
                            .build())
                        
                        .build();
                
                respuesta = response.toString();
                
                exchange.sendResponseHeaders(200, respuesta.getBytes().length);
                exchange.getResponseBody().write(respuesta.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                JsonObject response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                .add("name", "api Victor Aljama")
                                .add("api_version", "1.0.0")
                                .add("api_user", "guest")
                                .add("api_endpoint", "api/greeting")
                                .add("http_request_method", "GET")
                                .add("api_user", "guest")
                                .add("http_response_status", 405)
                                .add("http_response_message", "Method not allowed")
                                .add("response_time",(System.currentTimeMillis()-timeStart))
                            .build())
                        
                            .add("body", Json.createObjectBuilder()
                                .add("mensaje", JsonValue.NULL)
                            .build())
                        
                        .build();
                
                respuesta = response.toString();
                
                exchange.sendResponseHeaders(405, respuesta.getBytes().length);
                exchange.getResponseBody().write(respuesta.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        

        exchange.close();
    }
}
