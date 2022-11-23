package tarea1fr2;

/**
 *
 * @author Lucía Luna
 */

//Comienza la clase contador
class Contador
{
    private int c = 0; 
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c++;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
} 
// Termina la Clase Contador


class HiloSumador1 extends Thread 
{
    private Contador contador;

    public HiloSumador1(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    //El contador se encargará de confirmar que se realiza la suma
    public void run() {
        synchronized(contador){
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
} // Fin Clase HiloSumador

public class Tarea1fr2 {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Tarea 1: Clases Thread con Syncronized");
        System.out.println("-------------------------------");
        //ExecutionTimer timer = new ExecutionTimer();

        Contador cont = new Contador(0);
        HiloSumador1 hiloSuma1 = new HiloSumador1("Hilo Sumador 1: ", cont);
        HiloSumador1 hiloSuma2 = new HiloSumador1("Hilo Sumador 2: ", cont);
        HiloSumador1 hiloSuma3 = new HiloSumador1("Hilo Sumador 3: ", cont);
        HiloSumador1 hiloSuma4 = new HiloSumador1("Hilo Sumador 4: ", cont);
        HiloSumador1 hiloSuma5 = new HiloSumador1("Hilo Sumador 5: ", cont);
        
        //HiloRestador hiloResta = new HiloRestador("Hilo Restador", cont);
        





        //timer.start();
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        hiloSuma1.start();
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();
        //hiloResta.start();

        try {
            hiloSuma1.join();
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
         //   hiloResta.join();
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
        //timer.stop();
        //timer.printElapsedTime();
    }
}

