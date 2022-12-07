package paquete;

import java.util.Scanner;
import java.net.*;

public class ua3fr1{ 
    public static void main(String args[]) throws UnknownHostException{

        Scanner scan = new Scanner(System.in);  //scan para introducir manualmente la url
        InetAddress direccion = null;
        String urlintroducida;
        do{
            System.out.println("Teclee la url: ");
            urlintroducida = scan.next();

            try{  //control de errores que gestiona la salida de las direcciones
                direccion =  InetAddress.getByName(urlintroducida);
                Metodos(direccion);      
                System.out.println("Direcciones IP para: "+direccion.getHostName());
                InetAddress[] direcciones = InetAddress.getAllByName(direccion.getHostName());
                for (int i=0; i<direcciones.length;i++)
                {
                    System.out.println("\t\t"+direcciones[i].toString());
                    System.out.println("\t");
                }
            }
            catch(UnknownHostException e1){e1.printStackTrace();
            }
       
            URL url;
    
            try{  //trycatch  para ir a la url introducida
    
                System.out.println("Constructor Oracle");
                url = new URL("https://"+urlintroducida);
                visualizarurl(url);
    
            }
            catch(MalformedURLException e){System.out.println(e);
            }

        }
        while(urlintroducida=="localhost");
       
    }



private static void visualizarurl (URL url) //funcion encargada de visualizar la url introducida
{
    System.out.println("\tURL completa: "+url.toString());
    System.out.println("\tgetProtocol(): "+url.getProtocol());
    System.out.println("\tgetHost(): "+url.getHost());
    System.out.println("\tgetPort(): "+url.getPort());
    System.out.println("\tgetFile(): "+url.getFile());
    System.out.println("\tgetUserInfo(): "+url.getUserInfo());
    System.out.println("\tgetPath(): "+url.getPath());
    System.out.println("\tgetAuthority(): "+url.getAuthority());
    System.out.println("\tgetQuery(): "+url.getQuery());
    System.out.println("\tgetDefaultPort(): "+url.getDefaultPort());
    System.out.println("\t");
}  



private static void Metodos(InetAddress direccion){ //funcion para obtener los datos de red de la direccion introducida

    System.out.println("\tMetodo getByname(): " + direccion);
    InetAddress direccion2;
    try
    {
        direccion2 = InetAddress.getLocalHost();
        System.out.println("\t Metodo getLocalhost(): "+ direccion2);
    }
    catch (UnknownHostException e){
        e.printStackTrace();
    }
    System.out.println("\t Metodo getHostName(): "+direccion.getHostName());
    System.out.println("\t Metodo getHostAddress: "+direccion.getHostAddress());
    System.out.println("\t Metodo toString(): "+direccion.toString());
    System.out.println("\t Metodo getCanonicalHostname(): "+direccion.getCanonicalHostName());
}

}