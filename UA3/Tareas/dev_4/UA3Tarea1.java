import java.net.*;
import java.util.Scanner;


// El siguente ejercicio permite introducir tantas URLs como el usuarios quiera devolviendo los datos del mismo, hasta que se introduzca localhost
// El metodo Visualizar() muestra informacion de la URL usando los metodos de la clase URL.

public class Tarea1UA3
{
	public static void main(String[] args) {
            Scanner sca= new Scanner(System.in);
            String nombre;
		URL url;
		try
		{
                    do
                    {
			System.out.println("INTRODUCE UNA URL");
                        nombre= sca.nextLine();
                        if(nombre.equals("localhost"))
                        {
                            System.out.println("No se puede introducir localhost");
                            System.exit(0);
                        }else
                        {
                            url = new URL(nombre);
			    Visualizar(url);
                        }                
		}while(nombre!="localhost");
                }catch (MalformedURLException e) {	System.out.println(e);}
	}// Clase Main 

	private static void Visualizar(URL url)
	{
		System.out.println("\tURL completa: " + url.toString());
		System.out.println("\tgetProtocol(): " + url.getProtocol());
		System.out.println("\tgetHost(): " + url.getHost());
		System.out.println("\tgetPort(): " + url.getPort());
		System.out.println("\tgetFile(): " + url.getFile());
		System.out.println("\tgetUserInfo(): " + url.getUserInfo());
		System.out.println("\tgetPath(): " + url.getPath());
		System.out.println("\tgetAuthority(): " + url.getAuthority());
		System.out.println("\tgetQuery(): " + url.getQuery());
		System.out.println("\tgetDefaultPort(): "+ url.getDefaultPort());
		System.out.println("==================================================");
	}
}// Metodo Visualizar
