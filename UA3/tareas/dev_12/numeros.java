

public class numeros {
    int base;
    long cuadrado;
    long cubo;

    public numeros(int numBase, long cuadrado, long cubo) {
        this.base = numBase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }
    
    public numeros() {
        base = 0;
        cuadrado = 0;
        cubo = 0;
    }

    public int getBase() {
        return base;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setBase(int numBase) {
        this.base = numBase;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "Numeros{" + "numBase=" + base + ", cuadrado=" + cuadrado + ", cubo=" + cubo + '}';
    }
    
}