class Cola
{
    private int cerveza = 0;  // bolitas a Producir por los Hilos Productores y a Consumir por los Hilos Consumidores
    private boolean disponible = false; // Inicilmente la cola debe estar vacía
        
    public synchronized int get() {
        while(!disponible) {        // Si hay bolitas ...
            try {
                wait();             // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}            
        }
        int cerveza_tmp = 0;
        if(cerveza==3)
        {
            cerveza_tmp = cerveza;
            cerveza = 0;
        }
        System.out.println( "Se consume: " + cerveza_tmp);
        disponible = false; // Se pone la cola vacía ...
        notify();           // El objeto sincronizado queda libre
        return cerveza_tmp;       // Se devuelven los bolitas
    }

    public synchronized int getCervezas() {
              // El objeto sincronizado queda libre
        return cerveza;       // Se devuelven los bolitas
    }

    public synchronized void put(int valor) {
        while(disponible) {        // Si hay bolitas ...
            try {
                wait();         // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}        
        }
        cerveza++; // = valor;          // Coloca valor en la cola
        disponible = true;      // Disponible para consumir
        //System.out.println("Se produce: " + bolitas);
        notify();           // El objeto sincronizado queda libre
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



class ClaseHilosCliente extends Thread 
{
    private Cola cola;
    private int n;

    public ClaseHilosCliente(Cola c, int n) {
        cola = c;
        this.n = n;
    }

    public void run() { 
        int valor = 0;       
        for(int i=0; i<10;i++)
        {
            valor = cola.get(); // Pongo bolitas
            System.out.println(i + " => Consumidor : "  + n + " consume: " + valor);

            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
} // Fin Class Productor


public class ua2ex1 {
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        System.out.println("Hilos: Productor - Consumidor Final");
        System.out.println("-----------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        Cola cola = new Cola();
        
        ClaseHilosCliente c = new ClaseHilosCliente(cola, 1);        

        timer.start();
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        p1.start();        
        p2.start();        
        c.start();        

        try {
            
            c.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        
        timer.stop();
        timer.printElapsedTime();
    }
}