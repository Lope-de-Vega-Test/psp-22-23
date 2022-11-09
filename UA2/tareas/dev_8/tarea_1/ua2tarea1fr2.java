
class Contador {
    private int c = 0; // Atributo Contador

    Contador(int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        c++;
    }

    public synchronized void decrementa() {
        c--;
    }

    public synchronized int valor() {
        return c;
    }
} // Fin Class Contador

class HiloSumador implements Runnable {
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
        Thread.currentThread().setName(nombre);
        contador = c;
    }

    public void run() {
        for (int j = 0; j < 1000; j++) {
            contador.incrementa();
        }
        System.out.println(Thread.currentThread().getName() + " - contador vale " + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr2 {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("Hilos: Metodos Sincronizados");
        System.out.println("----------------------------");
        // Declaramos la variable contador
        Contador cont = new Contador(0);
        // Generamos los hilos con su nombre y contador
        HiloSumador hiloSuma1 = new HiloSumador("Hilo Sumador 1", cont);
        HiloSumador hiloSuma2 = new HiloSumador("Hilo Sumador 2", cont);
        HiloSumador hiloSuma3 = new HiloSumador("Hilo Sumador 3", cont);
        HiloSumador hiloSuma4 = new HiloSumador("Hilo Sumador 4", cont);
        HiloSumador hiloSuma5 = new HiloSumador("Hilo Sumador 5", cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        // Lanzamos los hilos
        Thread t1 = new Thread(hiloSuma1);
        Thread t2= new Thread(hiloSuma2);
        Thread t3 = new Thread(hiloSuma3);
        Thread t4 = new Thread(hiloSuma4);
        Thread t5 = new Thread(hiloSuma5);
        // Lanzamos los hilos
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Hacemos que se paren los hilos hasta que no terminen los demás
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

        } catch (InterruptedException e) {
            // Nothing to do here ...
        }
        // Mostramos los resultados
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}