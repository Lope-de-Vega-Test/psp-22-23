public class Ua2tarea1fr2 {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("HiloS Sincronizados");
        System.out.println("----------------------------");

        Contador contadorx = new Contador(0);


        HiloSumador hsuma1 = new HiloSumador("Hilo Suma 1", contadorx);
        HiloSumador hsuma2 = new HiloSumador("Hilo Suma 2", contadorx);
        HiloSumador hsuma3 = new HiloSumador("Hilo Suma 3", contadorx);
        HiloSumador hsuma4 = new HiloSumador("Hilo Suma 4", contadorx);
        HiloSumador hsuma5 = new HiloSumador("Hilo Suma 5", contadorx);

        System.out.println("EJECUTAMOS LOS HILOS");
        System.out.println("******************************");

        Thread h1 = new Thread(hsuma1);
        Thread h2= new Thread(hsuma2);
        Thread h3 = new Thread(hsuma3);
        Thread h4 = new Thread(hsuma4);
        Thread h5 = new Thread(hsuma5);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();

        } catch (InterruptedException e) {
            // Nothing to do here ...
        }
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + contadorx.valor());
        System.out.println("--------------------------------------");
    }
}
Class Contador {
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
}
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
}


