import java.lang.Thread;

class Contador{
    private int c = 0;  // Atributo Contador
    Contador (int c) {
        this.c = c;
    }
    //funcion que usaremos para incrementar el contador
    //Gracias a synchronized, los hilos se coordinaran a la hora de acceder a contador
    public synchronized void incrementa() {
        c++;
    }
    //funcion que devuelve el valor de contador
    //Gracias a synchronized, los hilos se coordinaran a la hora de acceder a contador
    public int valor() {
        return c;
    }
}

class HiloSumador implements Runnable
//Implementamos la interfaz Runnable
{
    //llamamos a la clase contador creando una variable con ella.
    private Contador contador;

    //constructor
    public HiloSumador(Contador c) {
        this.contador = c;
    }
    //incrementamos la variable contador
    public synchronized void run() {
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        System.out.println(" Numero del contador:" + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr2runnable {

    public static void main(String[] args) {
        System.out.println("-------------------------------");
        //contador a 0
        Contador cont = new Contador(0);

        //creamos los hilos con Runnable
        Runnable hilo1 = new HiloSumador( cont);
        Runnable hilo2 = new HiloSumador( cont);
        Runnable hilo3 = new HiloSumador( cont);
        Runnable hilo4 = new HiloSumador( cont);
        Runnable hilo5 = new HiloSumador( cont);

        System.out.println("Hilos ejecutandose");

        //Ejecutamos los hilos
        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();
        new Thread(hilo4).start();
        new Thread(hilo5).start();

        //mostramos el valor del contador
        System.out.println("--------------------------------");
        System.out.println("Hilos finalizados");
        System.out.println("--------------------------------");
        System.out.println("contador: " + cont.valor());
        System.out.println("--------------------------------");
    }
}