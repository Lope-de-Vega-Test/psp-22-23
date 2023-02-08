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
            
            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";    

                                
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();

            
        }
        else if ("POST".equals(exchange.getRequestMethod())){
            //1 obtener datos de la persona

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");      
            String personAbout = params.get("about");
            String personBirthYear = params.get("birthYear");


            Person person = new Person(personName, personAbout, Integer.parseInt(personBirthYear));
            store.putPerson(person);
            String responseString = "{";
            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }
        else if("DELETE".equals(exchange.getRequestMethod())){
            String respuestaBorrar = "borrado correctamente.";

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");      
            String personAbout = params.get("about");
            String personBirthYear = params.get("birthYear");
            
            store.deletePerson(personName);
            String responseString = "{";
            responseString += "\"name\": \"" + personName + "\",";
            responseString += "\"about\": \"" + personAbout + "\",";
            responseString += "\"birthYear\": " + personBirthYear + "";
            responseString += "}";
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

        }
        else if("PUT".equals(exchange.getRequestMethod())){
            String respuestaBorrar = "Modificado correctamente.";

            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            String personAbout = params.get("about");
            String personBirthYear = params.get("birthYear");
            Person person = new Person(personName, personAbout, Integer.parseInt(personBirthYear));
            store.modificarPeron(personName, person);

            exchange.sendResponseHeaders(200, respuestaBorrar.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(respuestaBorrar.getBytes());
            output.flush();
        }
        else
        {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}