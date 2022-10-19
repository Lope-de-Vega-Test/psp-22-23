
package tarea.pkg2.pkg1;
import java.util.Scanner;

public class Tarea21 {

    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca caracteres hasta un asterisco");
        
        String caracter = "";
        String linea = "";
        
        //lee un caracter hasta que el caracter introducido se "*"
        while(!caracter.equals("*")){
            linea += caracter;
            caracter = entrada.next();
        }
        //muestra por pantalla los caracteres introducidos
        System.out.println("Has introducido: "+linea);
    }
    
}
