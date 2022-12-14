
import java.net.*;
import java.util.Scanner;

public class ua3tarea1 {
    
    public static void main(String[] args) {
        InetAddress dir = null;

        // Con paso de argumento
        // Si tiene argumentos
        if (args.length == 1) {
            // Si el argumento pasado no es localhost o 127.0.0.1
            if (!args[0].contains("localhost") && !args[0].contains("127.0.0.1")) {
                try {
                    dir = InetAddress.getByName(args[0]);
                    pruebaMetodos(dir);//

                    System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
                    InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
                    for(int i = 0; i < direcciones.length; i++) {
                        System.out.println("\t\t" + direcciones[i].toString());
                    }

                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            } else {
                // Si el argumento es localhost o 127.0.0.1
                System.out.println("We don't analyze localhost!");
            }
        } else {
            // Si no hay argumentos o si hay mas de uno
            Scanner sc = new Scanner(System.in);
            String input_url = "";
            
            // Bucle: Solicita e imprime la informacion de la URL o direccion IP
            // Se termina el bucle cuando se introduce localhost o 127.0.0.1 y se imprime su informacion
            do {
                System.out.print("URL o direccion IP: ");
                input_url = sc.nextLine();
                System.out.println();

                try {
                    dir = InetAddress.getByName(input_url);
                    pruebaMetodos(dir);

                    
                    System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
                    // Array de tipo InetAddress con todas las direcciones IP asignadas a la direccion
                    InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
                    for(int i = 0; i < direcciones.length; i++) {
                        System.out.println("\t\t" + direcciones[i].toString());
                    }
                    System.out.println();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
            } while (!input_url.contains("localhost") && !input_url.contains("127.0.0.1"));

        }
    }

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName():  " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS METODOS DE LA CLASE
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }

}
