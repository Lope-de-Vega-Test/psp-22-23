
class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }
    
    public void incrementa() {
        c++;
    }
    
    public int valor() {
        return c;
    }
    
}

class HiloIncrementa extends Thread 
{
    private Contador contador;

    public HiloIncrementa(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }
    
    public void run() {
        for(int j=0; j<1000 ;j++) // TODO: Cambia a 3000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();
            /*
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
            */
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
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
        for(int j=0; j<1000;j++) // TODO: Cambia a 3000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();
            /*
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
            */
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
}

public class ua2tarea1fr1 {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: Bloques NO Sincronizados");
        System.out.println("-------------------------------");
        

        Contador cont = new Contador(0);
        Hilo hilo1 = new Hilo("hilo1: ", cont);
        Hilo hilo2 = new Hilo("hilo2:", cont);
        Hilo hilo3 = new Hilo("hilo3:", cont);
        Hilo hilo4 = new Hilo("hilo4:", cont);
        Hilo hilo5 = new Hilo("hilo5:", cont);

        
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