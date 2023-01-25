package UA3.tareas.dev_8.tarea5;

public class Numeros {

    int numeroBase;
    long cuadrado, cubo;

    public Numeros(int numeroBase, long cuadrado, long cubo) {
        this.numeroBase = numeroBase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public Numeros() {
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
