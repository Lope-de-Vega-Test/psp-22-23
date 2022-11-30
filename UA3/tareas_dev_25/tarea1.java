/*
Crea un programa en Java que admita desde la línea de comandos una URL y 
visualice información sobre esta. Modifica el programa para que admita 
continuamente nuevas IP o URL y muestre la información hasta que el usuario 
inserte "localhost".
 */

package pkg1.dani.socket;
import java.net.*;
import java.util.Scanner;

public class tarea1 {

    public static void main(String[] args) {
        URL url;
        String direccion="";
        Scanner entrada = new Scanner(System.in);
         do {
 try {
       

            InetAddress dir = null;
            System.out.println("========================================================");
            System.out.println("SALIDA PARA LOCALHOST: ");
            System.out.println("Introducir URL o IP");
           
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
 
   } while (!direccion.equals("localhost"));
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
