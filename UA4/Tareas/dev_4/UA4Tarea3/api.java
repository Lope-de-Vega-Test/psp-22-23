
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;




public class api {
    public static String MensajeBienvenida = "Hola! Mundo desde el framework de REST-API";
    public static String MensajeDespedida = "Adios! Desde nuestro framework de REST-API";

    public static void main (String[] args) throws IOException {
        AlmacenamientoDatos Almacenamiento = new AlmacenamientoDatos();

        
        HttpServer ServidorHTTP = HttpServer.create(new InetSocketAddress("10.1.0.62", 8080), 0);
        
        ServidorHTTP.createContext("/api/greeting", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String TextoRespuesta = MensajeBienvenida;
                exchange.sendResponseHeaders(200, TextoRespuesta.getBytes().length);
                OutputStream RespuestaOutPut = exchange.getResponseBody();
                RespuestaOutPut.write(TextoRespuesta.getBytes());
                RespuestaOutPut.flush();
            } 
            else
            {
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        }));

        ServidorHTTP.createContext("/api/bye", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                String TextoRespuesta = "bye bye! from our framework-less REST API\n";
                exchange.sendResponseHeaders(200, TextoRespuesta.getBytes().length);
                OutputStream RespuestaOutPut = exchange.getResponseBody();
                RespuestaOutPut.write(TextoRespuesta.getBytes());
                RespuestaOutPut.flush();
            } 
            else
            {
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        }));
               
        ServidorHTTP.createContext("/api/person", new ControladorPersonas(Almacenamiento));       

        
        ServidorHTTP.setExecutor(null); 
        ServidorHTTP.start();
        System.out.println("The framework-less REST API server is listening on " + ServidorHTTP.getAddress().getAddress() + ":" + ServidorHTTP.getAddress().getPort());
    }
}
