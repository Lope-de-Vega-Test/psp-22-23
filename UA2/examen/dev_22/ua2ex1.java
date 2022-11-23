/**
 * @mainpage                    Examen UA2
 *                                          
 */
package ua2ex1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ignacio
 */

class VasoCerveza {
    int id;/**< a partir de 0*/
    int tipo;/**< 0 - media pinta; 1 - una pinta*/

    /**
    *@brief              Constructor
    *
    *@param              id 
    *@param              tipo
    *
    */
    public VasoCerveza(int id, int tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    /**
    *@brief              Id getter
    * 
    *@return             Return id
    */
    public int getId() {
        return id;
    }

    /**
    *@brief              tipo getter
    *
    *@return             tipo
    */
    public int getTipo() {
        return tipo;
    }

    /**
    *@brief              Id setter
    */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
    *@brief              Tipo setter
    */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
    *@brief              Class toString
    *@return             class string
    */
    @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", tipo=" + tipo + '}';
    }
    
}

class Camarero {
    String name;
    ArrayList<VasoCerveza> listaVasos = new ArrayList<VasoCerveza>();/**< ArrayList de objetos VasoCerveza*/

    /**
    *@brief              Object constructor
    *
    *                    Recibe el nombre del camarero, genera tres objetos
    *                    VasoCerveza de media o una pinta (0 - 1) y los añade a
    *                    la lista
    *
    *@param              name
    *
    */
    public Camarero(String name) {
        VasoCerveza vaso1 = new VasoCerveza(0,(int) (Math.random()*2));
        VasoCerveza vaso2 = new VasoCerveza(1,(int) (Math.random()*2));
        VasoCerveza vaso3 = new VasoCerveza(2,(int) (Math.random()*2));
        
        this.name = name;
        listaVasos.add(vaso1);
        listaVasos.add(vaso2);
        listaVasos.add(vaso3);
    }
    
    /**
    *@brief              Devuelve vaso a consumidor
    *
    *                    No hará nada mientras no tenga vasos que servir, espera a
    *                    recibir vasos y si tiene al menos uno entonces elige uno al
    *                    azar, crea una copia, remueve el original del arraylist y
    *                    devuelve la copia
    *
    *@param              name in the method’s definition
    *
    *@return             comanda
    */
    public synchronized VasoCerveza servirCerveza(String name){
        while(listaVasos.size()==0){
            System.out.println(name+" espera... (no hay vasos aún)");
            try{
                wait();
            }catch(Exception e){
                
            }
        }
        int n = (int) (Math.random()*listaVasos.size());
        
        VasoCerveza comanda = listaVasos.get(n);
        listaVasos.remove(n);
        
        return comanda;
    }
    
    /**
    *@brief              recupera vaso
    *
    *                    Una vez el consumidor acaba, este devuelve el vaso
    *                    (se vuelve a añadir a la lista), pero primero elige
    *                    media o una pinta, para que no sean siempre la misma
    *                    que se generó en el constructor. Notifica a los
    *                    consumidores a la espera
    *
    *@param              devuelta in the method’s definition
    */
    public synchronized void devolverCerveza(VasoCerveza devuelta){
        devuelta.setTipo((int) (Math.random()*2));
        listaVasos.add(devuelta);
        notify();
    }
    /**
    *@brief              Cuenta vasos
    *
    *                    Cuenta el nº de vasos, devolviendo como int el tamaño de
    *                    la lista
    *
    *@return             listaVasos.size()
    */
    public void contarVasos(){
        System.out.println("Vasos disponibles: "+listaVasos.size());
    }
}

class HiloCliente extends Thread{
    String name;
    Camarero camarero;/**< Objeto compartido por todos los hilos*/

    public HiloCliente(String name, Camarero camarero) {
        this.name = name;
        this.camarero = camarero;
    }
    
    /**
    *@brief              Thread run
    *
    *                    Inicia el hilo. Pide una cerveza a través del camarero
    *                    sincronizado, aunque esperará si no hay y hasta que haya,
    *                    y añade al contador la cerveza que bebe (tomamos una
    *                    pinta como 568ml), lo muestra y devuelve el vaso. Espera
    *                    un tiempo al azar entre un cuarto de segundo 1 un segundo
    *                    y vuelta a empezar
    *
    */
    public void run(){
        System.out.println(this.name+" se sienta en la barra (comienza el hilo)");
        float litros = 0;
        
        while(true){
            System.out.println(this.name+" pide una cerveza");
            
            VasoCerveza pide = camarero.servirCerveza(name);
             
            System.out.println(this.name+" recibe la cerveza y la bebe gustosamente...");
            
            if(pide.tipo==0){
                litros+= (float) 0.284;
            }else{
                litros+=(float) 0.568;
            }
            
            System.out.println(this.name+" se la termina y lleva "+litros+" litros de cerveza. Devuelve el vaso");
            
            camarero.devolverCerveza(pide);
            
            System.out.println(this.name+" espera para seguir pidiendo");
            
            
            try {
                sleep(250 + ((int) Math.random() * 1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}

public class Ua2ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Camarero camarero = new Camarero("Moe"); /**< Declaramos el camarero (pseudoproductor/cola) */
        
        HiloCliente homer = new HiloCliente("Homer", camarero); /**< Creamos los hilos*/
        HiloCliente barney = new HiloCliente("Barney", camarero);
        HiloCliente lenny = new HiloCliente("Lenny", camarero);
        HiloCliente carl = new HiloCliente("Carl", camarero);
        HiloCliente lurleen = new HiloCliente("Lurleen", camarero);
        
        homer.start();/**< Comienzan los hilos*/
        barney.start();
        lenny.start();
        carl.start();
        lurleen.start();

        try {
            homer.join();/**< Se hace join de los hilos*/
            barney.join();/**< Nunca van a acabar los procesos porque están en bucles infinitos*/
            lenny.join();
            carl.join();
            lurleen.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }
        
    }
    
}
