
import java.util.Scanner;

/**
 *
 * @author Irene Alba Posadas
 */
public class Tarea2 {

    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //cadena en la que se registra lo introducido por el usuario
        String cadena = "";
        
        //variable para salir del while de escribir 
        boolean salir = false;
        
        System.out.println("Introduce la frase deseada, dejará de escribir cuando se detecte *.");
        
        //mientras salir sea false se añadirá nuevo texto a la variable cadena hasta encontrar * en la cadena, entonces salir será true
        while(salir == false){
           
            cadena += scan.nextLine();
            if(cadena.contains("*")){
                salir = true;
            }
        };
        
        //mostrará carácter por carácter hasta llegar a un *
        for(int i = 0; i < cadena.lastIndexOf("*"); i++){
            System.out.print(cadena.charAt(i));
        }
        System.out.println("");
        
    }
    
}
