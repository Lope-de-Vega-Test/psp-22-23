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
            //guardamos los datos del GET en un Map
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            //guardamos el nombre del get en un Map
            String personId = params.get("id"); 
            //creamos una variable para guardar ol objeto con nombre personName     
            Person person = store.getPerson(Integer.parseInt(personId));
            //comprobamos si esta vacia o no
            if (person == null) {
                //si esta vacia lanzamos error 404
                
                exchange.sendResponseHeaders(404, -1); // 404 Not Found
                exchange.close();
                return;
            }
            //si no hay errores, guardamos en la variable resonseString la informacion del la persona
            //en el formato JSON
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
            //obtener datos de la persona
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personId = params.get("id");
            String personName = params.get("name");
            String personAbout = params.get("about");
            String personBirthYear = params.get("birthYear");

            // Validamos que no esten vacios los campos
            if (personId == "" || personName == "" || personAbout == "" || personBirthYear == null || personName == null || personAbout == null) {
                // si falta algun parametro, salta error 400
                exchange.sendResponseHeaders(400, -1);
                exchange.close();
                return;
            }

            try {
                // cambiamos el año a int
                int birthYear = Integer.parseInt(personBirthYear);

                //creamos la persona
                Person person = new Person(Integer.parseInt(personId), personName, personAbout, birthYear);
                //la guardamos en la Store
                store.putPerson(person);
                //mostramos los nuevos datos
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
            // si el año no es un numero correcto, error 400
            exchange.sendResponseHeaders(400, -1);
            exchange.close();
            return;
}
        }
        else if("DELETE".equals(exchange.getRequestMethod())){
            String respuestaBorrar="";
            //recogemos datos
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personId = params.get("id");
            String personName = params.get("name");
            //comprobamos que exista el nombre en la Store, si no existe lanzamos error 400
            if(store.getPerson(Integer.parseInt(personId)) == null){
                respuestaBorrar = "No se pudo eliminar. La persona con nombre '" + personName + "' no existe en la DataStore.";
                exchange.sendResponseHeaders(400, respuestaBorrar.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respuestaBorrar.getBytes());
                output.flush();
            }else{
                //si existe la persona, terminamos de obtener los datos y llamamos a la funcion delete
                String personAbout = params.get("about");
                String personBirthYear = params.get("birthYear");
                
                store.deletePerson(Integer.parseInt(personId));

                String responseString = "{";
                responseString += "\"respuesta\": \"" + "BORRADO" + "\"";                
                responseString += "}";  
                // String responseString = "Persona eliminada con exito.";
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }

        }
        else if("PUT".equals(exchange.getRequestMethod())){
            String respuestaModificar = "Modificado correctamente.";
            //obtenemos datos
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personId = params.get("id");
            String personName = params.get("name");
            //comprobamos que exista la persona
            if (store.getPerson(Integer.parseInt(personId)) == null) {
                respuestaModificar = "No se pudo modificar. La persona con id '" + personId + "' no existe en la DataStore.";
                exchange.sendResponseHeaders(400, respuestaModificar.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respuestaModificar.getBytes());
                output.flush();
            } else {
                //si existe, terminamos de recoger los datos y llamamos a la funcion modificar.
                String personAbout = params.get("about");
                String personBirthYear = params.get("birthYear");
                Person person = new Person(Integer.parseInt(personId),personName, personAbout, Integer.parseInt(personBirthYear));
                store.modificarPerson(Integer.parseInt(personId), person);

                String responseString = "{";
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";  
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();

                // exchange.sendResponseHeaders(200, respuestaModificar.getBytes().length);            
                // OutputStream output = exchange.getResponseBody();
                // output.write(respuestaModificar.getBytes());
                // output.flush();
            }
        }
        else
        {
            //si no es nada de lo anterior, lanza error 405
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}