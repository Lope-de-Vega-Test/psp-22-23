import java.util.ArrayList;
import java.util.Random;

//-------------------------------------------------------------------//
//CLASE PROPIA DEL VASO DE CERVEZA DEL QUE SE USARÁ PARA BEBER
//-------------------------------------------------------------------//
public class VasoCerveza{
    private int id;
    private int tipo;
    private boolean disponible;

    public VasoCerveza(int id, int tipo) {
        disponible = true;
        this.tipo = tipo;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        if (this.tipo == 0 || this.tipo == 1){
            this.tipo = tipo;
        }
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}

//-------------------------------------------------------------------//
//CLASE CAMARERO ENCARGADA DE CONTENER LOS METODOS Y LA LISTA DE BEBER
//-------------------------------------------------------------------//
public class Camarero{
    private String nombre;
    private ArrayList<VasoCerveza> listaVasos = new ArrayList<VasoCerveza>();

    public Camarero(String nombre) {
        for(int i=0; i<3;i++){
            this.listaVasos.add(new VasoCerveza(i,randInt(0, 1)));
        }
        this.nombre = nombre;
    }

    //CLIENTE COGE CERVEZA ELIMINANDOLA DE LA ARRAYLIST Y DEVOLVIENDO EL TIPO DEL VASO
    public synchronized int servirCerveza(){
        boolean flag_beber = false;
        int tipo_cerveza;

        //SE MANTIENE HASTA QUE HAYA ALGUN VASO DISPONIBLE
        while(!listaVasos.get(0).isDisponible() && !listaVasos.get(1).isDisponible() && !listaVasos.get(2).isDisponible()) {                                        // Si hay datos ...
            try {
                wait();                                             // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}            
        }

        //COMPRUEBA CUAL ES EL VASO DISPONIBLE
        if(listaVasos.get(0).isDisponible()){
            listaVasos.get(0).setDisponible(false);
            tipo_cerveza = listaVasos.get(0).getTipo();
        }else if(listaVasos.get(1).isDisponible()){
            listaVasos.get(1).setDisponible(false);
            tipo_cerveza = listaVasos.get(1).getTipo();
        }else{
            listaVasos.get(2).setDisponible(false);
            tipo_cerveza = listaVasos.get(2).getTipo();
        }
        notify();
        return tipo_cerveza;
    }

    //CLIENTE DEVUELVE LA CERVEZA AÑADIENDO AL FINAL DEL ARRAYLIST UNA NUEVA CERVEZA
    public synchronized void devolverCerveza(){
        if(!listaVasos.get(0).isDisponible()){
            listaVasos.get(0).setDisponible(true);
            listaVasos.get(0).setTipo(randInt(0, 1));
        }else if(!listaVasos.get(1).isDisponible()){
            listaVasos.get(1).setDisponible(true);
            listaVasos.get(1).setTipo(randInt(0, 1));
        }else{
            listaVasos.get(2).setDisponible(true);
            listaVasos.get(2).setTipo(randInt(0, 1));
        }
    }
    //CONTAR VASOS TOTALES DISPONIBLES PARA BEBER
    public void contarVasos(){
        int numtotalvasos = 0;
        for (int i : listaVasos){
            if (listaVasos.get(i).isDisponible()){
                numtotalvasos++;
            }
        }
        System.out.println("El numero total de vasos del bar disponibles es: "+ numtotalvasos);
    }

    //FUNCION QUE DEVUELVE ENTERO RANDOM ENTRE 1 MINIMO Y MAXIMO
    public int randInt(int min, int max) {
        Random rand;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}

//-------------------------------------------------------------------//
//CLASE HILOCLIENTES ENCARGADA DE LLEVAR LOS CLIENTES QUE VAN A BEBER
//-------------------------------------------------------------------//
public class HilosClientes extends Thread{
    private float litrosbebidos;
    private int cervezarecibida;
    private Camarero camarero;
    private String nombre;

    public HilosClientes(Camarero camarero, String nombre){
        this.camarero = camarero;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        boolean infinito = true;
        do{
            synchronized(camarero){
                try {
                    //SIRVE CERVEZA
                    cervezarecibida = camarero.servirCerveza();
                    System.out.println("Mou sirve la cerveza a: "+nombre);
                    if(cervezarecibida == 1){
                        litrosbebidos = litrosbebidos + (473/1000);
                    }else{
                        litrosbebidos = litrosbebidos + ((473/1000)/2);
                    }
    
                    //DEVUELVE CERVEZA
                    camarero.devolverCerveza();
                    System.out.println(nombre+" devuelve la cerveza a mou");
                    //ESPERAR ANTES DE PERDIR OTRA
                    sleep(400);
    
                } catch (Exception e) {}
            }
        }while(infinito);
    }
}

//-------------------------------------------------------------------//
//CLASE QUE CONTROLA UN TEMPORIZADOR
//-------------------------------------------------------------------//
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

//Clase main Ua2ex1

public class Ua2ex1 {
    public static void main(String[] args) {

        System.out.println("-----------------------------------");
        System.out.println("Hilos:      Bar de mou             ");
        System.out.println("-----------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        Camarero mou = new Camarero("mou");
        HilosClientes homer = new HilosClientes(mou,"homer");
        HilosClientes barney = new HilosClientes(mou,"barney");
        HilosClientes carl = new HilosClientes(mou,"carl");
        HilosClientes lenny = new HilosClientes(mou,"lenny");
        HilosClientes lurleen = new HilosClientes(mou,"lurleen");

        

        System.out.println("Comienza la ejecución de los hilos ...");
        timer.start();

        homer.start();
        barney.start();
        carl.start();
        lenny.start();
        lurleen.start();

        try {
            homer.join();
            barney.join();
            carl.join();
            lenny.join();
            lurleen.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        
        timer.stop();
        timer.printElapsedTime();
    }
}



