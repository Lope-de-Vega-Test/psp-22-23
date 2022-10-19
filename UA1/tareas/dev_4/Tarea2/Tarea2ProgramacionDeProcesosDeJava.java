
package tarea2.programacion.de.procesos.de.java;

import java.util.Scanner;

public class Tarea2ProgramacionDeProcesosDeJava {

    
    
    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        
        String Palabra= " ";
        
        boolean numLetra= false;
        
        System.out.println("Indrouce una palabra cualquiera: ");
        
        
        
        while(numLetra==false)
        {
            Palabra += entrada.nextLine();
            if(Palabra.contains("*"))
            {
                System.out.println("Es palabra contiene un *");
                numLetra=false;
            }
            break;
        }
        
        
        
        
        for(int i=0; i<Palabra.lastIndexOf("*"); i++)
            {
                System.out.print(Palabra.charAt(i));
            }
    }
    
}
