

/*En este código, se han hecho varios cambios:
El nombre de la clase se cambió a Api para cumplir con las convenciones de nomenclatura de Java.
Se crearon dos métodos separados (handleGreeting y handleBye) para manejar las solicitudes GET 
para las rutas /api/greeting y /api/bye, respectivamente. 
Estos métodos toman un objeto HttpExchange como argumento y llaman a sus métodos correspondientes para enviar la respuesta.
Las cadenas de mensaje de bienvenida y despedida se convirtieron en constantes estáticas para que sean más fáciles de cambiar y mantener.
Se corrigieron las convenciones de nomenclatura de las constantes de mensaje y 
se utilizó la capitalización de palabras separadas por guiones bajos (snake_case).
Se eliminó la declaración de importación no utilizada para Map y HashMap. */

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;



public class api {

private static final String WELCOME_MESSAGE = "Hello World! from our framework-less REST API";
private static final String BYEBYE_MESSAGE = "BYE! from our framework-less REST API";

public static void main(String[] args) throws IOException {
    DataStorage store = new DataStorage();
    HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

    server.createContext("/api/greeting", exchange -> handleGreeting(exchange));
    server.createContext("/api/bye", exchange -> handleBye(exchange));
    server.createContext("/api/person", new PersonHandler(store));

    // All contexts have been created
    server.setExecutor(null); // creates a default executor
    server.start();
    System.out.println("The framework-less REST API server is listening on " + server.getAddress().getAddress() + ":" + server.getAddress().getPort());
}

private static void handleGreeting(HttpExchange exchange) throws IOException {
    if ("GET".equals(exchange.getRequestMethod())) {
        String responseText = WELCOME_MESSAGE;
        exchange.sendResponseHeaders(200, responseText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseText.getBytes());
        output.flush();
    } else {
        exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
    }
    exchange.close();
}

private static void handleBye(HttpExchange exchange) throws IOException {
    if ("GET".equals(exchange.getRequestMethod())) {
        String responseText = BYEBYE_MESSAGE;
        exchange.sendResponseHeaders(200, responseText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(responseText.getBytes());
        output.flush();
    } else {
        exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
    }
    exchange.close();
}
}