class Contador
{
    private int cont = 0;  
    Contador (int cont) { 
        this.cont = cont; 
    }

    public synchronized void incrementar() { 
        cont++; 
    }

    public int valor() {
        return cont; 
    }
} 




class HiloSumador extends Thread 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador cont) {
        setName(nombre);
        contador = cont;
    }

    public synchronized void run() {
        for(int j=0; j<1000;j++) 
        {
            contador.incrementar();
        }
        System.out.println("Hilo: " + contador.valor()); 
    }
}

public class Ua2tarea1fr2 {

    public static void main(String[] args) {
       
        Contador contar = new Contador(0);
        
        HiloSumador hilo1 = new HiloSumador("Hilo 1", contar);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", contar);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", contar);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", contar);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", contar);
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
        catch (InterruptedException e)
        {
            System.out.println("Error, pruebe de nuevo");
            
        }

       
        System.out.println("Valor Final del Contador: " + contar.valor());
    } 
}
