/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package psp_ua3_tarea5;

import java.io.*;
import java.net.*;
import java.util.Scanner;


/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea5 {

    /* Crea una clase Java llamada Numeros que defina 3 atributos: uno entero,
    otros dos de tipo long. Crea un constructor con parámetros y otro vacío.
    Define los getters y setters y crea un programa cliente que introduzca por
    teclado el atributo entero.

    Después, envía este objeto a un proceso servidor para que calcule el cuadrado y
    el cubo del número y envíe el objeto de vuelta al cliente con los cálculos
    realizados, almacenándolos en los atributos oportunos.

    Deberás controlar todos los errores posibles (que el servidor no esté iniciado,
    que se produzcan excepciones o cortes abruptos del cliente o servidor, que
    el número introducido por pantalla por el clientea sea menor que 0, etcétera). */
    public static void main(String[] args) throws Exception, ClassNotFoundException {
        Scanner entrada = new Scanner(System.in);

        int num;

        Numeros numero = new Numeros();

        System.out.println("Introduce un numero");
        num = entrada.nextInt();
        numero.setEntero(num);

        String Host = "localhost";
        int Puerto = 6000;//puerto remoto

        Socket cliente = new Socket(Host, Puerto);

        //FLUJO DE salida para objetos
        ObjectOutputStream numSal = new ObjectOutputStream(cliente.getOutputStream());

        // Se envia el objeto
        numSal.writeObject(numero);

        ObjectInputStream numEnt = new ObjectInputStream(cliente.getInputStream());
        //Se recibe un objeto
        Numeros recibido = (Numeros) numEnt.readObject();//recibo objeto
        numero.setCuadrado(recibido.getCuadrado());
        numero.setCubo(recibido.getCubo());

        // CERRAR STREAMS Y SOCKETS
        numEnt.close();
        numSal.close();
        cliente.close();
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
