/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_ua3_tarea5_server;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ignacio
 */

public class PSP_UA3_Tarea5_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        long cuad, cub;
        int numeroPuerto = 6000;// Puerto
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        System.out.println("Esperando al cliente.....");
        Socket cliente = servidor.accept();

        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        Numeros numero = (Numeros) inObjeto.readObject();

        cuad = (long) Math.pow(numero.getEntero(), 2);
        cub = (long) Math.pow(numero.getEntero(), 3);

        numero.setCuadrado(cuad);
        numero.setCubo(cub);

        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
        // Se prepara un objeto y se envia
        outObjeto.writeObject(numero); //enviando objeto

        // CERRAR STREAMS Y SOCKETS
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }

}

class Numeros implements Serializable {

    int entero;
    long cuadrado;
    long cubo;

    public Numeros() {
    }

    public Numeros(int entero, long cuadrado, long cubo) {
        this.entero = entero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public int getEntero() {
        return entero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setEntero(int entero) {
        this.entero = entero;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}
