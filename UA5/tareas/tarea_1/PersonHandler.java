
package com.victoraljama;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

class PersonHandler extends BasicHandler {
    public PersonHandler(DataStorage store) {
        super(store);
    }

    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);
        
        long timeStart=System.currentTimeMillis();

        if ("GET".equals(exchange.getRequestMethod())) {

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            if (params.size() == 1) { // Control para que no exceda el numero de parametros correcto
                String buscarPersona = params.get("id"); // Buscar personas mediante su id
                if (buscarPersona != null) {
                    try {
                        int id = Integer.parseInt(buscarPersona);
                        Person person = store.getPerson(id);
                        if (person != null) {
                            try {
                                JsonObject response = Json.createObjectBuilder()
                                    .add("header", Json.createObjectBuilder()
                                        .add("name", "api Victor Aljama")
                                        .add("api_version", "1.0.0")
                                        .add("api_user", "guest")
                                        .add("api_endpoint", "api/person")
                                        .add("http_request_method", "GET")
                                        .add("http_request_parameters", buscarPersona)
                                        .add("api_user", "guest")
                                        .add("http_response_status", 200)
                                        .add("http_response_message", "OK")
                                        .add("response_time",(System.currentTimeMillis()-timeStart))
                                    .build())

                                    .add("body", Json.createObjectBuilder()
                                        .add("person",Json.createObjectBuilder()
                                            .add("name", person.getName())
                                            .add("about", person.getAbout())
                                            .add("birthYear", person.getBirthYear())
                                        .build())
                                        
                                    .build())
                        
                                .build();
                
                                exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                                exchange.getResponseBody().write(response.toString().getBytes());
                            } catch (Exception e) {
                                //nothing to do
                            }
                        } else { // No existe la persona
                            try {
                                JsonObject response = Json.createObjectBuilder()
                                    .add("header", Json.createObjectBuilder()
                                        .add("name", "api Victor Aljama")
                                        .add("api_version", "1.0.0")
                                        .add("api_user", "guest")
                                        .add("api_endpoint", "api/person")
                                        .add("http_request_method", "GET")
                                        .add("http_request_parameters", buscarPersona)
                                        .add("api_user", "guest")
                                        .add("http_response_status", 404)
                                        .add("http_response_message", "not found")
                                        .add("response_time",(System.currentTimeMillis()-timeStart))
                                    .build())

                                    .add("body", Json.createObjectBuilder()
                                        .add("mensaje", "not found")
                                    .build())
                        
                                .build();
                
                                exchange.sendResponseHeaders(404, response.toString().getBytes().length);
                                exchange.getResponseBody().write(response.toString().getBytes());
                            } catch (Exception e) {
                                //nothing to do
                            }
                        }
                    } catch (NumberFormatException e) {
                        try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "GET")
                                    .add("http_request_parameters", buscarPersona)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 400)
                                    .add("http_response_message", "bad request")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("mensaje", "bad request")
                                .build())

                            .build();

                            exchange.sendResponseHeaders(400, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e1) {
                            //nothing to do
                        }
                    }

                } else { // Buscar mediante el nombre
                    buscarPersona = params.get("name");
                    if (buscarPersona != null) {
                        ArrayList<Person> persons = new ArrayList<Person>();
                        persons = store.getPersonsByName(buscarPersona); // Crear un ArrayList con las personas que tengan el nombre introducido
                        if (persons.size() > 0) { // Si hay algun registro de persona con el nombre introducido
                            int i = 0;
                            for (Person person : persons) {
                                i++;
                                try {
                                    JsonObject response = Json.createObjectBuilder()
                                        .add("header", Json.createObjectBuilder()
                                            .add("name", "api Victor Aljama")
                                            .add("api_version", "1.0.0")
                                            .add("api_user", "guest")
                                            .add("api_endpoint", "api/person")
                                            .add("http_request_method", "GET")
                                            .add("http_request_parameters", buscarPersona)
                                            .add("api_user", "guest")
                                            .add("http_response_status", 200)
                                            .add("http_response_message", "OK")
                                            .add("response_time",(System.currentTimeMillis()-timeStart))
                                        .build())

                                        .add("body", Json.createObjectBuilder()
                                            .add("person",Json.createObjectBuilder()
                                                .add("name", person.getName())
                                                .add("about", person.getAbout())
                                                .add("birthYear", person.getBirthYear())
                                            .build())
                                        .build())
                                            
                                    .build();

                                    exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                                    exchange.getResponseBody().write(response.toString().getBytes());
                                } catch (Exception e) {
                                    //nothing to do
                                }
                                
                            }
                        } else { // No existe la persona
                            try {
                                JsonObject response = Json.createObjectBuilder()
                                    .add("header", Json.createObjectBuilder()
                                        .add("name", "api Victor Aljama")
                                        .add("api_version", "1.0.0")
                                        .add("api_user", "guest")
                                        .add("api_endpoint", "api/person")
                                        .add("http_request_method", "GET")
                                        .add("http_request_parameters", buscarPersona)
                                        .add("api_user", "guest")
                                        .add("http_response_status", 404)
                                        .add("http_response_message", "not found")
                                        .add("response_time",(System.currentTimeMillis()-timeStart))
                                    .build())

                                    .add("body", Json.createObjectBuilder()
                                        .add("mensaje", "not found")
                                    .build())
                        
                                .build();
                
                                exchange.sendResponseHeaders(404, response.toString().getBytes().length);
                                exchange.getResponseBody().write(response.toString().getBytes());
                            } catch (Exception e) {
                                //nothing to do
                            }
                        }
                    }
                }
            } else { // Discordancia de parámetros
                try {
                    JsonObject response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                            .add("name", "api Victor Aljama")
                            .add("api_version", "1.0.0")
                            .add("api_user", "guest")
                            .add("api_endpoint", "api/person")
                            .add("http_request_method", "GET")
                            .add("api_user", "guest")
                            .add("http_response_status", 400)
                            .add("http_response_message", "bad request")
                            .add("response_time",(System.currentTimeMillis()-timeStart))
                        .build())

                        .add("body", Json.createObjectBuilder()
                            .add("mensaje", "bad request")
                        .build())

                    .build();

                    exchange.sendResponseHeaders(400, response.toString().getBytes().length);
                    exchange.getResponseBody().write(response.toString().getBytes());
                } catch (Exception e1) {
                    //nothing to do
                }
            }
            exchange.close();
        }

        else if ("POST".equals(exchange.getRequestMethod())) {
            // 1. Obtener los datos de la persona de la URL
            String nombrePersona = "", descripcion = "", fechaNacimiento = "";
            String responseString = "";

            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            nombrePersona = params.get("name");
            descripcion = params.get("about");
            fechaNacimiento = params.get("birthYear");

            try {

                // 2. Crear el objeto Persona
                Person nuevaPersona = new Person(nombrePersona, descripcion, Integer.parseInt(fechaNacimiento));

                // 3. Guardar la nueva persona en el almacen de personas
                store.putPerson(nuevaPersona);

                // 4. Devolver código 200
                try {
                    JsonObject response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                            .add("name", "api Victor Aljama")
                            .add("api_version", "1.0.0")
                            .add("api_user", "guest")
                            .add("api_endpoint", "api/person")
                            .add("http_request_method", "POST")
                            .add("http_request_parameters", nombrePersona + "," + descripcion + "," + fechaNacimiento)
                            .add("api_user", "guest")
                            .add("http_response_status", 200)
                            .add("http_response_message", "OK")
                            .add("response_time",(System.currentTimeMillis()-timeStart))
                        .build())

                        .add("body", Json.createObjectBuilder()
                            .add("name", nombrePersona)
                            .add("about", descripcion)
                            .add("birthYear", fechaNacimiento)
                        .build())

                    .build();

                    exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                    exchange.getResponseBody().write(response.toString().getBytes());
                } catch (Exception e) {
                    //nothing to do
                }
            } catch (NumberFormatException e1) {
                try {
                    JsonObject response = Json.createObjectBuilder()
                        .add("header", Json.createObjectBuilder()
                            .add("name", "api Victor Aljama")
                            .add("api_version", "1.0.0")
                            .add("api_user", "guest")
                            .add("api_endpoint", "api/person")
                            .add("http_request_method", "POST")
                            .add("http_request_parameters", nombrePersona + "," + descripcion + "," + fechaNacimiento)
                            .add("api_user", "guest")
                            .add("http_response_status", 405)
                            .add("http_response_message", "bad request")
                            .add("response_time",(System.currentTimeMillis()-timeStart))
                        .build())

                        .add("body", Json.createObjectBuilder()
                            .add("mensaje", "bad request")
                        .build())

                    .build();

                    exchange.sendResponseHeaders(405, response.toString().getBytes().length);
                    exchange.getResponseBody().write(response.toString().getBytes());
                } catch (Exception e) {
                    //nothing to do
                }
            }

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

                    Person nuevaPersona = new Person(nombrePersona, descripcion, Integer.parseInt(fechaNacimiento));
                    if (person != null) {
                        
                        try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "PUT")
                                    .add("http_request_parameters", id + "," + nombrePersona + "," + descripcion + "," + fechaNacimiento)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 200)
                                    .add("http_response_message", "OK")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("name", nombrePersona)
                                    .add("about", descripcion)
                                    .add("birthYear", fechaNacimiento)
                                .build())

                            .build();

                            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e) {
                            //nothing to do
                        }

                    } else {
                        try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "PUT")
                                    .add("http_request_parameters", id + "," + nombrePersona + "," + descripcion + "," + fechaNacimiento)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 404)
                                    .add("http_response_message", "not found")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("mensaje", "not found")
                                .build())

                            .build();

                            exchange.sendResponseHeaders(404, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e) {
                            //nothing to do
                        }
                    }
                } catch (NumberFormatException e1) {
                    try {
                        JsonObject response = Json.createObjectBuilder()
                            .add("header", Json.createObjectBuilder()
                                .add("name", "api Victor Aljama")
                                .add("api_version", "1.0.0")
                                .add("api_user", "guest")
                                .add("api_endpoint", "api/person")
                                .add("http_request_method", "PUT")
                                .add("http_request_parameters", id + "," + nombrePersona + "," + descripcion + "," + fechaNacimiento)
                                .add("api_user", "guest")
                                .add("http_response_status", 400)
                                .add("http_response_message", "bad request")
                                .add("response_time",(System.currentTimeMillis()-timeStart))
                            .build())

                            .add("body", Json.createObjectBuilder()
                                .add("mensaje", "bad request")
                            .build())

                        .build();

                        exchange.sendResponseHeaders(400, response.toString().getBytes().length);
                        exchange.getResponseBody().write(response.toString().getBytes());
                    } catch (Exception e) {
                        //nothing to do
                    }
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

                        try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "PUT")
                                    .add("http_request_parameters", idPersona)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 200)
                                    .add("http_response_message", "OK")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("mensaje", "La persona con id: " + idPersona + " ha sido eliminada con exito")
                                .build())

                            .build();

                            exchange.sendResponseHeaders(200, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e) {
                            //nothing to do
                        }

                    } else {
                        try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "PUT")
                                    .add("http_request_parameters", idPersona)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 404)
                                    .add("http_response_message", "not found")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("mensaje", "not found")
                                .build())

                            .build();

                            exchange.sendResponseHeaders(404, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e) {
                            //nothing to do
                        }
                    }
                } catch (NumberFormatException e1) {
                    try {
                            JsonObject response = Json.createObjectBuilder()
                                .add("header", Json.createObjectBuilder()
                                    .add("name", "api Victor Aljama")
                                    .add("api_version", "1.0.0")
                                    .add("api_user", "guest")
                                    .add("api_endpoint", "api/person")
                                    .add("http_request_method", "PUT")
                                    .add("http_request_parameters", idPersona)
                                    .add("api_user", "guest")
                                    .add("http_response_status", 400)
                                    .add("http_response_message", "bad request")
                                    .add("response_time",(System.currentTimeMillis()-timeStart))
                                .build())

                                .add("body", Json.createObjectBuilder()
                                    .add("mensaje", "bad request")
                                .build())

                            .build();

                            exchange.sendResponseHeaders(400, response.toString().getBytes().length);
                            exchange.getResponseBody().write(response.toString().getBytes());
                        } catch (Exception e) {
                            //nothing to do
                        }
                }

            }

        }
        exchange.close();
    }
}

