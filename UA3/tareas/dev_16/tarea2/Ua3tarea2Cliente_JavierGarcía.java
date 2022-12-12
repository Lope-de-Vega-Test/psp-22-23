

import java.io.*;
import java.net.*;

public class Ua3tarea2Cliente_JavierGarcía {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        String Host = "localhost";
        int Puerto = 7000;// Puerto
        
    Socket Cliente1 = new Socket(Host, Puerto);
    InetAddress i = Cliente1.getInetAddress();
    System.out.println("Puerto Local: " + Cliente1.getLocalPort());
    System.out.println("Puerto Remoto: " + Cliente1.getPort());
    System.out.println("Nombre Host/IP: " + Cliente1.getInetAddress());
    System.out.println("Host Remoto: " + i.getHostName().toString());
    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
    
    Cliente1.close();   
    }
    
}
