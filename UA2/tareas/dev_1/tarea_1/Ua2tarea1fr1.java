
package ua2tarea1fr1;

/**
 *
 * @author Irene Alba
 */

class Contador{
    private int c = 0;
    
    Contador(int c){
        this.c = c;
    }
    
    public void incrementa(){
        c++;
    }
    
    public int valor(){
        return c;
    }
    
}

class HiloSumador extends Thread{
    private Contador contador;
    
    public HiloSumador(String nombre, Contador c){
        setName(nombre);
        contador = c;
    }
    
    public void run(){
        for(int j = 0; j < 1000; j++){
            contador.incrementa();
        }
        
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
}

public class Ua2tarea1fr1 {

    public static void main(String[] args) {
        
        System.out.println("------------------------------------------------");
        System.out.println("Programación y sincronización de hilos en Java 1");
        System.out.println("------------------------------------------------");
        
        Contador cont = new Contador(0);
        HiloSumador hilo1 = new HiloSumador("Hilo 1", cont);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", cont);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", cont);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", cont);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", cont);
        
        System.out.println("Comienza la ejecución de los hilos...");
        System.out.println("-------------------------------------");
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }catch(InterruptedException e){
            //Nada para hacer
        }
        
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("---------------------------------------");
        
    }
    
}
