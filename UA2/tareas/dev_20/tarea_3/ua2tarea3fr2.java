package ua2tarea3fr2;

/**
 *
 * @author Lucía Luna
 */
import java.io.FileReader;

//Esta clase controla el tiempo de ejecución de los hilos
class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()    
    {
        elapsedTime();
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
  //Esta clase crea los hilos debido a la herencia de la clase Thread
   class hilo extends Thread{
   String[] args;
   int numero=0;
   
   //constructor
   public hilo(String[] args, int numero){
   this.args=args;
   this.numero=numero;
   }
   
//Funcion creada para arranca los hilos y para leerlos
 public void run(){
    int lineas=0;
    try {
        FileReader fr = new FileReader(args[numero]);
        while(fr.read()!=-1){
            lineas++;
        }
    } catch (Exception e) {
      
    }
    System.out.println("Numero de lineas: "+lineas);
    
   }
}

//clase principal 

  public class ua2tarea3fr2{
      
public static void main(String[] args) {
    ExecutionTimer timer=new ExecutionTimer();
    timer.start();

    hilo hilo1=new hilo(args,0);
    hilo hilo2=new hilo(args,1);
    hilo hilo3=new hilo(args,2);

    System.out.println("comienzan los hilos");
    //con start 
    hilo1.start();
    hilo2.start();
    hilo3.start();

    try {
        hilo1.join();
        hilo2.join();
        hilo3.join();

    } catch (Exception e) {
       
    }
    System.out.println("--------------------");
    System.out.println("hilos terminados");

    timer.stop();
    timer.printElapsedTime();
}  

    } 
