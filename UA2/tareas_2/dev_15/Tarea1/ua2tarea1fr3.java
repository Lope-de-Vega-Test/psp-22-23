
import java.util.ArrayList;



class Contador{
       private int c=0; //Atributo contador;
       Contador(int c){
           this.c=c;
       }
       public void incrementa(){
           c++;
       }
       public void decrementa(){
           c--;
       }
       public int valor(){
           return c;
       }
   }
   //Fin de la clase contador
   class HiloSumador implements Runnable{
       private Contador contador;
       
       public HiloSumador(String nombre, Contador c){
           Thread.currentThread().setName(nombre);
           contador=c;
       }
       public void run(){
          {
               for(int j=0; j<1000;j++){
                   contador.incrementa();
               }
               System.out.println(Thread.currentThread().getName()+"-contador vale "+contador.valor());
           }
       }
    }
   
public class ua2tarea1fr3 
{
    public static void main(String args[]) 
    {
        ArrayList<Thread> ilo = new ArrayList<Thread>(); 
        int hilosSumadores=5;
        Contador conta= new Contador(0);

        for(int i=0; i<hilosSumadores; i++)
        {
            HiloSumador hilos=new HiloSumador("Hilo"+i,conta);
            ilo.add(new Thread(hilos));
        }
         System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        for(int i=0; i<ilo.size(); i++)
        {
            ilo.get(i).start();
        }
        
        
        for(int i=0; i<ilo.size(); i++)
        {
            try
            {
                ilo.get(i).join();
            }
            catch (InterruptedException e)
            {
                // Nothing to do here ...
            }
        }
        
         System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + conta.valor());
    }
}
