public class Hilo extends Thread {

    private CuentaCorriente cuenta;
    private double saldo;
    private String nombre;
//Creamos un constructor
    Hilo(CuentaCorriente cc,double saldo,String nombre){
            this.saldo=saldo;
            this.nombre=nombre;
            cuenta=cc;
        }

    // Ejecutamos el metodo 
    public void run() {
        cuenta.IncrementarS(saldo, nombre);
    }

}
