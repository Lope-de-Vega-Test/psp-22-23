

public class HiloCuenta extends Thread {
    
    private CuentaCorriente cuenta;
    private String nombre;
    private float saldo;

    public HiloCuenta(CuentaCorriente cuenta, String nombre, float saldo) {
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public void run(){
        cuenta.addSaldo(saldo, nombre);
    }
}