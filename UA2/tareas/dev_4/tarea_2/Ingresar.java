package tarea.pkg2.ua2;


//La clase Ingresar donde se permitira apropiar una cantidad que la persona que controle la cuenta quiera añadir

public class Ingresar extends Thread {
    CuentaCorriente cuenta;
    double cantidad;
    
    //Ejecutable run que se encargara de correr la ejecucion 
    public void run()
    {
        cuenta.anadir(cantidad, getName());
    }

    //Clase Ingresar que nos llama las variables de la clase CuentaCorriente para poder ingresar
    public Ingresar(CuentaCorriente cuenta, Double cantidad, String nombre) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        
        setName(nombre);
        
    }
    
    
}
