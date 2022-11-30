//Importamos las clases necesarias
import java.net.*;
import java.util.Scanner;

public class Ua3tarea1 {

    public static void main(String[] args) {

        //Creamos un scanner para poder leer la URL que le pidamos al usuario
        Scanner sca = new Scanner(System.in);
        String buscar = "";

        do {

            //Pedimos URL
            System.out.println("Bienvenido, introduzca la URL");
            buscar = sca.next();

            //Creamos variable de tipo InetAdress
            InetAddress dir = null;

            System.out.println("========================================================");

            try {

                System.out.println("ESTAMOS BUSCANDO: " + buscar);
                System.out.println("");
                //Asignamos a la variable "dir" la variable que se le ha pedido al usuario
                dir = InetAddress.getByName("" + buscar);
                //Se llama a la funcion pruebaMetodos
                pruebaMetodos(dir);

                // Array de tipo InetAddress con todas las direcciones IP
                System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
                InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());

                for (int i = 0; i < direcciones.length; i++)
                    System.out.println("\t\t" + direcciones[i].toString());

                System.out.println("========================================================");

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }
            System.out.println("");

        } while (buscar.equals("localhost") == false);

    }

    private static void pruebaMetodos(InetAddress dir) {

        System.out.println("\tMetodo getByName():  " + dir);
        InetAddress dir2;

        //
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //Uso de metodos de la clase para la obtencion de los valores necesarios
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
