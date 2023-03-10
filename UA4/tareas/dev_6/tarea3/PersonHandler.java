
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
            try {
                handleGet(exchange);
            } catch (JSONException e) {
                e.printStackTrace();
            }      
        }else if("POST".equals(exchange.getRequestMethod())){

            //1.OBTENER LOS DATOS DE LA URL
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String name = params.get("name");
            String about = params.get("about");
            String birthYear = params.get("birthYear");
            if(name==null && about==null && birthYear==null){
                exchange.sendResponseHeaders(400, -1);
            }else{
                //2.CREO EL OBJETO PERSONA
                Person personanueva = new Person(name, about,Integer.parseInt(birthYear));


                //3.GUARDO LA NUEVA PERSONA EN EL ALMACEN DE PERSONAS
                store.putPerson(personanueva);

                //4.DEVUELVO 200
                String responseString = "usuario insertado correctamente";
                
                exchange.sendResponseHeaders(200, responseString.getBytes().length); 
                //5.ENVIA RESPUESTA
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush();
            }

        } 
        else if("PUT".equals(exchange.getRequestMethod())){
            //1.OBTENER LOS DATOS DE LA URL
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String name = params.get("name");
            
            String about = params.get("about");

            String birthYear = params.get("birthYear");

            //2.OBTENER LA PERSONA QUE QUEREMOS EDITAR   
            Person person = store.getPerson(name);
            
                Person personaactualizada = new Person(name, about,Integer.parseInt(birthYear));
                //3.GUARDO LA PERSONA EN EL ALMACEN
                store.udtPerson(name, personaactualizada);
                
                //4.DEVUELVO 200
                String responseString = "{";
                exchange.sendResponseHeaders(200, responseString.getBytes().length); 

                //5.ENVIA RESPUESTA
                OutputStream output = exchange.getResponseBody();
                output.write(responseString.getBytes());
                output.flush(); 
                //DEVUELVO ERROR
                System.out.println("la persona no es encontrada");
                exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
            
            
        }else if("DELETE".equals(exchange.getRequestMethod())){
             //1.OBTENER LOS DATOS DE LA URL
            Map <String,String> params = queryToMap(exchange.getRequestURI().getQuery());
            String name = params.get("name");
            String about = params.get("about");
            String birthYear = params.get("birthYear");

            //2.OBTENER LA PERSONA QUE QUEREMOS ELIMINAR  
            Person person = store.getPerson(name);
            
            //3.ELIMINO LA PERSONA 
            store.delPerson(name, person);
            
            //4.DEVUELVO 200
            String responseString = "{";
            exchange.sendResponseHeaders(200, responseString.getBytes().length); 

            //5.ENVIA RESPUESTA
            OutputStream output = exchange.getResponseBody();
            output.write(responseString.getBytes());
            output.flush(); 

            //DEVUELVO ERROR
            System.out.println("la persona no es encontrada");
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        else
        {
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}
