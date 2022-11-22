import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;



//La clase reader que leera el argumento y tambien contara la cantidad de caracteres que el fichero contengan.
  
class Reader extends Thread
{
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

    

//La clase ExecutionTimer donde se alojará los tiempos en los que el programa tardará en recorrer usando hilos y sin hilos.

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


//La clase main compuesta para recorrer el numero de argumentos que le pasemos para ejecutar el programa.
    
public class Tarea3UA2 {
 public static void main(String[] args) {   
     
        ExecutionTimer timerH= new ExecutionTimer();
        ExecutionTimer timerSinH= new ExecutionTimer();
        int numeroArgumentos=args.length;
        
        //Ejecutaremos para que los lea usando los hilos de la clase Reader.
        
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
                //Nada aqui
            }
            
        }
        timerH.stop();
        
        //Lo mismo pero sin la ejecucion de hilos.
        
        System.out.println("-----Ejecucion sin hilos-----");
        timerSinH.start();
        for(int i=0; i<numeroArgumentos; i++)
        {
            reader(args[i]);
        }
        timerSinH.stop();
        
        System.out.println("El tiempo transcurrido de ejecucion con hilos es: "+(timerH.elapsedTime()/1000000)+", y el tiempo de ejecucion de sin hilos es: "+timerSinH.elapsedTime()/1000000);
    }
 
 
    //Una misma clase reader pero para que pueda leer los caracteres sin tener que usar los hilos.
 
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
