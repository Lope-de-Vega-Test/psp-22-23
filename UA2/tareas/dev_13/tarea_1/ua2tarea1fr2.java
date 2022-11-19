

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

class Hilo extends Thread 
{
    private Contador contador;

    public Hilo(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        
            for(int j=0; j<1000;j++)
            {
                contador.incrementa();
                
            }
            System.out.println(getName() + " - contador vale " + contador.valor());
        
    }
}


public class ua2tarea1fr2 {

    
    public static void main(String[] args) {
        
        System.out.println("-------------------------------");
        System.out.println("Hilos: Tarea 1 con funcion incrementar sincronizada");
        System.out.println("-------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        Contador cont = new Contador(0);
        Hilo hilo1 = new Hilo("Hilo 1", cont);
        Hilo hilo2 = new Hilo("Hilo 2", cont);
        Hilo hilo3 = new Hilo("Hilo 3", cont);
        Hilo hilo4 = new Hilo("Hilo 4", cont);
        Hilo hilo5 = new Hilo("Hilo 5", cont);

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
        System.out.println("En este caso el resultado está garantizado\n pero los hilos se ejecutan a destiempo.\Esto es correcto");
        System.out.println("--------------------------------------");
        timer.stop();
        timer.printElapsedTime();
    }
    
}
