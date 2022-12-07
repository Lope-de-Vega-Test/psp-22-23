/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea1;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author RafaelRomero
 */

public class client {
public static void main(String[] arg) throws IOException {
     String Host = "";
     int menu=0;
     URL url;
     String direccion="";
     
     Scanner entrada = new Scanner(System.in);
     
    do{
        System.out.println("------- MENÚ PRINCIPAL -------");
        System.out.println("0. Salir");
        System.out.println("1. Introducir IP");
        System.out.println("2. Introducir URL");
        menu=entrada.nextInt();
        entrada.nextLine();
        
        switch(menu){
            case 1:
        System.out.println("Dime la IP");
        Host=entrada.nextLine();
	int Puerto = 6000;// Puerto
        Socket Cliente = new Socket(Host, Puerto);
        InetAddress i = Cliente.getInetAddress();
        System.out.println("Puerto Local: " + Cliente.getLocalPort());
        System.out.println("Puerto Remoto: " + Cliente.getPort());
        System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
        System.out.println("Host Remoto: " + i.getHostName().toString());
        System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
        if(Host.equals("localhost")){
          Cliente.close(); 
           menu=0;  
        }
        
                
                break;
                
            case 2:
      try {

            InetAddress dir = null;
            
            System.out.println("Introduce la URL");
           
            //variable dónde se guarda la información del usuario
            direccion=entrada.nextLine();
            
            // Comprobamos si es una dirección o una IP
            if (isValid(direccion)) {
                url = new URL("http://" +direccion);
                Visualizar(url);
            } else {
                dir = InetAddress.getByName(direccion);
                pruebaMetodos(dir);
            }

      
        } catch (Exception e) {
            System.out.println(e);
        }
   
                
                break;
      
        }
        
   
    }while(menu!=0);
    
  }



private static void Visualizar(URL url) {
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): " + url.getDefaultPort());
        System.out.println("==================================================");
    }
   
   private static void pruebaMetodos(InetAddress dir) {
	      System.out.println("\tMetodo getByName():  " + dir);
		InetAddress dir2;
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}

		// USAMOS METODOS DE LA CLASE
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
		}//pruebaMetodos
    
    public static boolean isValid(String url) {
		/* Try creating a valid URL */
		try {
			new URL(url).toURI();
			return true;
		}

		// If there was an Exception
		// while creating URL object
		catch (Exception e) {
			return false;
		}
	}
}//fin
