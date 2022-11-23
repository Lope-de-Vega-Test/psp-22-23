import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.midi.Soundbank;
import javax.sound.sampled.SourceDataLine;

//Clase que almacena los vasos
class VasoCerveza{
int id=0;
int tipo=0;

//Cosntructor de la clase
public VasoCerveza(int id, int tipo) {
    this.id = id;
    this.tipo =(int) Math.round(Math.random());
}
//Getter y Setter el id
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
//Getter y Setter del tipo
public int getTipo() {
    return tipo;
}
public void setTipo(int tipo) {
    this.tipo = tipo;
}
//toString que muestra los datos
@Override
public String toString() {
    return "VasoCerveza [id=" + id + ", tipo=" + tipo + "]";
}
}
//Clase camarero
class Camarero{

String nombre="";
//Lista de los vasos
 ArrayList listadevasos=new ArrayList<VasoCerveza>();

//Contructor de camarero donde añadimos vasos a la lista
 public Camarero() {
     VasoCerveza vaso1= new VasoCerveza(1, 0);
     VasoCerveza vaso2= new VasoCerveza(2, 1);
     VasoCerveza vaso3= new VasoCerveza(3, 0);
    this.listadevasos= new ArrayList<VasoCerveza>();

    listadevasos.add(vaso1);
    listadevasos.add(vaso2);
    listadevasos.add(vaso3);
}
//Getter y Setter del nombre
public String getNombre() {
    return nombre;
}


public void setNombre(String nombre) {
    this.nombre = nombre;
}

//Funcion de tipo VasoCerveza que va a servir una cerveza sacandolo de la lista de vasos
public VasoCerveza servirCerveza(){
    return (VasoCerveza) listadevasos.get(0);
}

//Funcion que devuelve los vasos a la lista 
public void devolverCerveza(){

}
//Getter y Setter de la lista de vasos
public ArrayList getListadevasos() {
    return listadevasos;
}
public void setListadevasos(ArrayList listadevasos) {
    this.listadevasos = listadevasos;
}
}

//Clase HiloCliente que creara los hilos
class HiloCliente extends Thread{
    String nombre;
    Camarero camarero;

     //Contructor de HiloCliente
     public HiloCliente(String nombre,Camarero camarero) {
        this.nombre=nombre;
        this.camarero=camarero;
    }
    //Getter y Setter de camarero
    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }
   
    //Getter y Setter de nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        //Funcion run que pondra los hilos en marcha
    public void run(){
        while(true){
            camarero.servirCerveza();
        
            camarero.devolverCerveza();
             try{
                 Thread.sleep(250);
             }
             catch(Exception e){
             }
        }
     }

}

//Clase principal
public class ua2ex1 {
    public static void main(String[] args) {
      
        Camarero camarero=new Camarero();
        camarero.servirCerveza();
        
    //Creacion de los hilos pasandole el nombre del cliente y el camarero
        HiloCliente cliente1=new HiloCliente("Hommer",camarero);
        HiloCliente cliente2=new HiloCliente("Barney",camarero);
        HiloCliente cliente3=new HiloCliente("Carl",camarero);
        HiloCliente cliente4=new HiloCliente("Lenny",camarero);
        HiloCliente cliente5=new HiloCliente("Lurleen",camarero);

    //Comienzan los hilos a ejecutarse
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();

    }
    
}
