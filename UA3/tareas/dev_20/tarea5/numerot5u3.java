package clientet5u3;

/**
 *
 * @author Lucía Luna
 */
public class numerot5u3 {
    int base;
    long cuadrado,cubo;
    
    
       public numerot5u3() {
           
       }
    
        public numerot5u3(int base, long cuadrado, long cubo) {
            this.base = base;
            this.cuadrado = cuadrado;
            this.cubo = cubo;
        }
        
      //Creamos los setters
    public void setBase(int base) {
        this.base = base;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
        
    
    public int getBase() {
        return base;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    //Creamos los getters
    public long getCubo() {
        return cubo;
    }

    @Override
    public String toString() {
        return "numeros{" + "\n Base   " + base + "\n Cuadrado  " + cuadrado + "\n Cubo  " + cubo + '}';
    }
        
       
}
