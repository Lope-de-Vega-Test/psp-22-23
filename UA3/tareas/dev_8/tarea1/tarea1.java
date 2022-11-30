package UA3.tareas.dev_8.tarea1;

import java.util.Scanner;
import java.net.*;

/**
 * Crea un programa en Java que admita desde la línea de comandos una URL y
 * visualice información sobre esta. Modifica el programa para que
 * admita continuamente nuevas IP o URL y muestre la información hasta que el
 * usuario inserte "localhost".
 */

public class tarea1 {

    /**
     * Ejecución del programa tarea1 de UA3
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String url;

        try {

            do {
                System.out.println("Tarea 1 de UA3");
                System.out.println("-------------");
                System.out.println("Introduce una URL: ");
                url = sc.nextLine();

                URL parseURL = new URL(url);
                visualizar(parseURL);

            } while (!url.equals("localhost"));

        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Este método imprime por pantalla la información de la url
     * @param url
     */
    private static void visualizar(URL url) {
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
