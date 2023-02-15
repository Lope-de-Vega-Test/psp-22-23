import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PersonHandler extends BasicHandler {
    String remoteAddress;
    DataStore store;

    String response;

    public PersonHandler(DataStore store) {
        super(store);
        this.store = store;
    }

    // AUTOMATIC PROCESS -- PERSON
    public void handle(HttpExchange exchange) throws IOException {
        String remoteAddress = exchange.getRemoteAddress().getHostString();
        System.out.println(
                "[" + new Date() + "] Received GET " + exchange.getRequestURI() + " from client: " + remoteAddress);

        response = "{";

        switch (exchange.getRequestMethod()) {

            // GET
            case "GET": {
                handleGet(exchange);
            }
                break;
            // POST
            case "POST": {
                handlePost(exchange);
            }
                break;
            // PUT
            case "PUT": {
                handlePut(exchange);
            }
                break;
            // DELETE
            case "DELETE": {
                handleDelete(exchange);
            }
                break;
            // ANY OTHER METHODS
            default: {
                response += "\"code\": 405, \"message\": \"method not allowed\"}";
                exchange.sendResponseHeaders(405, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
            }
        }

    }

    /**
     * 
     * Handler for getting a Person from our local moc database
     * 
     * @param exchange
     * @throws IOException
     */
    public void handleGet(HttpExchange exchange) throws IOException {
        // GET PARAMETERS MAP FORMAT "KEY" : "VALUE"
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        if (params.size() == 1) { // IF THERE IS ONLY ONE PARAMETER
            String $param = params.get("id"); // CHECK FOR PARAMETER 'ID'
            if ($param != null) { // IF PARAMETER 'ID' IS SET
                try {
                    int id = Integer.parseInt($param);
                    Person person = store.getPerson(id);
                    if (person != null) { // IF PERSON EXISTS
                        response += "\"code\": 200, \"message\": \"ok\", ";
                        response += "\"persons\": [{";
                        response += "\"name\": \"" + person.getName() + "\", ";
                        response += "\"about\": \"" + person.getAbout() + "\", ";
                        response += "\"birthYear\": " + person.getBirthYear() + "}]";
                        response += "}";
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                    } else { // IF PERSON DOES NOT EXIST o BAD USER INPUT
                        response += "\"code\": 404, \"message\": \"not found\"}";
                        exchange.sendResponseHeaders(404, response.getBytes().length);
                    }
                } catch (NumberFormatException e) { // INTEGER PARSE GONE WRONG
                    response += "\"code\": 400, \"message\": \"bad request\"}";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                }

            } else { // CHECK FOR PARAMETER 'NAME'
                $param = params.get("name");
                if ($param != null) { // IF PAREMETER 'NAME' IS SET
                    ArrayList<Person> persons = new ArrayList<Person>();
                    persons = store.getPersonsByName($param); // GET PERSON LIST BY NAME (STRING)
                    if (persons.size() > 0) { // IF PERSON ARRAYLIST IS NOT EMPTY
                        response += "\"code\": 200, \"message\": \"ok\", ";
                        response += "\"persons\": [";
                        int i = 0;
                        for (Person person : persons) {
                            i++;
                            response += "{\"name\": \"" + person.getName() + "\", ";
                            response += "\"about\": \"" + person.getAbout() + "\", ";
                            response += "\"birthYear\": " + person.getBirthYear() + "}";
                            if (i != persons.size()) {
                                response += ',';
                            }
                        }
                        response += "]}";
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                    } else { // IF PERSON DOES NOT EXIST o BAD USER INPUT
                        response += "\"code\": 404, \"message\": \"not found\"}";
                        exchange.sendResponseHeaders(404, response.getBytes().length);
                    }
                } else { // CHECK FOR PARAMETHER 'BIRTHYEAR'
                    $param = params.get("birthYear");
                    if ($param != null) {
                        try {
                            ArrayList<Person> persons = new ArrayList<Person>();
                            persons = store.getPersonsByBirthYear(Integer.parseInt($param)); // GET PERSON LIST BY
                                                                                             // BIRTHYEAR (INT)
                            if (persons.size() > 0) { // IF PERSON ARRAYLIST IS NOT EMPTY
                                response += "\"code\": 200, \"message\": \"ok\", ";
                                response += "\"persons\": [";
                                int i = 0;
                                for (Person person : persons) {
                                    i++;
                                    response += "{\"name\": \"" + person.getName() + "\", ";
                                    response += "\"about\": \"" + person.getAbout() + "\", ";
                                    response += "\"birthYear\": " + person.getBirthYear() + "}";
                                    if (i != persons.size()) {
                                        response += ',';
                                    }
                                }
                                response += "]}";
                                exchange.sendResponseHeaders(200, response.getBytes().length);
                            } else { // IF PERSON DOES NOT EXIST o BAD USER INPUT
                                response += "\"code\": 404, \"message\": \"not found\"}";
                                exchange.sendResponseHeaders(404, response.getBytes().length);
                            }
                        } catch (NumberFormatException e) { // INTEGER PARSING GONE WRONG
                            response += "\"code\": 400, \"message\": \"bad request\"}";
                            exchange.sendResponseHeaders(400, response.getBytes().length);
                        }

                    } else { // IF NONE OF THE PARAMETERS REQUIRED ARE SET
                        response += "\"code\": 400, \"message\": \"bad request\"}";
                        exchange.sendResponseHeaders(400, response.getBytes().length);
                    }
                }
            }
        } else { // THERES A DIFFERENT NUMBER OF PARAMETERS
            response += "\"code\": 400, \"message\": \"bad request\"}";
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
        exchange.close();
    }

    /**
     * Handler for creating a Person at our local mock database
     * 
     * @param exchange
     * @throws IOException
     */
    public void handlePost(HttpExchange exchange) throws IOException {
        // GET PARAMETERS MAP FORMAT "KEY" : "VALUE"
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        if (params.size() == 3) { // IF THERE IS ONLY FOUR PARAMETER
            String $name = params.get("name"); // GET VALUE FOR KEYNAME "name"
            String $about = params.get("about"); // GET VALUE FOR KEYNAME "about"
            String $birthYear = params.get("birthYear"); // GET VALUE FOR KEYNAME "birthYear"

            if ($name != null & $about != null && $birthYear != null) { // IF PARAMETERS
                                                                        // ARE SET
                try { // TRIES TO CONVERT $birthYear TO A INTEGER VALUE
                    int birthYear = Integer.parseInt($birthYear);

                    Person person = new Person($name, $about, birthYear);
                    store.addPerson(person);
                    response += "\"code\": 200, \"message\": \"ok\"}";
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                } catch (NumberFormatException e1) {
                    response += "\"code\": 400, \"message\": \"bad request\"}";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                }

            } else { // IF PARAMETER ARE NOT SET
                response += "\"code\": 400, \"message\": \"bad request\"}";
                exchange.sendResponseHeaders(400, response.getBytes().length);
            }
        } else { // THERE IS A DIFFERENT NUMBER OF PARAMETERS
            response += "\"code\": 400, \"message\": \"bad request\"}";
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
        exchange.close();
    }

    /**
     * Handler for modifying a Person in our local mock database
     * 
     * @param exchange
     * @throws IOException
     */
    public void handlePut(HttpExchange exchange) throws IOException {
        // GET PARAMETERS MAP FORMAT "KEY" : "VALUE"
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        String $id = params.get("id"); // GET VALUE FOR KEY "name"
        String $name = params.get("name");
        String $about = params.get("about");
        String $birthYear = params.get("birthYear");

        int paramsRead = 0;

        if ($id != null) { // IF PARAMETERS
                           // ARE SET
            try { // TRIES TO CONVERT $birthYear TO A INTEGER VALUE
                int id = Integer.parseInt($id);
                Person person = store.getPerson(id); // GET THE PERSON

                if (person != null) { // IF PERSON EXISTS
                    if ($name != null) {
                        paramsRead++;
                        person.setName($name);
                    }

                    if ($about != null) {
                        paramsRead++;
                        person.setAbout($about);
                    }

                    if ($birthYear != null) {
                        try {
                            int birthYear = Integer.parseInt($birthYear);
                            paramsRead++;
                            person.setBirthYear(birthYear);
                        } catch (NumberFormatException e) {
                            // ----
                        }
                    }

                    store.modifyPerson(id, person);
                    response += "\"code\": 200, \"message\": \"ok\"";
                    if (paramsRead == 0) {
                        response += ", \"info\": \"nothing changed\"";
                    }
                    response += '}';
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                } else { // IF PERSON DOES NOT EXIST o BAD USER INPUT
                    response += "\"code\": 404, \"message\": \"not found\"}";
                    exchange.sendResponseHeaders(404, response.getBytes().length);
                }
            } catch (NumberFormatException e1) { // PARSING INTEGER ('ID') GONE WRONG
                response += "\"code\": 400, \"message\": \"bad request\"}";
                exchange.sendResponseHeaders(400, response.getBytes().length);
            }

        } else { // IF PARAMETER 'ID' IS NOT SET
            response += "\"code\": 400, \"message\": \"bad request\"}";
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
        exchange.close();
    }

    /**
     * Handler for deleting a Person from our local mock database
     * 
     * @param exchange
     * @throws IOException
     * 
     */
    public void handleDelete(HttpExchange exchange) throws IOException {
        // GET PARAMETERS MAP FORMAT "KEY" : "VALUE"
        Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
        if (params.size() == 1) { // IF THERE IS ONLY ONE PARAMETER
            String $id = params.get("id"); // GET VALUE FOR KEY "name"

            if ($id != null) { // IF PARAMETER IS SET
                try {
                    int id = Integer.parseInt($id);
                    Person person = store.getPerson(id);
                    if (person != null) { // IF PERSON EXISTS
                        store.deletePerson(id);
                        response += "\"code\": 200, \"message\": \"ok\"}";
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                    } else { // IF PERSON DOES NOT EXIST o BAD USER INPUT
                        response += "\"code\": 404, \"message\": \"not found\"}";
                        exchange.sendResponseHeaders(404, response.getBytes().length);
                    }
                } catch (NumberFormatException e1) {
                    response += "\"code\": 400, \"message\": \"bad request\"}";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                }

            } else { // IF PARAMETER IS NOT SET
                response += "\"code\": 400, \"message\": \"bad request\"}";
                exchange.sendResponseHeaders(400, response.getBytes().length);
            }
        } else { // THERES A DIFFERENT NUMBER OF PARAMETERS
            response += "\"code\": 400, \"message\": \"bad request\"}";
            exchange.sendResponseHeaders(400, response.getBytes().length);
        }
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
        exchange.close();
    }
}