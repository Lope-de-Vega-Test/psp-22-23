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
            if (person == null) {
                
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
    
        else if ("POST".equals(exchange.getRequestMethod())){
            
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            String personAbout = params.get("about");
            String personBirthYear = params.get("birthYear");

            if (personName == "" || personAbout == "" || personBirthYear == null) {
                exchange.sendResponseHeaders(400, -1);
                exchange.close();
                return;
            }
            try {
              
                int birthYear = Integer.parseInt(personBirthYear);
                Person person = new Person(personName, personAbout, birthYear);
                
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
            } catch (NumberFormatException ex) {
            
            exchange.sendResponseHeaders(400, -1);
            exchange.close();
            return;
}
        }
        else if("DELETE".equals(exchange.getRequestMethod())){
            String borrar="";
           
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            
            if(store.getPerson(personName) == null){
                borrar = "No se pudo eliminar. La persona con nombre '" + personName + "' no existe en la DataStore.";
                exchange.sendResponseHeaders(400, borrar.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(borrar.getBytes());
                output.flush();
            }else{
               
                String personAbout = params.get("about");
                String personBirthYear = params.get("birthYear");
                
                store.deletePerson(personName);
                String responseString = "Persona eliminada con exito.";
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }

        }
        else if("PUT".equals(exchange.getRequestMethod())){
            String responseString = "{";
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personName = params.get("name");
            
            if (store.getPerson(personName) == null) {
                responseString = "No se pudo modificar. La persona con nombre '" + personName + "' no existe en la DataStore.";
                exchange.sendResponseHeaders(400, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            } else {
                
                String personAbout = params.get("about");
                String personBirthYear = params.get("birthYear");
                Person person = new Person(personName, personAbout, Integer.parseInt(personBirthYear));
                store.modificarPeron(personName, person);
                
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
        }
        else
        {
            
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}