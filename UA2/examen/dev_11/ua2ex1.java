import java.util.ArrayList;

class vasoCerveza{
    private int id = 0;
    private int tipo = 0;
    private boolean disponible = false;
    
    public vasoCerveza(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    } 

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "vasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }

}

class Camarero{

    //private int[] listaVasos = new int[3];
    private ArrayList<vasoCerveza> listavasos = new ArrayList();
    
    //listavasos.add(new vasoCerveza(0, 1, false)); No sabia como añadir longitud al arraylist 
    
    String nombreCamarero;
   
    public Camarero(String nombreCamarero) {
        
        this.nombreCamarero = nombreCamarero;
        for (int i = 0 ; i < 3 ; i++){
            listavasos.get(i).setId(i);
        }
    }
    
    public synchronized void servirCerveza(){
             
        if(listavasos.get(0).isDisponible() == true){
            listavasos.get(0).setDisponible(false);
        }
        if(listavasos.get(1).isDisponible() == true){
            listavasos.get(1).setDisponible(false);
        }     
        if(listavasos.get(2).isDisponible() == true){
            listavasos.get(2).setDisponible(false);
        }
        
        System.out.println("Se ha servido un vaso");
    }
    
    public synchronized void devolverCerveza(){
        
        if(listavasos.get(0).isDisponible() == false){
            listavasos.get(0).setDisponible(true);
        }
        if(listavasos.get(1).isDisponible() == false){
            listavasos.get(1).setDisponible(true);
        }     
        if(listavasos.get(2).isDisponible() == false){
            listavasos.get(2).setDisponible(true);
        }
        
        System.out.println("Se ha devuelto un vaso");
    }
    
    public synchronized void contarVaso(){
        
        int contador = 0;
        
        if(listavasos.get(0).isDisponible() == true){
            contador++;
        }
        if(listavasos.get(1).isDisponible() == true){
            contador++;
        }
        if(listavasos.get(2).isDisponible() == true){
            contador++;
        }
        
        System.out.println("Cantidad de vasos disponibles: " + contador);
    }
      
}

class HilosClientes extends Thread{
    
    String nombreCliente;

    public HilosClientes(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
    public void run(){
        
        Camarero camarero = null;
        int aux = 1;
        
        while (aux != 0){
            
            
            try{
                //Dormimos el hilo el tiempo indicado por el ejercicio
                Thread.sleep((int) Math.random()*(1000)+250);
            }catch(InterruptedException e){
                System.out.println("Ha ocurrido un error durmiendo el hilo");
            }
    
        }
        
    }
}



public class ua2ex1 {
    public static void main(String[] args) {
        
        Camarero camarero = new Camarero("Mou");
        HilosClientes  cliente1 = new HilosClientes ("Homer");
        HilosClientes  cliente2 = new HilosClientes ("Barney");
        HilosClientes  cliente3 = new HilosClientes ("Carl");
        HilosClientes  cliente4 = new HilosClientes ("Lenny");
        HilosClientes  cliente5 = new HilosClientes ("Lurleen");
        
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();
        
        try {

            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();

        }

        catch (InterruptedException e)
        {

        }
    }
}
