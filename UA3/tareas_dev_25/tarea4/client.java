/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea4;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author RafaelRomero
 */


public class client {
    
 //Creación variable    
 private String mensaje;

 //Creación constructor
        public client() {
                mensaje = "";
        }
        
        public String getMensaje() {
                return mensaje;
        }

        public void setMensaje(String mensaje) {
                this.mensaje = mensaje;
        }

   
    
public static void main(String[] arg) throws IOException {
    Scanner entrada = new Scanner(System.in);
    String Host = "localhost";
    int Puerto = 6000;// Puerto
    
    // Abrir canal de comunicaciones
    Socket Cliente = new Socket(Host, Puerto);
    
    //Creación de flujos de datos para mandar mensaje por argumentos.
       
                DataOutputStream flujo_salida = new DataOutputStream(Cliente.getOutputStream());
                client mio = new client();
                mio.setMensaje(escribir());
                flujo_salida.writeUTF(mio.getMensaje());
                flujo_salida.close();
                
                //Cerramos canal
                 Cliente.close();
  }

static public String escribir() {
                Scanner scan=new Scanner(System.in);
                String mensaje="";
                System.out.println("Comienza a escribir");
                System.out.println("Para finalizar escribe * y pulse ENTER");
               
                // Se ejecuta hasta que se introduce * 
                
                while (!mensaje.endsWith("*")) {
                        mensaje = mensaje + scan.nextLine();

                }
                // Si introducimos * devuelve todo menos el *
                if (mensaje.endsWith("*")) {
                        mensaje = mensaje.substring(0, (mensaje.length() - 1));
                }
                return mensaje;
        }

}
