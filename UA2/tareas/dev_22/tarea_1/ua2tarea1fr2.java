

package bloquesnosincronizados;

/**
 *
 * @author Ignacio
 */

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
} // Fin Class Contador


class HiloSumador extends Thread 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        synchronized(contador) {
            for(int j=0; j<1000;j++)
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
} // Fin Class HiloSumador



public class BloquesNoSincronizados {
    public static void main(String[] args) {

        Contador cont = new Contador(100);
        HiloSumador hiloSuma = new HiloSumador("Hilo Sumador", cont);
        HiloSumador hiloSuma2 = new HiloSumador("Hilo Sumador 2", cont);
        HiloSumador hiloSuma3 = new HiloSumador("Hilo Sumador 3", cont);
        HiloSumador hiloSuma4 = new HiloSumador("Hilo Sumador 4", cont);
        HiloSumador hiloSuma5 = new HiloSumador("Hilo Sumador 5", cont);
        

        
        System.out.println("Comienza la ejecucion de los hilos ...");
        System.out.println("--------------------------------------");
        
        hiloSuma.setPriority(Thread.MAX_PRIORITY); //Prioridades
        hiloSuma2.setPriority(3);
        hiloSuma3.setPriority(5);
        hiloSuma4.setPriority(7);
        hiloSuma5.setPriority(Thread.MIN_PRIORITY);
        
        hiloSuma.start(); //Comienzan
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();
        

        try {
            hiloSuma.join(); //Bloqueamos subproceso de llamada hasta que acaban
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
            
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecucion de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
        
        /* Comienza la ejecucion de los hilos ...
            --------------------------------------
            Hilo Sumador - contador vale 1100
            Hilo Sumador 5 - contador vale 2100
            Hilo Sumador 2 - contador vale 3100
            Hilo Sumador 3 - contador vale 4100
            Hilo Sumador 4 - contador vale 5100
            --------------------------------------
            ... Finaliza la ejecucion de los hilos
            --------------------------------------
            Valor Final del Contador: 5100
            --------------------------------------

        No se aprecia diferencia alguna más allá de la variación en el orden*/
    }
}