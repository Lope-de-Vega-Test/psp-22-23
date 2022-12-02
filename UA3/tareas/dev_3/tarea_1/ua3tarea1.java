import java.net.*;
import java.util.Scanner;

// En el siguiente ejercicio definimos un objeto InetAddress de nombre dir, que usaremos para guardar la dirección IP o URL introducida
// Después llamamos al metodo pruebaMetodos() pasándole el objeto creado. Aquí mostraremos los métodos de la clase InetAddress.
// Por ultimo utilizamos el metodo getAllByName() para ver todas las direcciones IP asignadas a la máquina representada por la URL o dirección IP
// Incluiremos todo el proceso en un try-catch para el control de errores.

public class PSP_UA3_Ejemplo_1_TestInetAddress {

	  public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

	   InetAddress dir = null;//Objeto InetAddress sobre el que haremos los métodos para mostrar la información.
       String direccionIntroducida = "";//Variable para recoger la URL/dirección IP.

       do{
            System.out.println();
            System.out.println("************************************************");
            System.out.println("INTRODUCE UNA URL O DIRECCION IP");
            System.out.println("************************************************");
            System.out.println();

            System.out.println("____________________________________________");
            System.out.print("URL/Direccion IP: ");
            direccionIntroducida = entrada.nextLine();//Recogemos la direccion o URL.
            System.out.println("____________________________________________");
            System.out.println();
            
            //Si la direccion es "localhost" la obviaremos y no mostramos sus datos
            if(direccionIntroducida.equals("localhost") == false && direccionIntroducida.equals("") == false){
                try {

                    System.out.println("========================================================");
                    System.out.println("SALIDA PARA UNA URL:");
                    dir = InetAddress.getByName(direccionIntroducida);
                    pruebaMetodos(dir);//Llamamos a la función especificada anteriormente pasándole como
                    //parámetro el objeto de tipo InetAddress.

                    // Array de tipo InetAddress con todas las direcciones IP
                    //asignadas a la URL/dirección.
                    System.out.println("\tDirecciones IP para " + dir.getHostName() + ":");
                    InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());

                    for (int i = 0; i < direcciones.length; i++){
                        System.out.println(" \t  -  " + direcciones[i].toString());//mostramos todas las direcciones IP.
                    }

                    System.out.println("========================================================");

                }
                //Control de errores.
                catch (UnknownHostException e1){
                    e1.printStackTrace();
                }
            }
            else if (direccionIntroducida.equals("")){
                System.out.println();
                System.out.println("-----------------------");
                System.out.println("La dirección está vacía");
                System.out.println("-----------------------");
                System.out.println();
            }
       }while(direccionIntroducida.equals("localhost") == false);//Si la URL introducida es "localhost", salimos del bucle

       System.out.println("******************************************");
       System.out.println();
       System.out.println("SALIENDO DEL PROGRAMA");
       System.out.println();
       System.out.println("******************************************");

	}// main

	private static void pruebaMetodos(InetAddress dir) {
	      System.out.println("\tMetodo getByName():  " + dir);//mostramos el nombre de la dirección
		InetAddress dir2;
		try {//Aquí mostramos el localhost del equipo local
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		}
        //Control de errores.
        catch (UnknownHostException e){
            e.printStackTrace();
        }

		// USAMOS METODOS DE LA CLASE
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
		}//pruebaMetodos
}//fin
