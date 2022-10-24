package com.mycompany.tarea3;
import java.util.Scanner;
/**
 *
 * @author Lucía Luna
 */
public class Tarea3 {

     public static void main(String[] args) {
    
        Scanner sca1 = new Scanner(System.in);
        
       // Declaramos las variables 
       // Variables principales: 
        int datoNumerico;
        String datoCadena;
        // Variables secundarias: 
        
        int cantidad;
        int opcion;
        
        
        //Creamos un menu de opcion en el que podremos escoger si vamos a introducir un valor entero o una cadena
         System.out.println(" Elige: ");        
         System.out.println("1. Introducir numero");        
         System.out.println("2. Introducir una cadena");        
         opcion = sca1.nextInt();
         switch (opcion)
         {
             
         
             case 1: 
                 // Introducimos una cantidad de argumentos. En caso de que la cantidad sea menor a 1, devuelve un valor
                 System.out.println("Introduzca la cantidad de argumentos:  ");
                 cantidad = sca1.nextInt();
                 if (cantidad<1)
                 {
                     System.out.println("Valor devuelto: 1");
                 System.exit(0);
                 }
                 //Sino tenemos otras dos opciones viables
                 
                 else
                 {
                  for(int i = 0; i<cantidad;i++)
                 {
                 System.out.println("Introducir dato: ");
                 datoNumerico = sca1.nextInt();
                 
                 // Si el valor es menor que 0 devuelve 3
                 if (datoNumerico<0)
                 {
                  System.out.println("Valor devuelto: 3");
                 System.exit(0);
                 }
                 
                 // En caso contrario nos devolvera un 0
                 else
                 {
                  System.out.println("Valor devuelto: 0");
                  System.exit(0);
                 }
                 
                 }
                 }
                
                 break;
                 
                 
                 
             case 2:
                 /*En este caso tenemos que devolver un valor 2 cuando se introduzca
                 la cadena */
                 System.out.println("Introduzca una cadena de caracteres: ");
                 datoCadena = sca1.next();
              
                     System.out.println("Valor devuelto: 2");
                 System.exit(0);
                 
               
                
                 
             break;
             
             /*Cada vez que se introduce algo se sale del programa*/
         }
       
    }
}
