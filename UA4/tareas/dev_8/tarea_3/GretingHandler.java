package UA4.tareas.dev_8.tarea_3;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

public class GretingHandler extends BasicHandler {

    public static String welcomeMessage = "Hello World! from our framework-less REST API";

    public GretingHandler(DataBase dataBase) {
        super(dataBase);
    }

    public void handle(HttpExchange exchange) throws IOException {

        JSONObject json = new JSONObject();

        JSONObject header = new JSONObject();
        header.put("api_name", "My API Name");
        header.put("api_version", "1.0.0");
        header.put("api_user", "guest");
        header.put("api_endpoint", "api/bye");
        header.put("http_request_method", "GET");
        header.put("http_request_parameters", "X.X.X");
        header.put("http_response_status", 200);
        header.put("http_response_message", "Bad Request");
        header.put("response_time", 123456789);

        json.put("header", header);

        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
            String responseText = welcomeMessage;

            JSONObject body = new JSONObject();
            body.put("response", welcomeMessage);
            json.put("body", body);

            // exchange.sendResponseHeaders(200, responseText.getBytes().length);
            exchange.sendResponseHeaders(200,  json.toString().getBytes().length);
            OutputStream output = exchange.getResponseBody();
            // output.write(responseText.getBytes());
            output.write(json.toString().getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();

    }

}
