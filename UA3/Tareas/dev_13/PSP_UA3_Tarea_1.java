import java.net.*;
import java.util.Scanner;

public class PSP_UA3_Tarea_1
{
	public static void main(String[] args){
                //Escaner para pedir por pantalla la url
                Scanner sca = new Scanner(System.in);
                //URL con la que trabajará el programa
		URL url = null;
                System.out.println("Introduzca URL's. El programa parará cuando se introduzca 'localhost'");
                //Variable que contendrá la URL para su comprobación
                String input="";
                do{
                    try{
                        input = sca.nextLine();
			//Montamos la dirección que nos da en la usuario en la URL
			url = new URL(input);
                        //Mostramos por pantalla la información de la URL
			Visualizar(url);
		}
		catch (MalformedURLException e) {System.out.println(e);}
                //Se repetira el proceso mientras no se introduzca "localhost"
                }while(!input.equals("localhost"));
		
	}

        //Método para mostrar por pantalla la información de la url 
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
}
