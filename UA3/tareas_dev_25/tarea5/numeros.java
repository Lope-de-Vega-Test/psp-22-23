/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea5;

/**
 *
 * @author RafaelRomero
 */

public class numeros {
    int numero;
        long cubo;
        long cuadrado;
        public numeros(){
                
        }
        public numeros(int numero, long cuadrado, long cubo){
                this.numero=numero;
                this.cuadrado=cuadrado;
                this.cubo=cubo;
        }
        public int getNumero() {
                return numero;
        }
        public void setNumero(int numero) {
                this.numero = numero;
        }
        public long getCubo() {
                return cubo;
        }
        public void setCubo(long cubo) {
                this.cubo = cubo;
        }
        public long getCuadrado() {
                return cuadrado;
        }
        public void setCuadrado(long cuadrado) {
                this.cuadrado = cuadrado;
        }
        @Override
        public String toString() {
                return "Numeros [numero=" + numero + ", cubo=" + cubo + ", cuadrado=" + cuadrado + "]";
        }
        
}
