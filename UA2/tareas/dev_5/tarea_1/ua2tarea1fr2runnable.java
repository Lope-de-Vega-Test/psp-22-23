
package ua2tarea1fr2runnable;
import java.util.ArrayList;

/**
 *
 * @author elena
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

class HiloSumador implements Runnable{
    private Contador contador;
    
    public HiloSumador(String nombre, Contador c){
        Thread.currentThread().setName(nombre);
        contador = c;
    }
    
    public void run(){
        synchronized(contador){
            for(int j = 0; j < 1000; j++){
                contador.incrementa();
            }
            
            System.out.println(Thread.currentThread().getName() + " - contador vale " + contador.valor());
                
        }        
    }
}

public class Ua2tarea1fr2runnable {

    public static void main(String[] args) {
        
        System.out.println("------------------------------------------------");
        System.out.println("Programación y sincronización de hilos en Java 1");
        System.out.println("------------------------------------------------");
        
        Contador cont = new Contador(0);
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        int nHilos = 5;
        
        for(int i = 0; i < nHilos; i++){
            HiloSumador hilo = new HiloSumador("Hilo " + i, cont);
            hilos.add(new Thread(hilo));
        }                      
        
        System.out.println("Comienza la ejecución de los hilos...");
        System.out.println("-------------------------------------");
        
        for(int i = 0; i < nHilos; i++){
            hilos.get(i).start();
        }
        
        for(int i = 0; i < nHilos; i++){
            try{
                hilos.get(i).join();
            }catch(InterruptedException e){
                
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("---------------------------------------");
        
    }
    
}

