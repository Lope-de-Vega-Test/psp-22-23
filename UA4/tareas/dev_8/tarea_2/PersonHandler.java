package UA4.tareas.dev_8.tarea_2;

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

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            // String personName = params.get("name");
            // Person person = store.getPerson(personName);

            // Busqueda por id
            if (params.get("name") == null && params.get("about") == null && params.get("birthYear") == null) {

                // Obtenemos la información de la url
                System.out.println("id: " + params.get("id"));
                int id = Integer.parseInt(params.get("id"));
                Person person = store.getPersonById(id);

                // En este caso la petición es no correcta
                if (person == null) {
                    exchange.sendResponseHeaders(404, -1); // Se envia un 404
                    exchange.close();
                    return;
                }

                // enviamos la información en JSON
                String responseString = "{";
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";

                // En este caso la petición es correcta
                exchange.sendResponseHeaders(200, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }

            /*
             * In the real world this part should be implemented this way:
             * JSONObject jsonObject = new JSONObject();
             * jsonObject.put("name", person.getName());
             * jsonObject.put("about", person.getAbout());
             * jsonObject.put("birthYear", person.getBirthYear());
             * responseString = jsonObject.toString();
             */

        } else if ("POST".equals(exchange.getRequestMethod())) {

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            // Obtenemos la información de la url
            String personName = params.get("name");
            String personAbout = params.get("about");
            int personbirthYear = Integer.parseInt(params.get("birthYear"));

            // Validamos que no esten vacios los campos
            if (personName == "" || personAbout == "" || personName == null || personAbout == null) {
                // si falta algun parametro, salta error 400
                exchange.sendResponseHeaders(400, -1);
                exchange.close();
                return;
            }
            try {

                Person person = new Person(personName, personAbout, personbirthYear);

                store.putPerson(person);

                String responseString = "{";
                responseString += "\"id\": \"" + person.getId() + "\",";
                responseString += "\"name\": \"" + person.getName() + "\",";
                responseString += "\"about\": \"" + person.getAbout() + "\",";
                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                responseString += "}";

                exchange.sendResponseHeaders(200, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            } catch (Exception e) {
                // si el año no es un numero correcto, error 400
                exchange.sendResponseHeaders(400, -1);
                exchange.close();
            }

        } else if ("PUT".equals(exchange.getRequestMethod())) {

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            int personId = Integer.parseInt(params.get("id"));
            String personName = params.get("name");
            String personAbout = params.get("about");
            int personbirthYear = Integer.parseInt(params.get("birthYear"));

            Person person = store.updatePerson(personId, personName, personAbout, personbirthYear);

            String responseString = "{";
            responseString += "\"id\": \"" + person.getId() + "\",";
            responseString += "\"name\": \"" + person.getName() + "\",";
            responseString += "\"about\": \"" + person.getAbout() + "\",";
            responseString += "\"birthYear\": " + person.getBirthYear() + "";
            responseString += "}";

            exchange.sendResponseHeaders(200, responseString.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

        } else if ("DELETE".equals(exchange.getRequestMethod())) {

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            int id = Integer.parseInt(params.get("id"));

            String responseString = "{";

            if (store.removePerson(id)) {
                responseString += "0: \"" + id + " eliminado\",";
                responseString += "}";
            } else {
                responseString += "0: \"" + "no eliminado\",";
                responseString += "}";
            }

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