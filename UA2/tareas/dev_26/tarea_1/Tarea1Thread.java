class Contador
{
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
} // Fin Class Contador

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
} // Fin Class ExecutionTimer

class HiloSumador extends Thread 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
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
} // Fin Class HiloSumador

public class Tarea1Thread {
    public static void main(String[] args) {
        System.out.println("-----------------------------");
        System.out.println("Hilos: Extensión Clase Thread");
        System.out.println("-----------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        Contador cont = new Contador(0);
        HiloSumador hiloSuma[] = new HiloSumador[5];
        for(int i = 0; i < 5; i++){
            hiloSuma[i] = new HiloSumador("Hilo Sumador "+(i+1), cont);
        }
        
        
        timer.start();
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        for(int i = 0; i < 5; i++){
            hiloSuma[i].start();
        }
        

        try {
            hiloSuma[0].join();
            hiloSuma[1].join();
            hiloSuma[2].join();
            hiloSuma[3].join();
            hiloSuma[4].join();
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