package ua2tarea1fr1;
//Creamos la clase numero que se usara para crear la variable comun entre los hilos
class Numero{
    private int num=0;
    Numero(int num){
        this.num=num;
    }
    public void incrementar(){
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
class Hilos extends Thread{
    private Numero numero;
    //Constructor de los hilos
    public Hilos(String nombre, Numero num){
        setName(nombre);
        numero=num;
    }   
    //Esta funcion la usaremos para que se ejecuten los hilos.
    public void run(){
        for(int i=0; i<1000; i++){
            numero.incrementar();
        }
        //Muestra el nombre del hilo y el valor de la variable creada antes.
        System.out.println(getName()+" - contador vale "+numero.valor());
    }
}

public class Ua2tarea1fr1 {
    public static void main(String[] args) {
        //Usamos la variable.
        Numero nmero=new Numero(0);
        //Creamos los hilos que vamos a usar y le devolvemos el nombre del hilo y el numero.
        Hilos hilo1=new Hilos("Hilo 1", nmero);
        Hilos hilo2=new Hilos("Hilo 2", nmero);
        Hilos hilo3=new Hilos("Hilo 3", nmero);
        Hilos hilo4=new Hilos("Hilo 4", nmero);
        Hilos hilo5=new Hilos("Hilo 5", nmero);
        //Ejecutamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        System.out.println("Comienza la ejecución de los hilos");
        //Control de errores por si acaso hay algun fallo.
        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }
        catch(InterruptedException e){}//Los hilos dejan de ejecutarse.
        System.out.println("... Finaliza la ejecución de los hilos");
        //Mmostramos el valor final de la variable
        System.out.println("Valor Final del Contador: " + nmero.valor());
    }
    
}
