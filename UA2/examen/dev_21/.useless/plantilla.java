
import java.util.Random;

public class ua2tarea2 {

    public static void main(String args[]) {
        RemesaCroquetas rc = new RemesaCroquetas(5);

        System.out.println(rc.toString());

        Hilo c1 = new Hilo(rc, "CoNsuMIdor 1", 4);
        Hilo p1 = new Hilo(rc, "PROductOR 1", 1);
        Hilo p2 = new Hilo(rc, "BarTender 5", 2);
        c1.start();
        p1.start();
        p2.start();
        try {
            c1.join();
            p1.join();
            p2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class RemesaCroquetas {

    // Variables
    private int cantidad;

    // Constructor
    public RemesaCroquetas() {
        this.cantidad = 0;
    }

    public RemesaCroquetas(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getters
    public synchronized int getCantidad() {
        return cantidad;
    }

    // Setters
    public synchronized void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Useful methods
    public synchronized void reponer(String nombre) {
        this.cantidad++;
        System.out.println(nombre + " produjo 1 croqueta");
        System.out.println("\t- " + toString());
        System.out.println();
    }

    public synchronized void reponer(String nombre, int cantidad) {
        this.cantidad += cantidad;
        System.out.println(nombre + " produjo " + cantidad + " croquetas");
        System.out.println("\t- " + toString());
        System.out.println();
    }

    public synchronized void consumir(String nombre) {
        if (this.cantidad > 0 && (this.cantidad - cantidad) >= 0) {
            this.cantidad--;
            System.out.println(nombre + " consumió 1 croqueta");
            System.out.println("\t- " + toString());
            System.out.println();
        } else {
            System.out.println("No hay existencias de croquetas para consumir");
        }

    }

    public synchronized void consumir(String nombre, int cantidad) {
        if (this.cantidad > 0 && (this.cantidad - cantidad) >= 0) {
            this.cantidad -= cantidad;
            System.out.println(nombre + " consumió " + cantidad + " croquetas");
            System.out.println("\t- " + toString());
        } else {
            System.out.println("No hay existencias de croquetas para consumir");
            System.out.println("\t- Quiso consumir " + cantidad + " croqueta(s)");
            System.out.println();
        }
    }

    // ToString
    @Override
    public String toString() {
        return "Existencias remesa: " + this.cantidad + '\n';
    }
}

class Hilo extends Thread {

    // Variables
    Random r = new Random();

    RemesaCroquetas rc;
    String nombre;
    int role;
    int acciones;

    // Constructors
    public Hilo(RemesaCroquetas rc, String nombre, int acciones) {
        this.rc = rc;
        this.acciones = acciones;
        setNombre(nombre);
        setRole();
    }

    // Getters
    public String getNombre() {
        return this.nombre;
    }

    public int getRole() {
        return this.role;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public void setRole() {
        this.role = this.nombre.contains("PRODUCTOR") ? 0 : this.nombre.contains("CONSUMIDOR") ? 1 : -1;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.acciones; i++) {
            switch (getRole()) {
                case 0: {
                    if (r.nextInt() > 1) {
                        this.rc.reponer(getNombre(), r.nextInt(1, 10));
                    } else {
                        this.rc.consumir(getNombre());
                    }
                }
                break;
                case 1: {
                    if (r.nextInt() > 1) {
                        this.rc.consumir(getNombre(), r.nextInt(1, 10));
                    } else {
                        this.rc.consumir(getNombre());
                    }
                }
                break;
                default: {
                    System.out.println("Oops! Hay alguien que no deberia estar aqui: " + this.getNombre() + ", role: " + getRole());
                    System.out.println();
                }
            }
            try {
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
