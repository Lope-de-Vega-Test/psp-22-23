import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PersonHandler extends BasicHandler {
    
    public PersonHandler(DataStorage store) {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println("[" + new Date() + "] Received " + exchange.getRequestMethod() + " " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
          
            handleGet(exchange);
        }
        else if ("POST".equals(exchange.getRequestMethod())) {
            handlePost(exchange);
        }
        else if("DELETE".equals(exchange.getRequestMethod())) {
            handleDelete(exchange);
        }
        else if("PUT".equals(exchange.getRequestMethod())) {
            handlePut(exchange);
        }
        else {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        try{
        System.out.println("Hola");
       
        Map<String,String> params = queryToMap(exchange.getRequestURI().getQuery());
        String personName = params.get("name"); 
        Person person = store.getPerson(personName);

        if (person == null) {
            exchange.sendResponseHeaders(404, -1); // 404 Not Found
            exchange.close();
            return;
        }

        
            String responseString = "{\"name\": \"" + person.getName() + "\",";
        responseString += "\"about\": \"" + person.getAbout() + "\",";
        responseString += "\"birthYear\": " + person.getBirthYear() + "}";
        
        exchange.sendResponseHeaders(200, responseString.getBytes().length);            
        OutputStream output = exchange.getResponseBody();
        output.write(responseString.getBytes());
        output.flush();

        } catch (Exception e) {
            System.out.println("Error");
            exchange.sendResponseHeaders(400, -1); // 400 Bad Request
            exchange.close();
            return;
        }
        
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        Map<String,String> params = queryToMap(exchange.getRequestURI().getQuery());
        String personName = params.get("name");
        String personAbout = params.get("about");
        String personBirthYear = params.get("birthYear");

        if (personName == "" || personAbout == "" || personBirthYear == null) {
            exchange.sendResponseHeaders(400, -1); // 400 Bad Request
            exchange.close();
            return;
        }

        try {
            int birthYear = Integer.parseInt(personBirthYear);
            Person person = new Person(personName, personAbout, birthYear);
            store.putPerson(person);

            String responseString = "{\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "}";
            
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        } 
        catch (NumberFormatException ex) {
            exchange.sendResponseHeaders(400, -1); // 400 Bad Request
            exchange.close();
            return;
        }
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        Map<String,String> params = queryToMap(exchange.getRequestURI().getQuery());
        String personName = params.get("name");
    
        if (store.getPerson(personName) == null) {
            String responseString = "No se pudo eliminar " + personName + " no existe";
            exchange.sendResponseHeaders(400, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }
        else {
            store.deletePerson(personName);
            String responseString = "La persona con nombre '" + personName + "' ha sido eliminada exitosamente.";
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }
    }
    private void handlePut(HttpExchange exchange) throws IOException {
        Map<String,String> params = queryToMap(exchange.getRequestURI().getQuery());
        String personName = params.get("name");
        String personAbout = params.get("about");
        String personBirthYear = params.get("birthYear");
    
        if (personName == null || personAbout == null || personBirthYear == null) {
            exchange.sendResponseHeaders(400, -1); // 400 Bad Request
            exchange.close();
            return;
        }
    
        Person person = store.getPerson(personName);
        if (person == null) {
            exchange.sendResponseHeaders(404, -1); // 404 Not Found
            exchange.close();
            return;
        }
    
        try {
            int birthYear = Integer.parseInt(personBirthYear);
            person.setAbout(personAbout);
            person.setBirthYear(birthYear);
            store.putPerson(person);
    
            String responseString = "{\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "}";
    
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        } 
        catch (NumberFormatException ex) {
            exchange.sendResponseHeaders(400, -1); // 400 Bad Request
            exchange.close();
            return;
        }
    }
    
    
}
