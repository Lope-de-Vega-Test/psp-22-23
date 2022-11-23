import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

class VasoCerveza{

    int id =0;
    int tipo =0; 
}


public VasoCerveza(int id, int tipo){

    this.id=id;
    this.tipo=tipo;

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
    this.tipo = tipo;
}

@Override
public String toString() {
    return "VasoCerveza [id=" + id + ", tipo=" + tipo + "]";
}

class Camarero{

    ArrayList Listadevasos = new ArrayList<VasoCerveza>();

    public Camarero(){

        this.Listadevasos=new ArrayList<VasoCerveza>();

        VasoCerveza vaso1 = new VasoCerveza(id, tipo);
        VasoCerveza vaso2 = new VasoCerveza(id, tipo);
        VasoCerveza vaso3 = new VasoCerveza(id, tipo);
        
        Listadevasos.add(vaso1);
        Listadevasos.add(vaso2);
        Listadevasos.add(vaso3);

        public synchronized int get() {
            boolean disponible;
            while(!disponible) {        
                try {
                    wait();             
                } catch(InterruptedException e) {}            
            }
            int numero;
            System.out.println( "Se consume: " + numero);
            disponible = false; 
            notify();           
            return numero;       
        }
    }

    public void servirCerveza(){
    
        for (int i=1; i<Listadevasos.size(); i++){
        System.out.println(Listadevasos.get(i));
        }

    }

}

public void devolverCerveza(){}

class HilosClientes extends Thread{

    String nombre;
    public HilosClientes(String nombre){
        this.nombre=nombre;
    }

}



public class ua2ex1 {
    public static void main(String[] args) {
        
    }
}


    

