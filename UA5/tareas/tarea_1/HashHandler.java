//package com.victoraljama;

import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.codec.binary.Hex;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Base64;

public class HashHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);
        long timeStart=System.currentTimeMillis();

        String respuesta = "Hola, esta es la api de Victor";

        if ("GET".equals(exchange.getRequestMethod())) {

            try {

                byte[] bytesOfMessage = respuesta.getBytes("UTF-8");

                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] theMD5digest = md.digest(bytesOfMessage);

                String hash = Hex.encodeHexString(theMD5digest); // convertir la respuesta a una cadena hexadecimal

                JsonObject response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                .add("name", "api Victor Aljama")
                                .add("api_version", "1.0.0")
                                .add("api_user", "guest")
                                .add("api_endpoint", "api/hash")
                                .add("http_request_method", "GET")

                                .add("http_request_parameters", Json.createObjectBuilder()
                                    .add("algorithm", "md5")
                                    .add("text", respuesta)
                                .build())

                                .add("api_user", "guest")
                                .add("http_response_status", 200)
                                .add("http_response_message", "OK")
                                .add("response_time",(System.currentTimeMillis()-timeStart))
                            .build())

                            .add("body", Json.createObjectBuilder()
                                .add("algorithm", "md5")
                                .add("texto", respuesta)
                                .add("hash", hash) // agregar la cadena hexadecimal en lugar del hash original
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
                                .add("api_endpoint", "api/hash")
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
