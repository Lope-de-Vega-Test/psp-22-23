import java.util.Scanner;

public class Tarea2 {
    public static void main(String[] args) {
        // usamos la clase Scanner para recibir datos
        Scanner entrada = new Scanner(System.in);
        String cadena;
        //lo guardamos en una variable String
        cadena = entrada.nextLine();
        //creamos un array tipo String, y metemos dentro la variable con nuestra
        //frase, seguido de .split, que nos permite seleccionar un delimitador
        //para divir nuestro string en varias partes.
        String[] parts = cadena.split("[*]");
        //guardamos el antes y el despues en una variable String
        String part1 = parts[0];//antes de *
        //Las mostramos
        System.out.println("Antes del * : "+part1);
    }
}
