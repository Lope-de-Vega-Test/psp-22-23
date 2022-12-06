/**
 *
 * @author Irene Alba
 */

import java.net.*;
import java.util.Scanner;

public class tarea1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        URL url = null;
        String dir = "";
        
        do{
            try {
                System.out.println("Introduce una url:");
                dir = scan.nextLine();
                url = new URL(dir);
                visualizarURL(url);

            } catch (MalformedURLException e) {
                System.out.println(e);
            } 
        }while(!dir.equals("http://localhost"));
        
        
    }
    
    private static void visualizarURL(URL url){
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): "  + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): " + url.getDefaultPort());
        System.out.println("================================================");
    }
}
