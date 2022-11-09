package bloquesnosincronizados;
/**
 *
 * @author Lucía Luna
 */
class HiloSumador implements Runnable 
   {
    private Contador contador;

    public HiloSumador (String nombre, Contador c){synchronized (this){
        
        setName(nombre);
        contador = c;
    }
    }

    public void run() {
        for(int j=0; j<1000;j++) 
        {
            contador.incrementa();
            
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }

    private void setName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   } // Fin Class HiloSumador
   

//clase contador
package bloquesnosincronizados;
/**
 *
 * @author Lucía Luna
 */
class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c++;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
    
    
} 
