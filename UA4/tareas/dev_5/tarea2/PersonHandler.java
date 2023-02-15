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

            if (person == null) {
                //si esta vacia lanzamos error 404
                exchange.sendResponseHeaders(404, -1); // 404 Not Found
                exchange.close();
                return;
            
            }

            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";                       
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }     
        
       else if(("POST".equals(exchange.getRequestMethod()))){

            String responseString = "{";
           
        Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
           String PersonaName = params.get("name");
           String PersonAbout = params.get("about");
           String PersonBirtYear=params.get("birthYear");
           
           
           Person personaNueva= new Person(PersonaName, PersonAbout, Integer.parseInt(PersonBirtYear));
           responseString += "\"name\": \"" + personaNueva.getName() + "\",";
           responseString += "\"about\": \"" + personaNueva.getAbout() + "\",";
           responseString += "\"birthYear\": " + personaNueva.getBirthYear() + "";
           responseString += "}";

           store.putPerson(personaNueva);
           exchange.sendResponseHeaders(200, responseString.getBytes().length); 
           OutputStream output1 = exchange.getResponseBody();
           output1.write(responseString.getBytes());
           output1.flush();

       }
        /*String responseString="Hecho";
        Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
        String PersonaName=params.get("name");
        String PersonAbout=params.get("about");
        String PersonBirtYear=params.get("birthYear");

        Person personaNueva=new Person(PersonaName,PersonAbout,Integer.parseInt(PersonBirtYear));
        store.putPerson(personaNueva);
             
        exchange.sendResponseHeaders(200, responseString.getBytes().length);            
        OutputStream output = exchange.getResponseBody();
        output.write(responseString.getBytes());
        output.flush();
        }*/

        else if(("DELETE".equals(exchange.getRequestMethod()))){

            String responseString="SE HA BORRADO";

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String PersonaName=params.get("name");
            store.deletePerson(PersonaName);

            exchange.sendResponseHeaders(200, responseString.getBytes().length); 
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());

            output.flush();
        }

       else if (("PUT".equals(exchange.getRequestMethod()))){

            String responseString = "SE HA MODIFICADO";
            
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String PersonaName = params.get("name");
            store.ModificarPeron(PersonaName );

            exchange.sendResponseHeaders(200, responseString.getBytes().length); 
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());

            output.flush();
        
        }
        
        else
        {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}