package UA4.tareas.dev_8.tarea_3;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ByeHandler extends BasicHandler {

    public static String byebyeMessage = "BYE! from our framework-less REST API";

    public ByeHandler(DataBase dataBase) {
        super(dataBase);
    }

    public void handle(HttpExchange exchange) throws IOException {

        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
            String responseText = byebyeMessage;
            exchange.sendResponseHeaders(200, responseText.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseText.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();

    }

}
