import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PersonHandler extends BasicHandler 
{
    public PersonHandler(DataStore store)
    {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException
    {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println("[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
            String responseString = "{";

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");        
            Person person = store.getPerson(personName);
        
            /* In the real world this part should be implemented this way:
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("about", person.getAbout());
            jsonObject.put("birthYear", person.getBirthYear());
            responseString = jsonObject.toString();
            */
            

            if(person==null)
            {
                responseString += "Info not found";
                responseString += "}";  
                exchange.sendResponseHeaders(404, responseString.getBytes().length); 
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }else
            {
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";                       
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }



        }else if ("POST".equals(exchange.getRequestMethod()))
        {
           String responseString = "{";
           // 1. obtengo los datos de la persona de la URL
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");
           String personaInformacion = params.get("about");
           int personaNacimiento =Integer.parseInt(params.get("birthYear"));
           // 2. creo el objeto persona
           Person persona= new Person(personaNombre, personaInformacion, personaNacimiento);
           responseString += "\"name\": \"" + persona.getName() + "\",";
           responseString += "\"about\": \"" + persona.getAbout() + "\",";
           responseString += "\"birthYear\": " + persona.getBirthYear() + "";
           responseString += "}";
           //3. Guardo la nueva persona en el almacen
           store.putPerson(persona);
           // 4. Devuelvo 200 OK
           exchange.sendResponseHeaders(200, responseString.getBytes().length); 
           OutputStream output1 = exchange.getResponseBody();
           output1.write(responseString.getBytes());
           output1.flush();

           
           



        }
        else if("PUT".equals(exchange.getRequestMethod()))
        {
           String responseString = "{";
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");
           String personaInformacion = params.get("about");
           int personaNacimiento =Integer.parseInt(params.get("birthYear"));

           Person personaPUT= new Person(personaNombre, personaInformacion, personaNacimiento);
           responseString += "\"name\": \"" + personaPUT.getName() + "\",";
           responseString += "\"about\": \"" + personaPUT.getAbout() + "\",";
           responseString += "\"birthYear\": " + personaPUT.getBirthYear() + "";
           responseString += "}";

           store.putPerson(personaPUT);

           exchange.sendResponseHeaders(200, responseString.getBytes().length); 
           OutputStream output1 = exchange.getResponseBody();
           output1.write(responseString.getBytes());
           output1.flush();

        }else if("DELETE".equals(exchange.getRequestMethod()))
        {
        
           String responseString = "";
           Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String personaNombre = params.get("name");

           //String personaInformacion = params.get("about");
           //int personaNacimiento =Integer.parseInt(params.get("birthYear"));

           //Person personaDELETE= new Person(personaNombre, personaInformacion, personaNacimiento);
           //responseString += "\"name\": \"" + personaDELETE.getName() + "\",";
           //responseString += "\"about\": \"" + personaDELETE.getAbout() + "\",";
           //responseString += "\"birthYear\": " + personaDELETE.getBirthYear() + "";
           //responseString += "}";

           store.deletePerson(personaNombre);

           //responseString += "\"name\": \"" + personaDELETE.getName() + "\",";
           //responseString += "\"about\": \"" + personaDELETE.getAbout() + "\",";
           //responseString += "\"birthYear\": " + personaDELETE.getBirthYear() + "";
           //responseString += "}";


           exchange.sendResponseHeaders(200, responseString.getBytes().length); 
           OutputStream output1 = exchange.getResponseBody();
           output1.write(responseString.getBytes());
           output1.flush();
        }
        else
        {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}
