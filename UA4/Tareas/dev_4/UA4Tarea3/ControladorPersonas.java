import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ControladorPersonas extends ControladorBasico {

    public ControladorPersonas(AlmacenamientoDatos Datos)
    {
        super(Datos);
    }

    public void handle(HttpExchange exchange) throws IOException
    {
        String AccesoRemoto = exchange.getRemoteAddress().getHostString();
        System.out.println("[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + AccesoRemoto);

        if ("GET".equals(exchange.getRequestMethod())) {
            String Respuesta = "{";

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String NombrePersona = params.get("name");        
            Persona persona = Datos.getPerson(NombrePersona);
        
            /* In the real world this part should be implemented this way:
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("about", person.getAbout());
            jsonObject.put("birthYear", person.getBirthYear());
            responseString = jsonObject.toString();
            */
            

            if(persona==null)
            {
                Respuesta += "Info not found";
                Respuesta += "}";  
                exchange.sendResponseHeaders(404, Respuesta.getBytes().length); 
                OutputStream RespuestaOutPut = exchange.getResponseBody();
                RespuestaOutPut.write(Respuesta.getBytes());
                RespuestaOutPut.flush();
            }else
            {
                Respuesta += "\"name\": \"" + persona.getNombre() + "\",";
                Respuesta += "\"about\": \"" + persona.getInformacion() + "\",";
                Respuesta += "\"birthYear\": " + persona.getNacimiento() + "";
                Respuesta += "}";                       
                exchange.sendResponseHeaders(200, Respuesta.getBytes().length);            
                OutputStream RespuestaOutPut = exchange.getResponseBody();
                RespuestaOutPut.write(Respuesta.getBytes());
                RespuestaOutPut.flush();
            }



        }else if ("POST".equals(exchange.getRequestMethod()))
        {
           String Respuesta = "{";
           // 1. obtengo los datos de la persona de la URL
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");
           String personaInformacion = params.get("about");
           int personaNacimiento =Integer.parseInt(params.get("birthYear"));
           // 2. creo el objeto persona
           Persona persona= new Persona(personaNombre, personaInformacion, personaNacimiento);
           Respuesta += "\"name\": \"" + persona.getNombre() + "\",";
           Respuesta += "\"about\": \"" + persona.getInformacion() + "\",";
           Respuesta += "\"birthYear\": " + persona.getNacimiento() + "";
           Respuesta += "}";
           //3. Guardo la nueva persona en el almacen
           Datos.putPerson(persona);
           // 4. Devuelvo 200 OK
           exchange.sendResponseHeaders(200, Respuesta.getBytes().length); 
           OutputStream RespuestaOutPut = exchange.getResponseBody();
           RespuestaOutPut.write(Respuesta.getBytes());
           RespuestaOutPut.flush();

           
           



        }
        else if("PUT".equals(exchange.getRequestMethod()))
        {
           String Respuesta = "{";
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");
           String personaInformacion = params.get("about");
           int personaNacimiento =Integer.parseInt(params.get("birthYear"));

           Persona personaPUT= new Persona(personaNombre, personaInformacion, personaNacimiento);
           Respuesta += "\"name\": \"" + personaPUT.getNombre() + "\",";
           Respuesta += "\"about\": \"" + personaPUT.getInformacion() + "\",";
           Respuesta += "\"birthYear\": " + personaPUT.getNacimiento() + "";
           Respuesta += "}";

           Datos.putPerson(personaPUT);

           exchange.sendResponseHeaders(200, Respuesta.getBytes().length); 
           OutputStream RespuestaOutPut = exchange.getResponseBody();
           RespuestaOutPut.write(Respuesta.getBytes());
           RespuestaOutPut.flush();

        }else if("DELETE".equals(exchange.getRequestMethod()))
        {
        
           String Respuesta = "";
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");

           //String personaInformacion = params.get("about");
           //int personaNacimiento =Integer.parseInt(params.get("birthYear"));

           //Person personaDELETE= new Person(personaNombre, personaInformacion, personaNacimiento);
           //responseString += "\"name\": \"" + personaDELETE.getName() + "\",";
           //responseString += "\"about\": \"" + personaDELETE.getAbout() + "\",";
           //responseString += "\"birthYear\": " + personaDELETE.getBirthYear() + "";
           //responseString += "}";

           Datos.deletePerson(personaNombre);

           //responseString += "\"name\": \"" + personaDELETE.getName() + "\",";
           //responseString += "\"about\": \"" + personaDELETE.getAbout() + "\",";
           //responseString += "\"birthYear\": " + personaDELETE.getBirthYear() + "";
           //responseString += "}";


           exchange.sendResponseHeaders(200, Respuesta.getBytes().length); 
           OutputStream RespuestaOutPut = exchange.getResponseBody();
           RespuestaOutPut.write(Respuesta.getBytes());
           RespuestaOutPut.flush();
        }
        else
        {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}
