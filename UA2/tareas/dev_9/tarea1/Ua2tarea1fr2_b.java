class Ua2tarea1fr2_b {
    public static void main(String[] args) {
        //Usamos la variable.
        Numero nmero=new Numero(0);
        //Creamos los hilos que vamos a usar y le devolvemos el nombre del hilo y el numero.
        Runnable hilo1=new Hilos("Hilo 1", nmero);
        Runnable hilo2=new Hilos("Hilo 2", nmero);
        Runnable hilo3=new Hilos("Hilo 3", nmero);
        Runnable hilo4=new Hilos("Hilo 4", nmero);
        Runnable hilo5=new Hilos("Hilo 5", nmero);
        //Ejecutamos los hilos
        new Thread (hilo1).start();
        new Thread (hilo2).start();
        new Thread (hilo3).start();
        new Thread (hilo4).start();
        new Thread (hilo5).start();
        System.out.println("Comienza la ejecución de los hilos");
        //Control de errores por si acaso hay algun fallo.
        try{
            Thread.sleep(700);
        }
        catch(Exception e){
            System.out.println(e);
        }//Los hilos dejan de ejecutarse.*/
        System.out.println("... Finaliza la ejecución de los hilos");
        //Mmostramos el valor final de la variable
        System.out.println("Valor Final del Contador: " + nmero.valor());
    }
}

class Numero{
    private int num=0;
    Numero(int num){
        this.num=num;
    }
    //Ponemos synchronized para asegurarnos de que la variable se sume el numero de veces que se ejecute un hilo.
    public synchronized void incrementar(){
        num++;
    }
    public void decrementar(){
        num--;
    }
    public int valor(){
        return num;
    }
}
//Esta clase será la que utilizaremos para que los hilos sumen la variable que creamos antes para que se sume 5000 se supone.
class Hilos implements Runnable{
    private Numero numero;
    private String nombrehilo;
    //Constructor de los hilos
    public Hilos(String nombre, Numero num){
        nombrehilo=nombre;
        numero=num;
    }   
    //Esta funcion la usaremos para que se ejecuten los hilos.
    public  void run(){
        
        for(int i=0; i<1000; i++){
            numero.incrementar();
        }
         
        //Muestra el nombre del hilo y el valor de la variable creada antes.
        System.out.println(nombrehilo+" - contador vale "+numero.valor());
    }
}
