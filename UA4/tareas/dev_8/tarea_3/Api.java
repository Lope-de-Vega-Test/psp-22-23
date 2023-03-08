package UA4.tareas.dev_8.tarea_3;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Api {
    public static String welcomeMessage = "Hello World! from our framework-less REST API";
    public static String byebyeMessage = "BYE! from our framework-less REST API";

    public static void main(String[] args) throws IOException {
        DataBase dataBase = new DataBase();

        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8080), 0);

        server.createContext("/api/bye", new ByeHandler(dataBase));

        server.createContext("/api/greting", new GretingHandler(dataBase));

        server.createContext("/api/person", new PersonHandler(dataBase));



        // All contexts has been created
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The framework-less REST API server is listening on " + server.getAddress().getAddress()
                + ":" + server.getAddress().getPort());
    }

}