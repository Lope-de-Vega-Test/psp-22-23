
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 *
 * @author Irene Alba Posadas
 */

class VasoCerveza{
    int id;
    int tipo;
    boolean disponible;
    
    public VasoCerveza(int id, boolean disponible){
        this.id = id;
        this.disponible = disponible;
        tipo = (int) (Math.random() * 1 + 0);
    }

    public int getId() {
        return id;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
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
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
    
    
}

class Camarero{
    private int nVasos = 3;
    private VasoCerveza[] listaVasos = new VasoCerveza[nVasos];
    private String nombre;
    
    public Camarero(String nombre){
        nVasos = 3;
        for(int i = 0; i < nVasos; i++){
            listaVasos[i] = new VasoCerveza(i, true);            
        }
        
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public synchronized int servirCerveza(String nombre){
        while (nVasos == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                //Nothing...
            }
        }
        
        int vaso = (int) (Math.random() * 2 + 0);
        int tipoCerveza = listaVasos[vaso].getTipo();
        System.out.println("Aquí tienes tu cerveza " + nombre + ".");
        listaVasos[vaso].setDisponible(false);
        nVasos--;
        notify();
        
        return tipoCerveza;
    }
    
    public synchronized void recogerVaso(String nombre){
        
        while (nVasos == 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                //Nothing...
            }
        }
        System.out.println("*" + nombre + " me ha devuelto el vaso*");
        int vaso = 0;
        
        if((!listaVasos[0].isDisponible() && listaVasos[1].isDisponible() && listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && !listaVasos[2].isDisponible())){
            vaso = 0;
            
        }else{
            if ((listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && !listaVasos[2].isDisponible())) {
                vaso = 1;
                
            }else{
                if ((listaVasos[0].isDisponible() && listaVasos[1].isDisponible() && !listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && listaVasos[1].isDisponible() && !listaVasos[2].isDisponible()) || (!listaVasos[0].isDisponible() && !listaVasos[1].isDisponible() && !listaVasos[2].isDisponible())) {
                    vaso = 2;
                    
                }
            }
        }
        
        listaVasos[vaso].setDisponible(true);
        int nuevoTipo = (int) (Math.random() * 1 + 0);
        listaVasos[vaso].setTipo(nuevoTipo);
        nVasos++;
        notify();
        
    }
    
    public synchronized void contarVasos(){
        if(nVasos == 0){
            System.out.println("No hay vasos disponibles.");
        }else{
            if(nVasos == 1){
                System.out.println("Hay un vaso disponible.");
            }else{
                System.out.println("Hay " + nVasos + " vasos disponibles.");
            }
        }
    }
}

class HilosClientes extends Thread{
    private Camarero camarero;
    private double cantBebida;
    
    public HilosClientes(Camarero camarero, String nombre){
        this.camarero = camarero;
        setName(nombre);
    }
    
    public void run(){
        int i = 3;
        do{
            System.out.println("Hola " + camarero.getNombre() + " soy " + getName() + ", ponme una cerveza.");
            int tipo = camarero.servirCerveza(getName());
            String nomTipo = "";  
            camarero.contarVasos();

            if (tipo == 1) {
                cantBebida += 0.568;
                nomTipo = " una pinta";
            } else {
                if(tipo == 0){
                    cantBebida += 0.568 / 2;
                    nomTipo = "media pinta";
                }
            }
            
            System.out.println("*" + getName() + " bebe " + nomTipo + " de cerveza*");

            System.out.println("*" + getName() + " lleva " + cantBebida + " litros de cerveza consumidos*");
            camarero.recogerVaso(getName());
            camarero.contarVasos();

            try {
                int t = 1000;
                sleep(t);
            } catch (InterruptedException e) {
                //Nothing...
            }
            
            i--;
        }while(i != 0);
    }
}

public class ua2ex1 {
    public static void main(String[] args) {
        Camarero camarero = new Camarero("Moe");
        
        HilosClientes cliente1 = new HilosClientes(camarero, "Homer");        
        HilosClientes cliente2 = new HilosClientes(camarero, "Barney");
        HilosClientes cliente3 = new HilosClientes(camarero, "Carl");
        HilosClientes cliente4 = new HilosClientes(camarero, "Lenny");
        HilosClientes cliente5 = new HilosClientes(camarero, "Lurleen");
        
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
        } catch (InterruptedException e) {
            //Nothing...
        }
    }
}
