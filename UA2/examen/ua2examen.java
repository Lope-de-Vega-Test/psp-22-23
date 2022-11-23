package paquete;

import java.util.ArrayList;
import java.math.*;

class VasoCerveza{
     int id;  // id del vaso
     int tipo; // tipo del vaso

    public VasoCerveza(int id, int tipo) {  //constructor, getter y setter
        this.id = id;
        this.tipo = tipo;
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
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
        

}

class Camarero{  //clase camarero y su lista
String nombrecamarero;
VasoCerveza vaso;
VasoCerveza vaso2;
VasoCerveza vaso3;
static ArrayList<Camarero> ListaVasos = new ArrayList();


public Camarero(String nombrecamarero){ //constructor y funciones
    this.nombrecamarero=nombrecamarero;
    }

    public class servirCerveza{

    }
    public class devolverCerveza{
        
    }
    public class contarVasos{
        
    }


}

class HiloClientes extends Thread //clase hilo
{
    private VasoCerveza vaso;
    private int n;

    public HiloClientes(VasoCerveza v, int n) {
        vaso = v;
        this.n = n;
    }

    public void run() { 
        int valor = 0;       
        for(int i=0; i<3;i++)
        {
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
}


public class ua2examen{  //clase principal
    public static void main(String[] args) {
        new Camarero("Moe");  //creando a Moe
    }
}










/*

 */