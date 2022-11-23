/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ua2ex1;

/**
 *
 * @author JRC
 */
public class Ua2ex1 {

       
  public static void main(String[] args) {  
    Hilocliente hilo = new Hilocliente();
	
    VasoCerveza VC = new VasoCerveza(hilo, 1);	
	Camarero C = new Camarero(hilo, 1);
	
    VC.start();
	C.start();

  }

    static class Hilocliente {

        public Hilocliente() {
        }
    }
}
  
class VasoCerveza {

    VasoCerveza(ua2ex1.Hilocliente hilo) {
    }


    VasoCerveza(Ua2ex1.Hilocliente hilo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
class Camarero {

    Camarero(Ua2ex1.Hilocliente hilo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
    
public class Hilocliente extends Thread{
    
      private hilo hilo;
    private int n;

    public Hilocliente (hilo h, int n){
        hilo = h;
        this.n = n;
    }

      @Override
    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = hilo.get(); //recoge el número
            System.out.println(i + "=>Cliente: " + n
                               + ", consume: " + valor);
        }
    }

    private int get() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

    public class hilo {
    private int numero;
    private boolean disponible = false;//inicialmente cola vacia

    public int get() {
	    if(disponible) {      //hay número en la cola
		disponible = false; //se pone cola vacía
            return numero;      //se devuelve
	    }
          return -1;	//no hay número disponible, cola vacía	
    }

    public void put(int valor) {
        numero = valor;    //coloca valor en la cola 
        disponible = true; //disponible para consumir, cola llena	
    }
}

    

