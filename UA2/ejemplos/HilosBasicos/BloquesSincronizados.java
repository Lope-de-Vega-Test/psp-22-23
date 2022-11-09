// EJEMPLO. Hilos: Bloques Sincronizados
// Basado en: Programación de servicios y procesos - Técnico Superior en DAM. Mª Jesús Ramos Martín. Editorial Garceta. 2ª Edición. 2018. ISBN: 978-84-1728-931-7.
// @author: dperez@ceslopedevega.com
//
// Compilar y ejecutar: javac BloquesSincronizados.java && java BloquesSincronizados


public class BloquesSincronizados {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("Hilos: Bloques Sincronizados");
        System.out.println("----------------------------");

        Contador cont = new Contador(100);
        HiloSumador hiloSuma = new HiloSumador("Hilo Sumador", cont);
        HiloRestador hiloResta = new HiloRestador("Hilo Restador", cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        hiloSuma.start();
        hiloResta.start();

        try {
            hiloSuma.join();
            hiloResta.join();
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
    }
}

class Contador
{
    private int c = 0;  // Atributo Contador
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
            for(int j=0; j<300;j++)
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

class HiloRestador extends Thread 
{
    private Contador contador;

    public HiloRestador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        synchronized(contador) {
            for(int j=0; j<300;j++)
            {
                contador.decrementa();
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
} // Fin Class HiloRestador


