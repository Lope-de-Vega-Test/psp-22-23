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

            //Cogemos el parametro con el nombre y lo guardamos en una variable
            String personId = params.get("id");

            //Creamos una variable person y usando el metodo creado le ponemos de nombre la variable creada antes
            Person person = store.getPerson(Integer.parseInt(personId));

            //Control de errores
            if (person == null) {
            responseString = "Persona inexistente";
            exchange.sendResponseHeaders(404, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
            return;
            }

            
             /*
             * In the real world this part should be implemented this way:
             * JSONObject jsonObject = new JSONObject();
             * jsonObject.put("name", person.getName());
             * jsonObject.put("about", person.getAbout());
             * jsonObject.put("birthYear", person.getBirthYear());
             * responseString = jsonObject.toString();
             */

             //Usando el mismo metodo de antes, ponemos cada variable con el dato que le hayamos introducido

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

        //Metodo POST (Añadir persona)

        else if ("POST".equals(exchange.getRequestMethod())) {

            String responseString = "{";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String name;
            String about;
            int id;
            int birthYear;

            //Guardamos en las variables creadas los parametros introducidos usando params

            id = Integer.parseInt(params.get("id"));
            name = params.get("name");
            about = params.get("about");
            birthYear = Integer.parseInt(params.get("birthYear"));

            Person person = new Person(id, name, about, birthYear);

            store.putPerson(person);

            responseString += "\"id\": \"" + person.getId() + "\",";
            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";

            //Enviamos el string creado anteriormente y hacemos flush
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        //Metodo DELETE (Borrar persona)

        else if ("DELETE".equals(exchange.getRequestMethod())) {

            String responseString = "{";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            //Creamos una variable donde guardamos el nombre que queramos borrar
            String idBorrar = params.get("id"); //Mediante params cogemos el parametro

            Person personBorrar = store.getPerson(Integer.parseInt(idBorrar));
            store.delPerson(personBorrar);

            responseString += "\"id\": \"" + personBorrar.getId() + "\",";
            responseString += "\"name\": \"" + personBorrar.getName() + "\",";
            responseString += "\"about\": \"" + personBorrar.getAbout() + "\",";
            responseString += "\"birthYear\": " + personBorrar.getBirthYear() + "";
            responseString += "}";

            //Control de errores
            if (personBorrar == null) {
                responseString = "Persona inexistente";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
                return;
                }

            //Enviamos el string creado anteriormente y hacemos flush
            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
        }

        //Metodo PUT (Actualizar)

        else if ("PUT".equals(exchange.getRequestMethod())) {

            String responseString = "Persona actualizada";
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            //Creamos variables que guarden los datos nuevos
            String about;
            int birthYear;
            String name;

            name = params.get("name");
            about = params.get("about");
            birthYear = Integer.parseInt(params.get("birthYear"));

            String idActualizar = params.get("id"); //Obtenemos el nombre de la persona que queramos actualizar

            //En una variable tipo person cogemos la persona que haya guardada 
            //con el nombre que hemos recogido en la variable de antes
            Person personActualizar = store.getPerson(Integer.parseInt(idActualizar));

            //Ahora creamos otra variable tipo person con los datos nuevos
            Person personActualizar2 = new Person(Integer.parseInt(idActualizar), name, about, birthYear);

            //Usando el metodo creado en el dataStore reemplazamos personActualizar por personActualizar2
            store.actPerson(personActualizar, personActualizar2);

            //Control de errores
            if (personActualizar == null) {
                responseString = "Persona inexistente";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
                return;
                }

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
