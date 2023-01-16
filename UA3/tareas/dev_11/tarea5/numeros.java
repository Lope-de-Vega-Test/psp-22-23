/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete1;

/**
 *
 * @author damik
 */
public class numeros {
    
    int base;
    long cuadrado;
    long cubo;

    public numeros(int numeroBase, long cuadrado, long cubo) {
        this.base = numeroBase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }
    
    public numeros() {
        base = 0;
        cuadrado = 0;
        cubo = 0;
    }

    public void setNumeroBase(int numeroBase) {
        this.base = numeroBase;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    public int getNumeroBase() {
        return base;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    @Override
    public String toString() {
        return "numeros{" + "numeroBase=" + base + ", cuadrado=" + cuadrado + ", cubo=" + cubo + '}';
    }
    
}
