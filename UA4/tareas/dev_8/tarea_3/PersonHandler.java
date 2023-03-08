package UA4.tareas.dev_8.tarea_3;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

class PersonHandler extends BasicHandler {

    public PersonHandler(DataBase dataBase) {
        super(dataBase);
    }

    public void handle(HttpExchange exchange) throws IOException {

        JSONObject json = new JSONObject();
        
        JSONObject header = new JSONObject();
        header.put("api_name", "My API Name");
        header.put("api_version", "1.0.0");
        header.put("api_user", "guest");
        header.put("api_endpoint", "api/bye");
        header.put("http_request_method", "GET");
        header.put("http_request_parameters", "X.X.X");
        header.put("http_response_status", 200);
        header.put("http_response_message", "Bad Request");
        header.put("response_time", 123456789);

        JSONObject body = new JSONObject();
        body.put("response_time", "HOLA");
                        
        json.put("header", header);
        json.put("body", body);



        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        if ("GET".equals(exchange.getRequestMethod())) {

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

            // String personName = params.get("name");
            // Person person = dataBase.getPerson(personName);

            // Busqueda por id
            if (params.get("name") == null && params.get("about") == null && params.get("birthYear") == null) {

                // Obtenemos la información de la url
                System.out.println("id: " + params.get("id"));
                int id = Integer.parseInt(params.get("id"));
                Person person = dataBase.getPersonById(id);

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

                dataBase.putPerson(person);

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

            Person person = dataBase.updatePerson(personId, personName, personAbout, personbirthYear);

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

            if (dataBase.removePerson(id)) {
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