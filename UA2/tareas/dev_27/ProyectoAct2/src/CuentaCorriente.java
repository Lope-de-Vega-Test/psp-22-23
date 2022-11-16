public class CuentaCorriente {
    private double saldo;
    //Creamos una sobrecarga de constructores
    CuentaCorriente(double saldo) {
        this.saldo = saldo;
    }

    CuentaCorriente() {
        saldo = 0;
    }

    // Consultamos el saldo con un tiempo de ejecucion aleatorio
    public double getSaldo() {
        try {
            Hilo.sleep(numRandom());
        } catch (InterruptedException e) {

        }
        return saldo;
    }

    // asignamos el saldo con un tiempo de ejecucion aleatorio
    public void setSaldo(double saldo) {

        this.saldo = saldo;
        try {
            Hilo.sleep(numRandom());
        } catch (InterruptedException e) {

        }
    }
    //Creamos un metodo que reciba el nombre y el saldo
    //Posteriormente lo muestra e incrementa
    public synchronized void IncrementarS(double saldo, String nombre) {

        double s_inicial = getSaldo();
        setSaldo(s_inicial + saldo);
        double s_final = getSaldo();
        System.out.println("Saldo Inicial: " + s_inicial);
        System.out.println("Saldo Final: " + s_final);
        System.out.println("Operacion realizada por: " + nombre);
    }

    // Creamos un metodo que devuelva un numero aleatorio entre 250 Y 2000
    private static long numRandom() {
        double num = (Math.random() * ((2000 - 250) + 1)) + 250;
        long num_redondeo = (long) (Math.round(num * 100.0) / 100.0);
        return num_redondeo;
    }
}
