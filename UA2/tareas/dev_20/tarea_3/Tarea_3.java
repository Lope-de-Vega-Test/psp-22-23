package tarea_3;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 *
 * @author Lucía Luna
 */

class Reader extends Thread{  
    //Creamos la clase READER para leer el argumento y para contar la cantidad de caracteres
    private String fichero;
    
    public Reader(String fichero)
    {
        this.fichero=fichero;
        setName(fichero);
    }

    public void run()
      {      
        int numeroCaracteres=0;
        
        FileReader fr= null;
        
        try{
            fr= new FileReader(fichero);
            int caracter=fr.read();
            
            while(caracter!=-1)
            {
                numeroCaracteres++;
                caracter=fr.read();
            }
        }catch(FileNotFoundException e)
        {
            System.out.println("Error al leer el fichero");
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el fichero");
            System.out.println(e.getMessage());
        }
        finally{
            try
            {
                if(fr!=null)
                {
                    fr.close();     
                    System.out.println(" Total de caracteres de los textos que se han introducido: "+numeroCaracteres);
                    
                }
            }catch(Exception e)
            {
                System.out.println("Fichero cerrado");
                System.out.println(e.getMessage());
            }
        }
 }
}

    

//Class ExecutionTimer.En esta clase se almacena el tiempo que tarda el programa en ejecutar

class ExecutionTimer
{
    private long startTime=0;
    private long endTime=0;
    private long timeElapsed;
    
    public void start()
    {
        startTime=System.nanoTime();
    }
    
    public void stop()
    {
        endTime=System.nanoTime();
    }
    
    public long elapsedTime()
    {
       return timeElapsed=endTime-startTime;
    } 
}


//Class main se encarga de recorrer el numero de argumentos para finalmente ejecutar el programa 
public class  Tarea_3 {
 public static void main(String[] args) {   
     
        ExecutionTimer timerH= new ExecutionTimer();
        ExecutionTimer timerSinH= new ExecutionTimer();
        int numeroArgumentos=args.length;
        
        //Usamos la clase READER para leer los hilos al ejecutar
        
        System.out.println("--------Ejecucion de hilos--------");
        timerH.start();
        
        for(int i=0; i<numeroArgumentos; i++)
        {
            Reader hilo= new Reader(args[i]);
            
            
            hilo.start();
            
            try{
                hilo.join();
            }catch(InterruptedException e)
            {
                
            }
            
        }
        timerH.stop();
        
        //Hacemos lo mismo que en paso anterior pero esta vez sin ejecutar hilos
        
        System.out.println("-----Ejecucion sin hilos-----");
        timerSinH.start();
        for(int i=0; i<numeroArgumentos; i++)
        {
            reader(args[i]);
        }
        timerSinH.stop();
        
        System.out.println("El tiempo transcurrido de ejecucion con hilos es: "+(timerH.elapsedTime()/1000000)+", y el tiempo de ejecucion sin hilos es: "+timerSinH.elapsedTime()/1000000);
    }
 
 
    //Class Reader para leer los caracteres sin usar los hilos
 
    public static void reader(String fichero)
 {      
        int numeroCaracteres=0;
        
        FileReader fr= null;
        
        try{
            fr= new FileReader(fichero);
            int caracter=fr.read();
            
            while(caracter!=-1)
            {
                numeroCaracteres++;
                caracter=fr.read();
            }
        }catch(FileNotFoundException e)
        {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally{
            try
            {
                if(fr!=null)
                {
                    fr.close();
                    System.out.println("Numero de caracteres de los textos introducidos: "+numeroCaracteres);
                    
                }
            }catch(Exception e)
            {
                System.out.println("Cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
 }
} 
