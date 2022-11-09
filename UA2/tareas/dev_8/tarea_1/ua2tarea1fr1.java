
class Contador {
    private int c = 0; // Atributo Contador

    Contador(int c) {
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

/**
 * Declaramos la clase de Hilo contador
 */
class HiloSumador extends Thread {
    private Contador contador;

    /**
     * Esta método creará un hilo con un nombre y contador
     * @param nombre
     * @param c
     */
    public HiloSumador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    /**
     * Este método genera un bucle, incrementa un contador en cada interación y lo muestra
     */
    public void run() {
        for (int j = 0; j < 1000; j++) {
            contador.incrementa();
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr1 {
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
        hiloSuma1.start();
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();

        // Hacemos que se paren los hilos hasta que no terminen los demás
        try {
            hiloSuma1.join();
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostramos los resultados
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}