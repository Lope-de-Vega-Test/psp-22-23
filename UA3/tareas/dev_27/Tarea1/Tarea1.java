import java.util.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Tarea1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        URL url;
        InetAddress dir = null;
        String direccion;
        // con un try, pedimos entrada de texto hasta que se introduzca localhost
        try {
            do {

                System.out.println("Escribe una URL o IP");
                direccion = entrada.nextLine();

                if (isValid(direccion)) {
                    url = new URL("http://" + direccion);
                    Visualizar(url);
                } else {
                    dir = InetAddress.getByName(direccion);
                    pruebaMetodos(dir);

                }
            } while (!direccion.equals("localhost"));
        //control de errores de url
        } catch (MalformedURLException e) {
            System.out.println(e);
        //control de errores de ip
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }

    //Funcion para mostrar la informacion de la URL
    private static void Visualizar(URL url) {
        System.out.println("\tURL: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): " + url.getDefaultPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("==============================");
    }
    //funcion para comprobar la url
    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

    //Funcion para mostrar la informacion de la IP
    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName():  " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
            //control de errores
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
        System.out.println("==============================");
    }
}