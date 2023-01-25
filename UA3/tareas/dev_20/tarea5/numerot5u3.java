package clienteej5;

/**
 *
 * @author Lucía Luna
 */

public class numerot5 {
    int num;
    long numcuadrado;
    long numcubo;

    public numerot5(int num, long numcuadrado, long numcubo) {
        this.num = num;
        this.numcuadrado = numcuadrado;
        this.numcubo = numcubo;
    }
    
    public numerot5() {
        num = 0;
        numcuadrado = 0;
        numcubo = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getNumcuadrado() {
        return numcuadrado;
    }

    public void setNumcuadrado(long numcuadrado) {
        this.numcuadrado = numcuadrado;
    }

    public long getNumcubo() {
        return numcubo;
    }

    public void setNumcubo(long numcubo) {
        this.numcubo = numcubo;
    }

    @Override
    public String toString() {
        return "Numeros [num=" + num + ", numcuadrado=" + numcuadrado + ", numcubo=" + numcubo + "]";
    }

   
}
