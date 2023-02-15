import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;


public class api {
    public static String welcomeMessage = "Hola! desde nuestro framework-less REST API";
    public static String byebyeMessage = "Adiós! desde nuestro framework-less REST API";

    public static void main(String[] args) throws IOException {
        DataStore store = new DataStore();

        HttpServer server;
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            server = HttpServer.create(new InetSocketAddress(socket.getLocalAddress().getHostAddress(), 8080), 0);
        }
        
        server.createContext("/api/greeting", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String responseText = welcomeMessage;
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            }
            else
            {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));

        server.createContext("/api/bye", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String responseText = byebyeMessage;
                exchange.sendResponseHeaders(200, responseText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseText.getBytes());
                output.flush();
            }
            else
            {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));

        server.createContext("/api/person", new PersonHandler(store));

        // All contexts has been created
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("El framework-less REST API servidor esta escuchando en " + server.getAddress().getAddress() + ":" + server.getAddress().getPort());
    }
}
