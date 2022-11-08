



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
   class HiloSumador extends Thread{
       private Contador contador;
       
       public HiloSumador(String nombre, Contador c){
           setName(nombre);
           contador=c;
       }
       public void run(){
           synchronized(contador){
               for(int j=0; j<1000;j++){
                   contador.incrementa();
               }
               System.out.println(getName()+"-contador vale "+contador.valor());
           }
       }
   }
   //Fin de la clase HiloSumador 
public class ua2tarea1fr2 {
    public static void main(String[] args) {
      System.out.println("----------------------------");
      System.out.println("Hilos: Bloques Sincronizados");
      System.out.println("----------------------------");
      
      Contador cont=new Contador(0);
      HiloSumador hiloSuma1 = new HiloSumador("Hilo Sumador", cont);
      HiloSumador hiloSuma2= new HiloSumador("Hilo sumador", cont);
      HiloSumador hiloSuma3= new HiloSumador("Hilo sumador", cont);
      HiloSumador hiloSuma4= new HiloSumador("Hilo sumador", cont);
      HiloSumador hiloSuma5= new HiloSumador("Hilo sumador", cont);
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        hiloSuma1.start();
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();
            
        try{
            hiloSuma1.join();
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
        }
        catch(InterruptedException e){
            
        }
         System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}

