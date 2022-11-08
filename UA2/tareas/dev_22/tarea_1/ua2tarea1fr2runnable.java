

package bloquesnosincronizados;

import java.util.ArrayList;

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


class HiloSumador implements Runnable  
{
    private Contador contador;

    public HiloSumador(Contador c) {
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
            System.out.println("Hilo con id " + Thread.currentThread().getId() + " - contador vale " + contador.valor());
        }
    }

} // Fin Class HiloSumador



public class BloquesNoSincronizados {
    public static void main(String[] args) {
        
        ArrayList<Thread> myThreads = new ArrayList<Thread>(); //Array de hilos
        int threadsNumber = 5; //Nº de hilos, 5

        Contador cont = new Contador(100);
        
        for(int i=0; i<threadsNumber; i++) //For de adición de hilos al array
        {
            HiloSumador hilo = new HiloSumador(cont);
            myThreads.add(new Thread(hilo));
        }

        
        System.out.println("Comienza la ejecucion de los hilos ...");
        System.out.println("--------------------------------------");
        
        
        
        for(int i=0; i<myThreads.size(); i++) //El start se implementa con un for
        {
            myThreads.get(i).start();
        }
        

        for(int i=0; i<myThreads.size(); i++) //Cada join se hace en un try-catch
        {                                       // vía for
            try
            {
                myThreads.get(i).join();
            }
            catch (InterruptedException e)
            {
                // Nothing to do here ...
            }
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecucion de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
        
        /* Comienza la ejecucion de los hilos ...
            --------------------------------------
            Hilo con id 14 - contador vale 1100
            Hilo con id 18 - contador vale 2100
            Hilo con id 17 - contador vale 3100
            Hilo con id 15 - contador vale 4100
            Hilo con id 16 - contador vale 5100
            --------------------------------------
            ... Finaliza la ejecucion de los hilos
            --------------------------------------
            Valor Final del Contador: 5100
            --------------------------------------

        La única diferencia está en que tenemos un id por hilo pero
        hacen lo mismo que en los otros dos casos*/
        
    }
}