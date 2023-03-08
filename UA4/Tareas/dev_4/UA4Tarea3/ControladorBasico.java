import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class ControladorBasico implements HttpHandler {

    public AlmacenamientoDatos Datos;
    public ControladorBasico(AlmacenamientoDatos Datos)
    {
        this.Datos = Datos;
    }

    public static Map<String, String> queryToMap(String query)
    {
        Map<String, String> resultado = new HashMap<String, String>();

        for (String param : query.split("&")) 
        {
            String pair[] = param.split("=");
            if (pair.length>1) 
            {
                resultado.put(pair[0], pair[1]);
            }
            else
            {
                resultado.put(pair[0], "");
            }
        }
        return resultado;
    }

    
    
}
