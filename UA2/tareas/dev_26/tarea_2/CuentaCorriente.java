

public class CuentaCorriente {

    private float saldo;
    private String nombre;

    public CuentaCorriente(){
        saldo = 0;
        nombre = "anonimo";
    }

    public CuentaCorriente(float saldo, String nombre){
        this.saldo = saldo;
        this.nombre = nombre;
    }

    public float getSaldo(){
        try {
            Thread.sleep(numRandom());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saldo;    
    }

    public void setSaldo(float saldo){
        try {
            Thread.sleep(numRandom());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saldo = saldo;
    }

    public synchronized void addSaldo(float saldo, String nombre){
        System.out.println("Cuenta de: "+ this.nombre);
        System.out.println("El saldo anterior era: " + this.saldo);
        System.out.println("El ingreso será de: "+ saldo);
        System.out.println("El nuevo saldo es: " +  (this.saldo + saldo));
        System.out.println("La persona que realizó el ingreso fue: " + nombre+"\n");
        setSaldo(this.saldo + saldo);
    }

    private static long numRandom(){
        long num = (long) (Math.random() *(2000-250+1)+250);
        return num;
    }

}