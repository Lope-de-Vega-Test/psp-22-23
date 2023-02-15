import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class api {
    public static String response;

    public static void main(String[] args) throws IOException {
        DataStore store = new DataStore();
        HttpServer server;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            server = HttpServer.create(new InetSocketAddress(socket.getLocalAddress().getHostAddress(), 8080), 0);
          }

        // GREETING CONTEXT
        server.createContext("/api/greeting", (exchange -> {
            // GET
            if ((exchange.getRequestMethod()).equals("GET")) {
                response = "{\"code\": 200, \"message:\": \"ok\", \"custom_message\": \"Greetings!\"}";
                exchange.sendResponseHeaders(200, response.getBytes().length);

            }
            // ANY OTHER METHOD
            else {
                response = "{\"code\": 405, \"message:\": \"method not allowed\"}";
                exchange.sendResponseHeaders(405, response.getBytes().length);
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.flush();
            os.close();
            exchange.close();

        }));

        // FAREWELL CONTEXT
        server.createContext("/api/farewell", (exchange -> {
            // GET
            if ((exchange.getRequestMethod()).equals("GET")) {
                response = "{\"code\": 200, \"message:\": \"ok\", \"custom_message\": \"Goodbye!\"}";
                exchange.sendResponseHeaders(200, response.getBytes().length);
            }
            // ANY OTHER METHOD
            else {
                response = "{\"code\": 405, \"message:\": \"method not allowed\"}";
                exchange.sendResponseHeaders(405, response.getBytes().length);
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.flush();
            os.close();
            exchange.close();
        }));

        // PERSON
        server.createContext("/api/person", new PersonHandler(store));

        // All contexts have been created
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The framework-less REST API server is listening on " + server.getAddress().getAddress()
                + ":" + server.getAddress().getPort());
    }
}