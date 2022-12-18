package UA3.tareas.dev_6.tarea1;

import java.util.Scanner;
import java.net.*;

public class tarea1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        URL url;
        String dir = "";
        
        do{
            try {
                System.out.println("url:");
                dir = s.nextLine();
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
