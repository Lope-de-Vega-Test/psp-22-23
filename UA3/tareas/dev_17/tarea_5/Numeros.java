import java.io.Serializable;

public class Numeros implements Serializable {
    private int entero;
    private long cuadrado;
    private long cubo;

    public Numeros() {
    }

    public Numeros(int entero) {
        this.entero = entero;
    }

    public int getEntero() {
        return entero;
    }

    public void setEntero(int entero) {
        this.entero = entero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}
