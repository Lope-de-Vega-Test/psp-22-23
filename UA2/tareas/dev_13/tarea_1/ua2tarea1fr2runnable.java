
import java.util.ArrayList;


class Contador{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public synchronized void incrementa() {
        c++;
        
    }

    public synchronized int valor() {
        return c;
    }
}

class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()    
    {
        elapsedTime();
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}

class Hilo implements Runnable 
{
    private Contador contador;

    public Hilo(String nombre, Contador c) {
        Thread.currentThread().setName(nombre);
        
        contador = c;
    }

    public void run() {
        
            for(int j=0; j<1000;j++)
            {
                contador.incrementa();
                
            }
            System.out.println(Thread.currentThread().getName() + " - contador vale " + contador.valor());
        
    }
}


public class ua2tarea1fr1 {
    
    public static void main(String[] args) {
        
        System.out.println("-------------------------------");
        System.out.println("Hilos: Tarea 1 con funcion incrementar sincronizada y usando runnable");
        System.out.println("-------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        
        ArrayList<Thread> Hilos = new ArrayList<Thread>(); 
        int numeroHilos = 5;
        
        Contador cont = new Contador(0);
        for(int i=0; i<numeroHilos; i++)
        {
            Hilo hilo = new Hilo("Hilo "+i,cont);
            Hilos.add(new Thread(hilo)); 
        }
        
        
        timer.start();
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        
        for(int i=0; i<Hilos.size(); i++)
        {
            Hilos.get(i).start();
        }
        
         for(int i=0; i<Hilos.size(); i++)
        {
            try
            {
                Hilos.get(i).join();
            }
            catch (InterruptedException e)
            {
                // Nothing to do here ...
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
        System.out.println("El resultado usando Runnable es el mismo a cuando importamos Threads.\n Los hilos se nos muestran en orden de finalización y el resultado es correcto");
        System.out.println("--------------------------------------");
        timer.stop();
        timer.printElapsedTime();
    }
    
}
