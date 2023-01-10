
package ua3tarea5cliente;


public class numeros {
    
    int numeroBase;
    long cuadrado,cubo;

    public numeros(int numeroBase, long cuadrado, long cubo) {
        this.numeroBase = numeroBase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }
    
    public numeros() {
        numeroBase = 0;
        cuadrado = 0;
        cubo = 0;
    }

    public void setNumeroBase(int numeroBase) {
        this.numeroBase = numeroBase;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    public int getNumeroBase() {
        return numeroBase;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    @Override
    public String toString() {
        return "numeros{" + "numeroBase=" + numeroBase + ", cuadrado=" + cuadrado + ", cubo=" + cubo + '}';
    }
    
}
