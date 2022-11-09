class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }
    public void incrementa() {
        //usaremos esta funcion para incrementar el hilo
        c++;
    }
    public int valor() {
        //Devolvemos el valor actual del contador.
        return c;
    }
} // Fin Class Contador

class Hilo1 extends Thread 
{
    private Contador contador;

    public Hilo1(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        for(int j=0; j<1000;j++) // TODO: Cambia a 3000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();     
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
}

public class ua2tarea1fr1 {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: Bloques NO Sincronizados");
        System.out.println("-------------------------------");


        Contador cont = new Contador(100);
        Hilo1 hilo1 = new Hilo1("Hilo 1", cont);
        Hilo1 hilo2 = new Hilo1("Hilo 2", cont);
        Hilo1 hilo3 = new Hilo1("Hilo 3", cont);
        Hilo1 hilo4 = new Hilo1("Hilo 4", cont);
        Hilo1 hilo5 = new Hilo1("Hilo 5", cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();

        }
        catch (InterruptedException e){}
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}
