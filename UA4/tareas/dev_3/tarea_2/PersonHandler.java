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
            String responseString = "";

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personId = params.get("id");

            try {

                if (personId != null) {
                    int id = Integer.parseInt(personId);
                    try {
                        Person person = store.getPerson(id);
                        if (person != null) {
                            responseString = "{";
                            responseString += "\"id\": \"" + person.getId() + "\",";
                            responseString += "\"name\": \"" + person.getName() + "\",";
                            responseString += "\"about\": \"" + person.getAbout() + "\",";
                            responseString += "\"birthYear\": " + person.getBirthYear() + "";
                            responseString += "}";
                            exchange.sendResponseHeaders(200, responseString.getBytes().length);

                        } else {
                            responseString = "Not Found";
                            exchange.sendResponseHeaders(404, responseString.getBytes().length);
                        }
                    } catch (NumberFormatException e1) {
                        responseString = "Bad Request";
                        exchange.sendResponseHeaders(400, responseString.getBytes().length);
                    }

                } else {
                    String personName = params.get("name");

                    if (personName != null) {
                        try {
                            Person person = store.getPerson(personName);
                            if (person != null) {
                                responseString = "{";
                                responseString += "\"id\": \"" + person.getId() + "\",";
                                responseString += "\"name\": \"" + person.getName() + "\",";
                                responseString += "\"about\": \"" + person.getAbout() + "\",";
                                responseString += "\"birthYear\": " + person.getBirthYear() + "";
                                responseString += "}";
                                exchange.sendResponseHeaders(200, responseString.getBytes().length);

                            } else {
                                responseString = "Not Found";
                                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                            }
                        } catch (Exception e) {
                        }
                        }
                    }
            } catch (Exception e1) {
                responseString = "Bad Request";
                exchange.sendResponseHeaders(400, responseString.getBytes().length);
            }


            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

            /*
             * In the real world this part should be implemented this way:
             * JSONObject jsonObject = new JSONObject();
             * jsonObject.put("name", person.getName());
             * jsonObject.put("about", person.getAbout());
             * jsonObject.put("birthYear", person.getBirthYear());
             * responseString = jsonObject.toString();
             */

            }


        else if ("POST".equals(exchange.getRequestMethod())) {
            // 1. Obtener los datos de la persona de la URL
            String nombrePersona = "", descripcion = "", fechaNacimiento = "", id = "";
            String responseString = "";

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            id = params.get("id");
            nombrePersona = params.get("name");
            descripcion = params.get("about");
            fechaNacimiento = params.get("birthYear");

            if (id != null) {
                try {

                    // 2. Crear el objeto Persona
                    Person nuevaPersona = new Person(Integer.parseInt(id), nombrePersona, descripcion,
                            Integer.parseInt(fechaNacimiento));

                    // 3. Guardar la nueva persona en el almacen de personas
                    store.putPerson(nuevaPersona);

                    // 4. Devolver código 200
                    responseString = " LA NUEVA PERSONA CON ID " + id + " HA SIDO INTRODUCIDA CON EXITO";
                    responseString += "\n Los datos son:";
                    responseString += "\n id: " + nuevaPersona.getId();
                    responseString += "\n nombre: " + nuevaPersona.getName();
                    responseString += "\n descripción: " + nuevaPersona.getAbout();
                    responseString += "\n año de nacimiento: " + nuevaPersona.getBirthYear();
                    exchange.sendResponseHeaders(200, responseString.getBytes().length);
                } catch (NumberFormatException e1) {
                    responseString = "Bad Request";
                    exchange.sendResponseHeaders(400, responseString.getBytes().length);
                }

            }

            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

        }

        else if ("PUT".equals(exchange.getRequestMethod())) {

            // 1. Obtener los datos de la persona de la URL
            String nombrePersona = "", descripcion = "", fechaNacimiento = "", id = "";
            String responseString = "";

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            id = params.get("id");
            nombrePersona = params.get("name");
            descripcion = params.get("about");
            fechaNacimiento = params.get("birthYear");

            if (id != null) {
                try {

                    Person person = store.getPerson(Integer.parseInt(id));

                    Person nuevaPersona = new Person(Integer.parseInt(id), nombrePersona, descripcion,
                            Integer.parseInt(fechaNacimiento));
                    if (person != null) {
                        responseString = "{";
                        responseString += "\"id\": \"" + nuevaPersona.getId() + "\",";
                        responseString += "\"name\": \"" + nuevaPersona.getName() + "\",";
                        responseString += "\"about\": \"" + nuevaPersona.getAbout() + "\",";
                        responseString += "\"birthYear\": " + nuevaPersona.getBirthYear() + "";
                        responseString += "}";
                        exchange.sendResponseHeaders(200, responseString.getBytes().length);

                    } else {
                        responseString = "Not Found";
                        exchange.sendResponseHeaders(404, responseString.getBytes().length);
                    }
                } catch (NumberFormatException e1) {
                    responseString = "Bad Request";
                    exchange.sendResponseHeaders(400, responseString.getBytes().length);
                }

            }

            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

        }

        else if ("DELETE".equals(exchange.getRequestMethod())) {
            // 1. Obtener los datos de la persona de la URL
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String idPersona = params.get("id");
            String responseString = "";

            // 2. Borrar la persona con el nombre indicado


            if (idPersona != null) {
                try {
                    Person person = store.getPerson(Integer.parseInt(idPersona));
                    if (person != null) {

                        store.delPerson(Integer.parseInt(idPersona));

                        responseString = " LA PERSONA CON ID " + idPersona + " HA SIDO ELIMINADA CON EXITO";
                        exchange.sendResponseHeaders(200, responseString.getBytes().length);

                    } else {
                        responseString = "Not Found";
                        exchange.sendResponseHeaders(404, responseString.getBytes().length);
                    }
                } catch (NumberFormatException e1) {
                    responseString = "Bad Request";
                    exchange.sendResponseHeaders(400, responseString.getBytes().length);
                }

            }

            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();

            // 4. Devolver código 200

            output.write(responseString.getBytes());
            output.flush();
        }
        exchange.close();
    }
}
