
import java.util.Random;

public class ua2tarea2 {
    public static void main(String args[]) {
        
        Cuenta c1 = new Cuenta(8000.0f); // Creacion objeto Cuenta
        System.out.println("\t- Saldo actual: " + c1.getSaldo()); // Visualizacion saldo actual/inicial

        // Creacion objetos Hilo
        Hilo h1 = new Hilo(c1, 900.0f, "David");
        Hilo h2 = new Hilo(c1, 200.0f, "Sandra");
        Hilo h3 = new Hilo(c1, 550.0f, "Mariana");
        Hilo h4 = new Hilo(c1, 750.0f, "Daniel");
        Hilo h5 = new Hilo(c1, 400.0f, "Adrian");
        Hilo h6 = new Hilo(c1, 150.0f, "Sara");
        
        // Lanzamiento hilos
        h1.start(); h2.start(); h3.start(); h4.start(); h5.start(); h6.start();
        
        // Incluir los hilos a la ejecucion del proceso principal
        try {h1.join(); h2.join(); h3.join(); h4.join(); h5.join(); h6.join();} catch (Exception e) {}
        
        c1.retirada(2570.0f, "David");
        
        System.out.println("\t- Saldo actual: " + c1.getSaldo()); // Visualizacion saldo actual/final
        
        
        
    }
}

class Cuenta {
    Random r = new Random();
    private static int ID_COUNT = 1;
    private int id;
    private float saldo;

    public Cuenta() {
        this.saldo = 0;
        this.id = Cuenta.ID_COUNT;
        increaseIdCount();
    }
    
    public Cuenta(float saldo) {
        this.saldo = saldo;
        this.id = Cuenta.ID_COUNT;
        increaseIdCount();
    }
    
    private void increaseIdCount(){
        Cuenta.ID_COUNT++;
    }
    
    public int getId(){
        return this.id;
    }
    
    private void setId(){
        this.id = Cuenta.ID_COUNT;
        increaseIdCount();
    }
    
    public synchronized float getSaldo() {
        try {
            Thread.sleep(r.nextInt(250,750)); }
        catch (Exception e) {}
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    private synchronized void addSaldo(float saldo){
        this.saldo+=saldo;
        try {
            Thread.sleep(r.nextInt(250,750)); }
        catch (Exception e) {}
    }
    
    private synchronized void substractSaldo(float saldo){
        this.saldo-=saldo;
        try {
            Thread.sleep(r.nextInt(250,750)); }
        catch (Exception e) {}
    }

    @Override
    public String toString(){
        return (
                "Cuenta Corriente ---------- Inicio\n" +
                "\t- Numero: " + getId() + "\n" +
                "\t- Saldo: " + getSaldo() + " EUR\n"
        );
    }
    
    public synchronized void ingreso(float cantidad, String autor){
        System.out.println("Cuenta Corriente ---------- Ingreso");
        System.out.println(
                "Ingresando " + cantidad + " EUR\n" +
                "\t- Saldo anterior: " + getSaldo() + " EUR"
        );
        addSaldo(cantidad);
        System.out.println(
                "\t- Saldo actual: " + getSaldo() + " EUR\n" +
                "\t- INGRESO efectuado por: " + autor + "\n"
        );
    }
    
    public synchronized void retirada(float cantidad, String autor) {
        System.out.println("Cuenta Corriente ---------- Retirada");
        System.out.println(
                "Retirando " + cantidad + " EUR\n" +
                "\t- Saldo anterior: " + getSaldo() + " EUR"
        );
        substractSaldo(cantidad);
        System.out.println(
                "\t- Saldo actual: " + getSaldo() + " EUR\n" +
                "\t- RETIRADA efectuada por: " + autor + "\n"
        );
    }
}

class Hilo extends Thread {
    Cuenta c;
    float cantidad;
    String autor;
    
    public Hilo(Cuenta c, float cantidad, String autor) {
        this.c = c;
        this.cantidad = cantidad;
        this.autor = autor;
    }
    
    public void run() {
        c.ingreso(cantidad, autor);
    }
}

/*
    He probado quitando los sleep y los synchronized
    y no difiere o altera el valor de las variables,
    sino el orden la visualizacion de los `sout`
    por pantalla.
*/