import java.io.*;
import java.net.*;


public class ua3ej3frserver {

    public static void main(String[] args) throws IOException{

        ServerSocket nuevoserversocket = new ServerSocket(1000); //Se crea el socket con un puerto que queramos coincidiendo con el cliente
        Socket nuevosocket = nuevoserversocket.accept();

        BufferedReader auxentrada = new BufferedReader(new InputStreamReader(nuevosocket.getInputStream()));
        PrintWriter auxsalida = new PrintWriter(nuevosocket.getOutputStream(), true);
        
        String msjCliente = auxentrada.readLine(); //Recibimos el mensaje del cliente
        String devolucionmsj = msjCliente.toUpperCase();
        
        auxsalida.println(devolucionmsj); //Se imprime por pantalla la respuesta en mayusculas
        
        nuevosocket.close(); //Cerramos el socket
    }
}