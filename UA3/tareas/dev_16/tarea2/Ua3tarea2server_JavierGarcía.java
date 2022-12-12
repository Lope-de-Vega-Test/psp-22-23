package ua3tarea2server_javiergarcía;

import java.io.*;
import java.net.*;


public class Ua3tarea2server_JavierGarcía {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        int Puerto = 7000;// Puerto
	ServerSocket Servidor = new ServerSocket(Puerto);
        System.out.println("Escuchando en " + Servidor.getLocalPort());

        Socket cliente1 = Servidor.accept();
        // Realizar Acciones con cliente 1
            System.out.println("Entra Cliente 1. ");
            System.out.println("Cliente 1 terminado.");
            
            System.out.println("-----------------------");

        Socket cliente2 = Servidor.accept();
        // Realizar Acciones con cliente 2
            System.out.println("Entra Cliente 2.");
            System.out.println("Cliente 2 terminado.");

        Servidor.close();
    }
    
}
