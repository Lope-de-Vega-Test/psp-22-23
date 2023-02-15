import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PersonHandler extends BasicHandler {
    public PersonHandler(DataStore store) {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {
            String responseString = "{";

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            //Pedimos el parametro con el nombre y lo guardamos
            String personId = params.get("id");
            //Creamos una variable person y usando el metodo creado le ponemos de nombre la variable creada antes
            Person person = store.getPerson(Integer.parseInt(personId));
            
             /*
             * In the real world this part should be implemented this way:
             * JSONObject jsonObject = new JSONObject();
             * jsonObject.put("name", person.getName());
             * jsonObject.put("about", person.getAbout());
             * jsonObject.put("birthYear", person.getBirthYear());
             * responseString = jsonObject.toString();
             */

             //Hacemos el mismo proceso con todas las variables restantes

            responseString += "\"id\": \"" + person.getId() + "\",";
            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        //Funcion Añadir persona usando el metodo POST

        else if ("POST".equals(exchange.getRequestMethod())) {

            String responseString = "Persona creada";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String name;
            String about;
            int id;
            int birthYear;

            //Guardamos en las variables creadas los parametros introducidos

            id = Integer.parseInt(params.get("id"));
            name = params.get("name");
            about = params.get("about");
            birthYear = Integer.parseInt(params.get("birthYear"));
            Person person = new Person(id, name, about, birthYear);
            store.putPerson(person);

            //Enviamos el string creado anteriormente y hacemos flush
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        //Metodo DELETE

        else if ("DELETE".equals(exchange.getRequestMethod())) {

            String responseString = "Persona borrada";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            //Creamos una variable donde guardamos el nombre que queramos borrar
            String idBorrar = params.get("id"); //Mediante params cogemos el parametro

            Person personBorrar = store.getPerson(Integer.parseInt(idBorrar));
            store.delPerson(personBorrar);

            //Enviamos el string creado y hacemos flush
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        //Metodo PUT (Actualizar)

        else if ("PUT".equals(exchange.getRequestMethod())) {

            String responseString = "Persona actualizada";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            //Creamos variables que guarden los nuevos datos para actualizar
            String about;
            int birthYear;
            String name;
            name = params.get("name");
            about = params.get("about");
            birthYear = Integer.parseInt(params.get("birthYear"));
            //Obtenemos el nombre de la persona para actualizarla
            String idActualizar = params.get("id"); 

            //Cogemos la persona que haya guardada con el nombre en la variable de antes
            Person personaActualizar = store.getPerson(Integer.parseInt(idActualizar));

            //Ahora creamos otra variable tipo person con los datos nuevos
            Person personaActualizar2 = new Person(Integer.parseInt(idActualizar), name, about, birthYear);

            //Usando el metodo creado en el dataStore reemplazamos personActualizar por personActualizar2
            store.actPerson(personaActualizar, personaActualizar2);

            //Enviamos el string creado anteriormente y hacemos flush
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        else {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}
