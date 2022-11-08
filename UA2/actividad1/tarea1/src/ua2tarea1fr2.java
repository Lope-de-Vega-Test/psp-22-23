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
        //Devolvemos el valor actual del contador.
        return c;
    }
} // Fin Class Contador

class Hilo1 extends Thread
//Ahora usaremos la herencia de thread para que podamos llamar a los hilos en la funcion main
{
    //llamamos a la clase contador
    private Contador contador; 

    public Hilo1(String nombre, Contador c) {
        //creamos un constructor del hilo.
        setName(nombre);
        contador = c;
    }

    public synchronized void run() {
        //Creamos esta funcion para declarar los 5 hilos
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        //Ahora mostramos el numero del hilo y el contador
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
} 

public class ua2tarea1fr2 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");
        //Iniciamos el contador a 0.
        Contador cont = new Contador(0);
        //Declaramos los 5 hilos que vamos a utilizar
        Hilo1 hilo1 = new Hilo1("Hilo 1", cont);
        Hilo1 hilo2 = new Hilo1("Hilo 2", cont);
        Hilo1 hilo3 = new Hilo1("Hilo 3", cont);
        Hilo1 hilo4 = new Hilo1("Hilo 4", cont);
        Hilo1 hilo5 = new Hilo1("Hilo 5", cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        //Iniciamos los hilos con start
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        //Ahora ordenamos los hilos con el try
        try {

            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }

        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());//Mostramos el valor final.
        System.out.println("--------------------------------------");
    }
}