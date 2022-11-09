
class CuentaCorriente {
    private int saldo = 0;

    /**
     * Constructor de la clase
     * @param saldo
     */
    CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    /**
     * getter de saldo de la cuenta
     * @return
     */
    public synchronized int getSaldo() {
        dormir();
        return saldo;
    }

    /**
     * setter de saldo de la cuenta
     * @param saldo
     */
    public synchronized void setSaldo(int saldo) {
        dormir();
        this.saldo = saldo;
    }

    /**
     * Este método ingresará dinero en la cuenta
     * @param ingreso
     */
    public synchronized void ingreso(int ingreso) {
        dormir();
        this.saldo += ingreso;
        System.out.println("su saldo es de: " + this.saldo);
    }

    /**
     * Este método dormir aleatoriamente el hielo
     */
    public synchronized void dormir() {
        int n = (int) ((Math.random() * ((2500 - 2000) + 1)) + 2000);
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Hilo extends Thread {
    private CuentaCorriente cuentaCorriente;

    /**
     * Esta método creará un hilo con un nombre y cuenta corriente
     * 
     * @param nombre
     * @param c
     */
    public Hilo(String nombre, CuentaCorriente c) {
        setName(nombre);
        cuentaCorriente = c;
    }

    /**
     * Este método será el encargado de llamar a la función ingreso y hacer que se ejecute
     */
    public void run() {
        cuentaCorriente.ingreso(100);
    }
}

public class ua2tarea1fr1 {

    public static void main(String[] args) {

        System.out.println("----------------------------");
        System.out.println("Hilos: Metodos Sincronizados");
        System.out.println("----------------------------");

        // Establecemos saldo inicial
        int saldoInicial = 500;
        CuentaCorriente cuentaCorriente = new CuentaCorriente(saldoInicial);

        // Creamos los hilos
        Hilo hilo1 = new Hilo("papa", cuentaCorriente);
        Hilo hilo2 = new Hilo("mama", cuentaCorriente);
        Hilo hilo3 = new Hilo("hermano1", cuentaCorriente);
        Hilo hilo4 = new Hilo("hermano2", cuentaCorriente);
        Hilo hilo5 = new Hilo("hermano3", cuentaCorriente);
        // Lanzamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        // Hacemos que el primer hilo en terminar se espere a por el último
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mostramos los resultados
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cuentaCorriente.getSaldo());
        System.out.println("--------------------------------------");

    }

}
