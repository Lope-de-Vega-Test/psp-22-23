package paquete;

 
import java.io.*;
import java.net.*;
 
// Main class
public class ua3fr2_mainserver {
 
    public static void main(String[] args)
    {
 
        // trycatch para controlar errores
        try {
 
            // Crea un objeto en la clase ServerSocket

            ServerSocket ss = new ServerSocket(6001);
 
            // Establece conexion
            Socket soc = ss.accept();
 
            // Crea un objeto en la clase DataInputStream
            DataInputStream dis = new DataInputStream(soc.getInputStream());
 
            String str = (String)dis.readUTF();
 
            //Muestra el mensaje en la consola
            System.out.println("mensaje= " + str);
 
            // Cierra el socket
            ss.close();
        }
 
        // Controlador de errores
        catch (Exception e) {
 
            System.out.println(e);
        }
    }
}