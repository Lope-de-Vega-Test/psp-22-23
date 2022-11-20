package u2tareafr2.java;

/**
 *
 * @author Lucía Luna
 */
class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public synchronized void incrementa() {
        c++;
    }

    

    public int valor() {
        return c;
    }
} // Final Class Contador

  

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



class hilo extends Thread 
{
    private Contador contador;

    public hilo(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

   
    public void run() {
        
        //synchronized(contador)
        {
        
          for(int j=0; j<1000;j++) // TODO: Cambia a 3000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();
         
        }
        
        }
      
        System.out.println(getName() + " - contador vale " + contador.valor());
    }

   
 // Final Class HiloSumador

public class ua2tarea1fr2 {

     public static void main(String[] args) {
        
        System.out.println("-------------------------------");
        System.out.println("Hilos: Tarea 1 con funcion incrementar sincronizada");
        System.out.println("-------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        Contador cont = new Contador(0);
        hilo hilo1 = new hilo("Hilo 1", cont);
        hilo hilo2 = new hilo("Hilo 2", cont);
        hilo hilo3 = new hilo("Hilo 3", cont);
        hilo hilo4 = new hilo("Hilo 4", cont);
        hilo hilo5 = new hilo("Hilo 5", cont);

        timer.start();
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
        
        timer.stop();
        timer.printElapsedTime();
    }
}
}
