package paquete;
import java.util.Scanner;
import java.lang.*;




public class ua1tarea3 {

    public static void main(String[] args)  {
            
        if (args.length < 1) // Nos devuelve el valor 1 si no hay argumentos
        {
        
            
            System.out.println("No hay argumentos, valor: 1");
        
        }
                  else             // Nos devuelve un 2 si no es un int
        {          
            
          for (int i=0; i<args.length;i++)
            {
              if (ComprobarNumero(args[i]) == false)
            {
            
                System.out.println("El argumento es "+args[i]+" valor devuelto: 2");
                
            }
            
            if (ComprobarNumero(args[i]) == true) // Nos devolvera un numero si es un int
            {

            
                int argInt;
                argInt = Integer.parseInt(args[i]);
                
                if (argInt < 0)
                {
                System.out.println("Argumento: "+argInt+", valor: 3");
                }
                if (argInt > 0)
                {
                System.out.println("Argumento: "+argInt+", valor: 0");
                }
            }
            
            }
        
        }
                      
            
                }
           
             
    public static boolean ComprobarNumero(String cadena) // Funcion en cargada de verificar todo
    {  
        boolean dev;
    try
    {
    Integer.parseInt(cadena);
    dev = true;
    }catch(NumberFormatException excepcion)
    {
    dev = false;
    }
    return dev;  
    }}