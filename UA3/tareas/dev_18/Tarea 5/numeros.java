public class Numeros {
    
    int numerobase;
    long cuadrado;
    long cubo;

    public Numeros(int numerobase, long cuadrado, long cubo) {
      
        this.numerobase = numerobase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
      
    }
    
    public numeros() {
      
        numerobase = 0;
        cuadrado  =0;
        cubo = 0;
      
    }

    public void setNumerobase(int numerobase) {
      
        this.numerobase = numerobase;
      
    }

    public void setCuadrado(long cuadrado) {
      
        this.cuadrado = cuadrado;
      
    }

    public void setCubo(long cubo) {
      
        this.cubo = cubo;
      
    }

    public int getNumerobase() {
      
        return numerobase;
      
    }

    public long getCuadrado() {
      
        return cuadrado;
      
    }

    public long getCubo() {
      
        return cubo;
      
    }

    @Override
    public String toString() {
      
        return "numeros{"+"numeroBase="+numerobase+",cuadrado="+cuadrado+",cubo="+cubo+'}';
      
    }
    
}
