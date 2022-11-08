import java.io.*;
import java.util.Scanner;

public class App {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        // Creamos un contador que vaya sumando el tamaño del vector en el bucle
        int cantidad;
        cantidad = 1;
        // Creamos el vector de string para que vaya almacenando los datos que vamos
        // introduciendo
        // Establezco el tamaño del vector a 100
        String cadena[] = new String[100];
        // Creamos un bucle for que vaya pidiendo por terminal las frases
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Escriba una cadena: ");
            cadena[i] = scan.nextLine();
            // En el caso de que no contenga un *, se sumara el contador y volvera a pedir
            // otro string
            if (!cadena[i].contains("*")) {
                cantidad++;
            }
            // En el caso de que contenga un * igualamos i al tamño del vector y
            // terminaremos el bucle
            if (cadena[i].contains("*")) {
                i = cantidad;
            }

        }

        System.out.println(" ");
        System.out.println("Resultado: ");

        // Ahora mostramos los datos introducidos, recorriendo el tamaño del bucle para
        // que no aparezcan datos null
        for (int j = 0; j < cantidad; j++) {
            if (!cadena[j].contains("*")) {
                System.out.println(cadena[j]);

            }

        }

    }
}
