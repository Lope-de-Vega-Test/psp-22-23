import java.lang.Thread;

class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        //usaremos "synchronized" para hacer que los hilos se coordinen.
        c++;
    }

    public int valor() {
        //devolvemos el valor actual del contador.
        return c;
    }
} // Fin Class Contador

class Hilo1 implements Runnable
//Implementamos la extension runnable para iniciar el hilo en main
{
     //llamamos a la clase contador
    private Contador contador;

    public Hilo1(Contador c) {
        //creamos un constructor del hilo.
        this.contador = c;
    }

    public synchronized void run() {
        //Creamos esta funcion para declarar los 5 hilos
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        //Ahora mostramos el numero del hilo y el contador
        System.out.println("El contador vale " + contador.valor());
    }
} 

public class ua2tarea1fr2runnable {

    public static void main(String[] args) {
        System.out.println("-------------------------------");
        //Iniciamos el contador a 0.
        Contador cont = new Contador(0);

        //Declaramos los 5 hilos que vamos a utilizar
        Runnable hilo1 = new Hilo1( cont);
        Runnable hilo2 = new Hilo1( cont);
        Runnable hilo3 = new Hilo1( cont);
        Runnable hilo4 = new Hilo1( cont);
        Runnable hilo5 = new Hilo1( cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        //Iniciamos los hilos con start
        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();
        new Thread(hilo4).start();
        new Thread(hilo5).start();

        //Ahora utilizaremos un sleep para que los hilos puedan mostrar correctamente el valor del contador
        try {
            Thread.sleep(700);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());//Mostramos el valor final.
        System.out.println("--------------------------------------");
    }
}