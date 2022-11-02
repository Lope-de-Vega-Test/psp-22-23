// Compilar y ejecutar: javac ua2tarea1fr3.java && java ua2tarea1fr3

import java.io.*;
import java.lang.Thread;

class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
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
        this.contador = c;
    }

    public synchronized void run() {
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        System.out.println(" - contador vale " + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr3 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");

        Contador cont = new Contador(0);
        Runnable hilo1 = new HiloSumador( cont);
        Runnable hilo2 = new HiloSumador( cont);
        Runnable hilo3 = new HiloSumador( cont);
        Runnable hilo4 = new HiloSumador( cont);
        Runnable hilo5 = new HiloSumador( cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();
        new Thread(hilo4).start();
        new Thread(hilo5).start();

        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}
