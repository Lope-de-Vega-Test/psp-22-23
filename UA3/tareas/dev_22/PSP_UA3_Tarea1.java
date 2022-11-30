/**
 * @mainpage                    Tarea 1 UA3
 *                                          
 */
package ejemploconexion;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EjemploConexion {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String dir;/**< String para construir URL, también me interesa el método equals*/
        URL url;/**< Esta URL es la que usaré para mostrar sus datos*/
        
            do {
                System.out.println("Introduce URL");
                dir = entrada.nextLine();

                /**
                *@note           solo nos interesa construir una URL si el string no es localhost.
                */
                if (dir.equals("localhost")==false) {
                    /**
                    *@attention        Debemos construir la URL en un try-catch para controlar posibles errores
                    *
                    */
                    try {
                        url = new URL(dir);
                        Visualizar(url);
                    } catch (MalformedURLException e) {
                        System.out.println(e);
                    }    
                    
                } else {
                    System.out.println("Introducido localhost, saliendo...");
                }

            /**
            *@note           este do-while hará que se ejecute todo hasta introducir el localhost 
            */
            } while (dir.equals("localhost") == false);      
    }

    /**
    *@brief              URL data visualizer
    *
    *                    Recibe una URL y muestra en varios println
    *                    diferentes aspectos de la misma
    *
    *@param              URL url
    *
    */
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
}
