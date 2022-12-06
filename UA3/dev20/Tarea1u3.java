package tarea1u3;
import java.net.*; 
import java.util.Scanner; 

/**
 *
 * @author Lucía Luna
 */

public class Tarea1u3 {
    public static void main(String args[]) throws UnknownHostException 
    {
      Scanner scan = new Scanner(System.in);
        InetAddress dir = null;
        String direccion;
        do
        {
            System.out.println("Introduce la direccion: ");
            direccion = scan.next();
               try{
                    dir =  InetAddress.getByName(direccion);
                    pruebaMetodos(dir);
        
                System.out.println("Direcciones IP para: "+dir.getHostName());
                InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
                for (int i=0; i<direcciones.length;i++)
                {
        
                    System.out.println("\t\t"+direcciones[i].toString());
                    System.out.println("\t");
                }
            }catch(UnknownHostException e1){e1.printStackTrace();}
  
              URL url;
                 try{
                    System.out.println("Constructor simple para oracle");
                    url = new URL("https://"+direccion);
                    visualizar(url);
                  }catch(MalformedURLException e){System.out.println(e);}
        }while(direccion=="localhost");
       
  }

   private static void visualizar (URL url)
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


private static void pruebaMetodos(InetAddress dir){
    System.out.println("\tMetodo getByname(): " + dir);
    InetAddress dir2;
    try
    {

        dir2 = InetAddress.getLocalHost();
        System.out.println("\t Metodo getLocalhost(): "+ dir2);

    }catch (UnknownHostException e){

        e.printStackTrace();
        
    }
System.out.println("\t Metodo getHostName(): "+dir.getHostName());
    System.out.println("\t Metodo getHostAddress: "+dir.getHostAddress());
    System.out.println("\t Metodo toString(): "+dir.toString());
    System.out.println("\t Metodo getCanonicalHostname(): "+dir.getCanonicalHostName());
}

}
