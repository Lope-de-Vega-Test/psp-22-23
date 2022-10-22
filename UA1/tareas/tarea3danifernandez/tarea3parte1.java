package com.mycompany.tarea3parte1;
import java.util.Scanner;
/**
 *
 * @author daniel fernandez
 */



public class Tarea3parte1 {

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
         System.out.println("Escoja una opcion: ");        
         System.out.println("1. Introducir dato numerico");        
         System.out.println("2. Introducir dato de tipo Cadena");        
         opcion = sca1.nextInt();
         switch (opcion)
         {
             /*En el caso 1 estaremos tratando la variable numerica en la que
             tendremos varias opciones a usar*/
         
             case 1: 
                 // Primera opcion, introducimos una cantidad de argumentos, si esa cantidad es menor a 1, nos devuelve un valor
                 System.out.println("Introduzca la cantidad de argumentos:  ");
                 cantidad = sca1.nextInt();
                 if (cantidad < 1)
                 {
                     System.out.println("Valor devuelto: 1");
                 System.exit(0);
                 }
                 // En caso contrario tendremos otras dos opciones viables
                 
                 else
                 {
                  for(int i = 0; i<cantidad;i++)
                 {
                 System.out.println("Introducir dato: ");
                 datoNumerico = sca1.nextInt();
                 
                 // Si el valor es menor que 0 nos va a devolver un 3
                 if (datoNumerico < 0)
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
                 
                 /*El caso 2 es muy simple, solamente debemos devolver un valor 2 cuando introduzcamos
                 la cadena de caracteres*/
             case 2:
                 
                 System.out.println("Introduzca una cadena de caracteres: ");
                 datoCadena = sca1.next();
              
                     System.out.println("Valor devuelto: 2");
                 System.exit(0);
                 
               
                
                 
             break;
             
             /*POR CADA DATO INTRODUCIDO NOS VA A SACAR DEL PROGRAMA*/
         }
       
    }
}
