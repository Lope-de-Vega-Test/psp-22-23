// Compilar y ejecutar: javac ua2tarea1fr2.java && java ua2tarea1fr2

class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        //Usaremos esta función para incrementar en 1 el valor del contador en los diferentes hilos
        //cada iteración del bucle for que añadiremos más adelante.
        //Hacemos uso de "synchronized" para que los hilos se coordinen a la hora de acceder al contador.
        c++;
    }

    public int valor() {
        //Devolvemos el valor actual del contador.
        return c;
    }
} // Fin Class Contador

class HiloSumador extends Thread
//En este caso implementaremos los hilos heredando de la clase Thread, lo que afectará a la manera en la que
//llamaremos a los hilos en la función main.
{
    private Contador contador;//Instanciamos a la clase Contador.

    public HiloSumador(String nombre, Contador c) {
        //creamos un constructor del hilo para que se le asigne el valor de contador y un nombre.
        setName(nombre);
        contador = c;
    }

    public synchronized void run() {
        //Esta función nos servirá para que, tras declarar los 5 hilos, podamos ejecutarlos.

        //Gracias a este for, que ya nombramos antes, hacemos que cada hilo, al iniciarse, incremente en 1 el valor del contador
        //por cada iteración, de tal modo que cuando finalice el bucle, cada hilo incrementará en 1000 dicho valor.
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        //Por último, mostramos el valor que tiene el contador al finalizar la operación.
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr2 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");

        Contador cont = new Contador(0);//Inicializamos el contador con valor 0.

        //Declaramos los 5 hilos que necesitamos para el ejercicio pasandole los parámetros de nombre y contador.

        //Para asignarle el nombre lo introducimos directamente entre comillas, para el contador, le asignamos la variable
        //declarada antes.
        HiloSumador hilo1 = new HiloSumador("Hilo 1", cont);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", cont);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", cont);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", cont);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        //Iniciamos el proceso de todos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        //En este try catch arrancamos los cinco hilos usando .join() para mantener el orden de ejecucion entre
        //los hilos para que se ejecute primero el 1, luego el 2, etc...
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
