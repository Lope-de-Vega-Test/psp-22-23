package ua2tarea1fr2;

/**
 *
 * @author damik
 */

class Contador
{
    private int cont = 0;  // Atributo Contador
    Contador (int cont) { 
        this.cont = cont; 
    }

    public synchronized void incrementa() { //Usamos synchronized para que los hilos 
        cont++; //Se incrementa el valor 1 vez
    }

    public int valor() {
        return cont; //Se devuelve el valor resultante
    }
} 

class HiloSumador extends Thread //Creacion de la clase Hilo
{
    private Contador contador;

    public HiloSumador(String nombre, Contador cont) {
        setName(nombre);
        contador = cont;
    }

    public synchronized void run() {
        for(int j=0; j<1000;j++) //Se incrementa 1000 veces el valor
        {
            contador.incrementa();
        }
        System.out.println("Hilo: " + contador.valor()); //Se muestra el resultado del hilo
    }
}

public class Ua2tarea1fr2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Contador conta = new Contador(0);
        //Creamos todos los hilos
        HiloSumador hilo1 = new HiloSumador("Hilo 1", conta);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", conta);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", conta);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", conta);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", conta);
        
        //Se inician los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        //Hacemos un try catch por si ocurre un error
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Error, pruebe de nuevo");
            // Nothing to do here ...
        }
        
        //Se muestra el resultado final
        System.out.println("Valor Final del Contador: " + conta.valor());
    } 
}
