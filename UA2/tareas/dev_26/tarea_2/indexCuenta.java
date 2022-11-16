public class indexCuenta {
    public static void main(String[] args){

        CuentaCorriente cuenta = new CuentaCorriente(1000, "Jose");
        HiloCuenta hilo1 = new HiloCuenta(cuenta, "hilo1", 200);
        HiloCuenta hilo2 = new HiloCuenta(cuenta, "hilo2", 500);
        HiloCuenta hilo3 = new HiloCuenta(cuenta, "hilo3", 670);
        HiloCuenta hilo4 = new HiloCuenta(cuenta, "hilo4", 200);
        HiloCuenta hilo5 = new HiloCuenta(cuenta, "hilo5", 500);
        HiloCuenta hilo6 = new HiloCuenta(cuenta, "hilo6", 670);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            hilo6.join();
        }
        catch (InterruptedException e){}
    }
}
