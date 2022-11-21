package tarea2guille;

import java.util.ArrayList;

// Creamos la clase cuentacorriente
class cuentacorriente {

    int saldo;

// agregaremos getsaldo, setsaldo y agregarsaldo
    public cuentacorriente(int saldo) {
        this.saldo = saldo;

    }

    public synchronized int getsaldo() {
        try {
            Thread.sleep((int) (Math.random() * (1900) + 250));
        } catch (Exception e) {
        }
        return saldo;
    }

    public synchronized void setsaldo(int saldo) {

        try {
            Thread.sleep((int) (Math.random() * (1900) + 250));
        } catch (Exception e) {
        }
        this.saldo = saldo;
    }

    public synchronized void agregarsaldo(int anadir, String nombre) {

        System.out.println("La cuenta tenia un saldo de: " + this.saldo);
        this.saldo += anadir;

        System.out.println("El nuevo saldo es: " + this.saldo);

        System.out.println("------------------------------");

        System.out.println("Autor de la operación: " + nombre);
    }

}

// procedemos a crear la clase usuario
class usuario extends Thread {

    String nombre;
    int saldo;
    cuentacorriente cuenta;

    public usuario(String nombre, int saldo, cuentacorriente cuenta) {

        this.nombre = nombre;
        this.saldo = saldo;
        this.cuenta = cuenta;
    }

    public void run() {
        cuenta.agregarsaldo(saldo, nombre);
    }

}

public class Tarea2guille {

    public static void main(String[] args) {

        cuentacorriente cuenta = new cuentacorriente(1000);
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {

            int saldo = (int) (Math.random() * (160) + 50);
            usuario user = new usuario("Hilo " + i, saldo * i, cuenta);
            hilos.add(new Thread(user));
        }

        // Inicialización hilos
        for (int i = 0; i < hilos.size(); i++) {
            hilos.get(i).start();
        }

        // Finalización ejecución hilos
        for (int i = 0; i < hilos.size(); i++) {
            try {
                hilos.get(i).join();
            } catch (Exception e) {

            }

        }

        System.out.println("TOTAL: " + cuenta.getsaldo());

    }
}
