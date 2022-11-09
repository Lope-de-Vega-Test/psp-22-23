package paquete;


class Contador
{
    private int contadorcarlos = 0;  // contador
    Contador (int contadorcarlos) { 
        this.contadorcarlos = contadorcarlos; 
    }

    public void incrementa() {
        contadorcarlos++; //incrementa el valor en 1
    }

    public int valor() {
        return contadorcarlos; 
    }
} 

class HiloSumador extends Thread //clase hilo
{
    private Contador contador;

    public HiloSumador(String nombre, Contador contadorcarlos) {
        setName(nombre);
        contador = contadorcarlos;
    }

    public void run() {
        for(int j=0; j<1000;j++) //incrementa el valor en 1000
        {
            contador.incrementa();
        }
        System.out.println("Hilo: " + contador.valor()); //resultado del hilo
    }
}

public class ua2tarea1fr1 {


    public static void main(String[] args) {

        Contador c = new Contador(0);
        //creamos los hilos
        HiloSumador hilo1 = new HiloSumador("Hilo 1", c);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", c);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", c);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", c);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", c);
        
        //hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        //try catch por si falla
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }
        catch (InterruptedException e)
        {

            System.out.println("Error");
        }
        
        //resultado final
        System.out.println("Valor Final del Contador: " + c.valor());
    } 
}