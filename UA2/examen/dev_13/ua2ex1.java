/**
 * @file               Ua2ex1
 *
 * @brief              Este fichero pretende simular el funcionamiento de un bar con un camarero y cinco clientes, que consumirán cerveza en tres vasos
 *            
 *                     
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief              Vaso de cerveza que gestionará el camarero y consumirá el cliente
 *
 
 *
 * @class              VasoCerveza
 */
class VasoCerveza{
    int id; /**< Identificador del vaso. Lo generará automáticamente el camarero*/
    int tipo;/**< 0 para media pinta o 1 para pinta. Se genera de forma aleatoria al generar el baso*/

    
    public VasoCerveza(int id) {
        this.id = id;
        this.tipo =(int) Math.round(Math.random());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
}

/**
 * @brief              Clase para gestionar los vasos
 *
 
 *
 * @class              Camarero
 */
class Camarero{
    ArrayList<VasoCerveza> listaVasos = new ArrayList<VasoCerveza>();/**< Lista de vasos disponibles*/
    String nombre;/**< Nombre del irremplazable Mou*/

    public Camarero(String nombre) {
        for(int i=0;i<3;i++){
        VasoCerveza vaso = new VasoCerveza(i+1);
        listaVasos.add(vaso);
        }
        
        this.nombre = nombre;
    }
    
    /**
 *@brief              Método para pasar los vasos disponibles.
 *
 *
 *@return             Devuelve el primer vaso disponible
 *
 */
    public synchronized VasoCerveza servirCerveza(){
        
        if(listaVasos.size()>0){
        VasoCerveza vaso = listaVasos.get(0);
        listaVasos.remove(0);
        
        return vaso;
        }else return null;
        
    }
    
    /**
 *@brief              Método que permite reinsertar vasos a la lista, de forma que se encuentren disponibles
 *
 */
    public synchronized void devolverCerveza(VasoCerveza vaso){
        listaVasos.add(vaso);
        System.out.println("Hay " +listaVasos.size()+" vasos disponibles");
    }
    /**
 *@brief              Muestra por pantalla el número de vasos disponibles
 *
 *
 */
    public synchronized  void contarVasos(){
        System.out.println("Hay "+ listaVasos.size()+" vasos disponibles");
    }
}

/**
 * @brief              Clientes que pedirán y consumirar la cerveza del camarero, siempre que haya algún vaso disponible
 *
 
 *
 * @class              HiloClientes
 */
class HiloClientes extends Thread{
    String nombre;/**< Nombre del cliente*/
    Camarero camarero;/**< Camareró que atenderá al clientes*/
    float totalBebido;/**< Suma total de los litros consumidos por el cliente*/
    VasoCerveza vasoUsado;/**< Vaso que el cliente está usando*/

    public HiloClientes(String nombre, Camarero camarero) {
        this.nombre = nombre;
        this.camarero = camarero;
    }
    
    /**
 *@brief              Pide una cerveza y en caso de obtenerla espera un tiempo, suma su contenido al total, devuelve al vaso al camarero y espera un tiempo aleatorio
 *
 *
 */
    public void run(){
        System.out.println(nombre+" ha llegado al bar");
        
        while(true){
            vasoUsado = camarero.servirCerveza();
            
            if(vasoUsado!=null){
              System.out.println(nombre+" pide una cerveza y Mou le consigue el vaso "+vasoUsado.getId());  
            try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            if(vasoUsado.getTipo()==0){
                System.out.println(nombre+" se ha bebido una media pinta");
                
                totalBebido+=0.284;
            }else{
                System.out.println(nombre+" se ha bebido una pinta");
                
                totalBebido+=0.568;
            }
            
            System.out.println(nombre+" le devuelve a Mou un vaso");    
            camarero.devolverCerveza(vasoUsado);
            
            System.out.println(nombre +" lleva bebidos "+ totalBebido+" litros");
            }else System.out.println(nombre+" pide una cerveza pero no hay vasos disponibles");
            
            try{
               Thread.sleep((int)Math.random()*1000+250);
            }catch(Exception e){}
            
        }
    }
}

public class ua2ex1 {

    public static void main(String[] args) {
        
        Camarero camarero = new Camarero("Mou");
        
        ArrayList<HiloClientes> clientes = new ArrayList<HiloClientes>();
        
        HiloClientes cliente1 = new HiloClientes("Homer", camarero);
        HiloClientes cliente2 = new HiloClientes("Barney", camarero);
        HiloClientes cliente3 = new HiloClientes("Carl", camarero);
        HiloClientes cliente4 = new HiloClientes("Lenny", camarero);
        HiloClientes cliente5 = new HiloClientes("Lurleen", camarero);
        
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);
        
        for(int i=0;i<clientes.size();i++){
            clientes.get(i).start();
        }   
    }
}
