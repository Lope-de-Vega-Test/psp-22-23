package ua2tarea1fr2runnable;
import java.util.ArrayList;

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
    
    public void incrementa() {
        cont++; //Se incrementa el valor 1 vez
    }
    
    public int valor() {
        return cont; //Se devuelve el valor resultante
    }
    
}

class HiloSumador implements Runnable{  //Implementamos Runnable
    private Contador contador;
    
    public HiloSumador(String nombre, Contador c){
        Thread.currentThread().setName(nombre);
        contador = c;
    }
    
    public void run(){  //Se realiza lo que pide el ejercicio
        synchronized(contador){
            for(int j = 0; j < 1000; j++){ //Se incrementa en 1 un total de 1000 veces el valor
                contador.incrementa();
            }
            
            System.out.println(Thread.currentThread().getName() + " valor:  " + contador.valor());  //Se muestra el resultaod de cada hilo
                
        }        
    }
}

public class Ua2tarea1fr2runnable {

    public static void main(String[] args) {
        
        System.out.println("Bienvenido");
        System.out.println("");
        
        Contador cont = new Contador(0);
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        int numHilos = 5;
        
        for(int i = 0; i < numHilos; i++){
            HiloSumador hilo = new HiloSumador("Hilo " + i, cont);
            hilos.add(new Thread(hilo));
        }                      
        
        System.out.println("Se empiezan a ejecutar los hilos");
        System.out.println("");
        
        //Se indica con For cuantas veces queremos que se ejecuten, en este caso numHilos, que vale 5
        
        for(int i = 0; i < numHilos; i++){
            hilos.get(i).start();
        }
        
        for(int i = 0; i < numHilos; i++){
            try{
                hilos.get(i).join();
            }catch(InterruptedException e){
                //empty
            }
        }
        
        //Se muestra el valor final
        System.out.println("");
        System.out.println("Valor Final del Contador: " + cont.valor());
        
    }
    
}
