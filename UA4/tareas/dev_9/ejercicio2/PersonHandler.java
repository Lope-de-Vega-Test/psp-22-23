import java.io.OutputStream;
import java.util.Map;
import java.io.IOException;
import java.util.Date;
import com.sun.net.httpserver.HttpExchange;


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
            String personName = params.get("id");      
            int ID=Integer.parseInt(personName);  
            Person person = store.getPerson(ID);
            if(person==null){
                responseString="Person not found";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
            /* In the real world this part should be implemented this way:
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("about", person.getAbout());
            jsonObject.put("birthYear", person.getBirthYear());
            responseString = jsonObject.toString();
            */
            else{
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
        
        else if("POST".equals(exchange.getRequestMethod())){
            String responseString;
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String id=params.get("id");
            String name=params.get("name");
            String  about=params.get("about");
            String birth=params.get("birthYear");
            responseString="OK";
            int ID=Integer.parseInt(id);
            int birthYear=Integer.parseInt(birth);
            Person person=new Person(ID, name, about, birthYear);
            store.putPerson(person);
            exchange.sendResponseHeaders(200, responseString.getBytes().length);            
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush();
                
        }

        else if("DELETE".equals(exchange.getRequestMethod())){
            String responseString="Persona borrada";
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String personDelete=params.get("id");
            
            int ID=Integer.parseInt(personDelete);
            Person person=store.getPerson(ID);
            if(person==null){
                responseString="Person not found";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
            else{
                store.deleteperson(person);
                exchange.sendResponseHeaders(200, responseString.getBytes().length);            
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
        }
        
        else if("PUT".equals(exchange.getRequestMethod())){
            String responseString="Persona actualizada";;
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String id=params.get("id");
            String personAct=params.get("name");
            String  about=params.get("about");
            String birth=params.get("birthYear");
            
            int ID=Integer.parseInt(id);
            Person person=store.getPerson(ID);
            if(person==null){
                responseString="Person not found";
                exchange.sendResponseHeaders(404, responseString.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }
            else{
                int birthYear=Integer.parseInt(birth);
                Person person2=new Person(ID, personAct, about, birthYear);
                store.updatePerson(ID, person, person2);
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
