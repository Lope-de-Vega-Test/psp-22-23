package com.adrianluque.handlers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.adrianluque.storage.Gateway;
import com.adrianluque.storage.users.User;
import com.adrianluque.storage.users.UserStorage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserHandler extends BasicHandler implements HttpHandler {
    // Attributes
    private long timeStart;
    private Gateway gateway = new Gateway();

    // Constructors
    public UserHandler(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println('[' + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ']' + " Received "
                + "\"" + exchange.getRequestMethod()
                + "\" [" + exchange.getRequestURI() + "] from " + exchange.getRemoteAddress().getHostString());
        switch (exchange.getRequestMethod()) {
            case "GET": { // view user
                handleGet(exchange);
            }
                break;

            case "POST": { // updates user
                handlePost(exchange);
            }
                break;

            case "PUT": { // creates new user
                handlePut(exchange);
            }
                break;

            case "DELETE": { // delete user
                handleDelete(exchange);
            }
                break;

            default: {
                handleOther(exchange);
            }
        }
    }

    /**
     * 
     * @param exchange
     * @throws IOException
     */
    void handleOther(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();

        JsonObject response = null;
        int rCode = 405;

        response = Json.createObjectBuilder()
                .add("header", Json.createObjectBuilder()
                        .add("api_name", "Users API")
                        .add("api_version", "0.1-BETA")
                        .add("api_user", "guest")
                        .add("api_endpoint", exchange.getRequestURI().toString())
                        .add("http_request_method", exchange.getRequestMethod())
                        .add("http_request_parameters", getRequestParameters(exchange.getRequestURI().getQuery()))
                        .add("http_response_status", rCode)
                        .add("http_response_message", "method not allowed")
                        .add("response_time", (System.currentTimeMillis() - timeStart))
                        .build())
                .add("body", Json.createObjectBuilder()
                        .build())
                .build();

        String responseStr = response.toString();
        int responseLength = responseStr.getBytes().length;
        exchange.sendResponseHeaders(rCode, responseLength);
        exchange.getResponseBody().write(responseStr.getBytes());
    }

    /**
     * 
     * @param exchange
     * @throws IOException
     */
    void handleGet(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();

        JsonObject response = null;
        int rCode = 0;

        try {
            Map<String, Object> params = toKVMap(exchange.getRequestURI().getQuery());
            Object $param = null;

            User user = null;
            ArrayList<User> users = null;

            if (params.containsKey("index")) {
                if (($param = params.get("index")) != null) {
                    user = gateway.storage.getUserByIndex(Integer.valueOf(String.valueOf($param)));
                }
            } else if (params.containsKey("uuid")) {
                if (($param = params.get("uuid")) != null) {
                    user = gateway.storage.getUserByUUID(Long.valueOf(String.valueOf($param)));
                }
            } else if (params.containsKey("id")) {
                if (($param = params.get("id")) != null) {
                    user = gateway.storage.getUserById(String.valueOf($param));
                }
            } else if (params.containsKey("idLike")) {
                if (($param = params.get("idLike")) != null) {
                    users = new ArrayList<User>(
                            gateway.storage.getUsersByIdLike(String.valueOf($param)));
                }
            } else if (params.containsKey("creationDate")) {
                if (($param = params.get("creationDate")) != null) {
                    users = new ArrayList<User>(
                            gateway.storage.getUsersByCreationDate(String.valueOf($param)));
                }
            } else if (params.containsKey("creationDateAfter")) {
                if (($param = params.get("creationDateAfter")) != null) {
                    users = new ArrayList<User>(
                            gateway.storage.getUsersCreatedAfter(String.valueOf($param)));
                }
            } else if (params.containsKey("creationDateBefore")) {
                if (($param = params.get("creationDateBefore")) != null) {
                    users = new ArrayList<User>(
                            gateway.storage.getUsersCreatedBefore(String.valueOf($param)));
                }
            } else if (params.containsKey("creationDateAfter") && params.containsKey("creationDateBefore")) {
                if (($param = params.get("creationDateAfter")) != null) {
                    Object $_param;
                    if (($_param = params.get("creationDateBefore")) != null) {
                        users = new ArrayList<User>(
                                gateway.storage.getUsersCreatedBetween(String.valueOf($param), String.valueOf($_param)));
                    }
                }
            }

            if (user != null) { // user - index, uuid and id
                rCode = 200;
                response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                                .add("api_name", "Users API")
                                .add("api_version", "0.1-BETA")
                                .add("api_user", "guest")
                                .add("api_endpoint", exchange.getRequestURI().toString())
                                .add("http_request_method", exchange.getRequestMethod())
                                .add("http_request_parameters",
                                        getRequestParameters(exchange.getRequestURI().getRawQuery()))
                                .add("http_response_status", rCode)
                                .add("http_response_message", "ok")
                                .add("response_time", (System.currentTimeMillis() - timeStart))
                                .build())
                        .add("body", Json.createObjectBuilder()
                                .add("user", Json.createObjectBuilder()
                                        .add("uuid", user.getUUID())
                                        .add("id", user.getId())
                                        .add("creationDate", user.getCreationDate())
                                        .build())
                                .build())
                        .build();
            } else if (users != null) { // <users> creationDate
                if (!users.isEmpty()) {
                    rCode = 200;
                    JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

                    for (User u : users) {
                        JsonObjectBuilder userBuilder = Json.createObjectBuilder()
                                .add("uuid", u.getUUID())
                                .add("id", u.getId())
                                .add("creationDate", u.getCreationDate());

                        jsonArrayBuilder.add(userBuilder.build());
                    }

                    JsonArray jsonArray = jsonArrayBuilder.build();

                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getRawQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "ok")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder()
                                    .add("users", jsonArray)
                                    .build())
                            .build();
                } else { // <users> not found
                    rCode = 404;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getRawQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "not found")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder())
                            .build();
                }
            } else { // user not found or bad request
                if ($param == null) {
                    rCode = 400;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getRawQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "bad request")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder())
                            .build();
                } else {
                    rCode = 404;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getRawQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "not found")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder())
                            .build();
                }

            }
        } catch (Exception e) { // bad request
            rCode = 400;
            response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                            .add("api_name", "Users API")
                            .add("api_version", "0.1-BETA")
                            .add("api_user", "guest")
                            .add("api_endpoint", exchange.getRequestURI().toString())
                            .add("http_request_method", exchange.getRequestMethod())
                            .add("http_request_parameters",
                                    getRequestParameters(exchange.getRequestURI().getRawQuery()))
                            .add("http_response_status", rCode)
                            .add("http_response_message", "bad request")
                            .add("response_time", (System.currentTimeMillis() - timeStart))
                            .build())
                    .add("body", Json.createObjectBuilder()
                            .build())
                    .build();
        } finally {
            String responseStr = response.toString();
            int responseLength = responseStr.getBytes().length;
            exchange.sendResponseHeaders(rCode, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        }
    }

    /**
     * 
     * @param exchange
     * @throws IOException
     */
    void handlePost(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();

        JsonObject response = null;
        int rCode = 0;

        try {
            Map<String, Object> params = toKVMap(exchange.getRequestURI().getQuery());

            if (params.containsKey("uuid") && params.containsKey("id")) {
                if (params.get("uuid") != null && params.get("id") != null) {
                    User user = new User();
                    user.setUUID(Long.parseLong(String.valueOf(params.get("uuid"))));
                    user.setId(String.valueOf(params.get("id")));
                    user.setCreationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    gateway.storage.addUser(user);
                    rCode = 201;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "created")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder())
                            .build();
                } else {
                    rCode = 400;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "bad request")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder().build())
                            .build();
                }
            } else {
                rCode = 400;
                response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                                .add("api_name", "Users API")
                                .add("api_version", "0.1-BETA")
                                .add("api_user", "guest")
                                .add("api_endpoint", exchange.getRequestURI().toString())
                                .add("http_request_method", exchange.getRequestMethod())
                                .add("http_request_parameters",
                                        getRequestParameters(exchange.getRequestURI().getQuery()))
                                .add("http_response_status", rCode)
                                .add("http_response_message", "bad request")
                                .add("response_time", (System.currentTimeMillis() - timeStart))
                                .build())
                        .add("body", JsonValue.NULL)
                        .build();
            }

        } catch (Exception e) {
            rCode = 400;
            response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                            .add("api_name", "Users API")
                            .add("api_version", "0.1-BETA")
                            .add("api_user", "guest")
                            .add("api_endpoint", exchange.getRequestURI().toString())
                            .add("http_request_method", exchange.getRequestMethod())
                            .add("http_request_parameters", getRequestParameters(exchange.getRequestURI().getQuery()))
                            .add("http_response_status", rCode)
                            .add("http_response_message", "bad request")
                            .add("response_time", (System.currentTimeMillis() - timeStart))
                            .build())
                    .add("body", Json.createObjectBuilder()
                            .build())
                    .build();
        } finally {
            String responseStr = response.toString();
            int responseLength = responseStr.getBytes().length;
            exchange.sendResponseHeaders(rCode, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        }
    }

    /**
     * 
     * @param exchange
     * @throws IOException
     */
    void handlePut(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();

        JsonObject response = null;
        int rCode = 0;

        try {
            Map<String, Object> params = toKVMap(exchange.getRequestURI().getQuery());

            Object $param = null;
            int index = 0;

            if (params.containsKey("index")) {
                if (($param = params.get("index")) != null) {
                    index = Integer.valueOf(String.valueOf($param));

                    if (params.get("id") != null) {
                        User u = gateway.storage.getUserByIndex(index);

                        String id = String.valueOf(params.get("id"));

                        User user = new User(u.getUUID(), id, u.getCreationDate());

                        if (gateway.storage.updateUser(index, user)) {
                            rCode = 200;
                            response = Json.createObjectBuilder()
                                    .add("header", Json.createObjectBuilder()
                                            .add("api_name", "Users API")
                                            .add("api_version", "0.1-BETA")
                                            .add("api_user", "guest")
                                            .add("api_endpoint", exchange.getRequestURI().toString())
                                            .add("http_request_method", exchange.getRequestMethod())
                                            .add("http_request_parameters",
                                                    getRequestParameters(exchange.getRequestURI().getQuery()))
                                            .add("http_response_status", rCode)
                                            .add("http_response_message", "created")
                                            .add("response_time", (System.currentTimeMillis() - timeStart))
                                            .build())
                                    .add("body", Json.createObjectBuilder())
                                    .build();
                        } else {
                            rCode = 404;
                            response = Json.createObjectBuilder()
                                    .add("header", Json.createObjectBuilder()
                                            .add("api_name", "Users API")
                                            .add("api_version", "0.1-BETA")
                                            .add("api_user", "guest")
                                            .add("api_endpoint", exchange.getRequestURI().toString())
                                            .add("http_request_method", exchange.getRequestMethod())
                                            .add("http_request_parameters",
                                                    getRequestParameters(exchange.getRequestURI().getQuery()))
                                            .add("http_response_status", rCode)
                                            .add("http_response_message", "not found")
                                            .add("response_time", (System.currentTimeMillis() - timeStart))
                                            .build())
                                    .add("body", Json.createObjectBuilder())
                                    .build();
                        }
                    }

                } else {
                    rCode = 400;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "bad request")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder()
                                    .build())
                            .build();
                }
            } else {
                rCode = 400;
                response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                                .add("api_name", "Users API")
                                .add("api_version", "0.1-BETA")
                                .add("api_user", "guest")
                                .add("api_endpoint", exchange.getRequestURI().toString())
                                .add("http_request_method", exchange.getRequestMethod())
                                .add("http_request_parameters",
                                        getRequestParameters(exchange.getRequestURI().getQuery()))
                                .add("http_response_status", rCode)
                                .add("http_response_message", "bad request")
                                .add("response_time", (System.currentTimeMillis() - timeStart))
                                .build())
                        .add("body", Json.createObjectBuilder()
                                .build())
                        .build();
            }

        } catch (Exception e) {
            rCode = 400;
            response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                            .add("api_name", "Users API")
                            .add("api_version", "0.1-BETA")
                            .add("api_user", "guest")
                            .add("api_endpoint", exchange.getRequestURI().toString())
                            .add("http_request_method", exchange.getRequestMethod())
                            .add("http_request_parameters", getRequestParameters(exchange.getRequestURI().getQuery()))
                            .add("http_response_status", rCode)
                            .add("http_response_message", "bad request")
                            .add("response_time", (System.currentTimeMillis() - timeStart))
                            .build())
                    .add("body", Json.createObjectBuilder()
                            .build())
                    .build();
        } finally {
            String responseStr = response.toString();
            int responseLength = responseStr.getBytes().length;
            exchange.sendResponseHeaders(rCode, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        }
    }

    /**
     * 
     * @param exchange
     * @throws IOException
     */
    void handleDelete(HttpExchange exchange) throws IOException {
        timeStart = System.currentTimeMillis();

        JsonObject response = null;
        int rCode = 0;

        try {
            Map<String, Object> params = toKVMap(exchange.getRequestURI().getQuery());

            Object $param = null;
            int index = 0;

            if (params.containsKey("index")) {
                if (($param = params.get("index")) != null) {
                    index = Integer.valueOf(String.valueOf($param));

                    if (gateway.storage.removeUser(index)) {
                        rCode = 200;
                        response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                        .add("api_name", "Users API")
                                        .add("api_version", "0.1-BETA")
                                        .add("api_user", "guest")
                                        .add("api_endpoint", exchange.getRequestURI().toString())
                                        .add("http_request_method", exchange.getRequestMethod())
                                        .add("http_request_parameters",
                                                getRequestParameters(exchange.getRequestURI().getQuery()))
                                        .add("http_response_status", rCode)
                                        .add("http_response_message", "created")
                                        .add("response_time", (System.currentTimeMillis() - timeStart))
                                        .build())
                                .add("body", Json.createObjectBuilder())
                                .build();
                    }
                } else {
                    rCode = 400;
                    response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                    .add("api_name", "Users API")
                                    .add("api_version", "0.1-BETA")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", exchange.getRequestURI().toString())
                                    .add("http_request_method", exchange.getRequestMethod())
                                    .add("http_request_parameters",
                                            getRequestParameters(exchange.getRequestURI().getQuery()))
                                    .add("http_response_status", rCode)
                                    .add("http_response_message", "bad request")
                                    .add("response_time", (System.currentTimeMillis() - timeStart))
                                    .build())
                            .add("body", Json.createObjectBuilder()
                                    .build())
                            .build();
                }
            } else {
                rCode = 400;
                response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                                .add("api_name", "Users API")
                                .add("api_version", "0.1-BETA")
                                .add("api_user", "guest")
                                .add("api_endpoint", exchange.getRequestURI().toString())
                                .add("http_request_method", exchange.getRequestMethod())
                                .add("http_request_parameters",
                                        getRequestParameters(exchange.getRequestURI().getQuery()))
                                .add("http_response_status", rCode)
                                .add("http_response_message", "bad request")
                                .add("response_time", (System.currentTimeMillis() - timeStart))
                                .build())
                        .add("body", Json.createObjectBuilder()
                                .build())
                        .build();
            }
        } catch (Exception e) {
            rCode = 400;
            response = Json.createObjectBuilder()
                    .add("header", Json.createObjectBuilder()
                            .add("api_name", "Users API")
                            .add("api_version", "0.1-BETA")
                            .add("api_user", "guest")
                            .add("api_endpoint", exchange.getRequestURI().toString())
                            .add("http_request_method", exchange.getRequestMethod())
                            .add("http_request_parameters", getRequestParameters(exchange.getRequestURI().getQuery()))
                            .add("http_response_status", rCode)
                            .add("http_response_message", "bad request")
                            .add("response_time", (System.currentTimeMillis() - timeStart))
                            .build())
                    .add("body", Json.createObjectBuilder()
                            .build())
                    .build();
        } finally {
            String responseStr = response.toString();
            int responseLength = responseStr.getBytes().length;
            exchange.sendResponseHeaders(rCode, responseLength);
            exchange.getResponseBody().write(responseStr.getBytes());
        }
    }

    /**
     * 
     * @param query
     * @return String
     */
    String getRequestParameters(String query) {
        String parameters = "";
        try {
            Map<String, Object> paramsMap = toKVMap(query);
            for (String p : paramsMap.keySet()) {
                parameters += p + ", ";
            }
            if (parameters.length() > 0) {
                parameters = parameters.substring(0, parameters.length() - 2);
            }
        } catch (Exception e) {
        }

        return parameters;
    }
}
