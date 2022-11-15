package tarea.pkg2.ua2;

import static java.lang.Thread.sleep;



//Clse CuentaCorriente donde se alojara el saldo inicial 
public class CuentaCorriente {
    Double saldo;

    //Constructor de CuentaCorriente
    public CuentaCorriente(double saldo) {
        this.saldo = saldo;
    }


    //Clases de saldo teniendo un getter y un setter.
    public double getSaldo() {
        try{
            sleep(1000);
        }catch(Exception e)
        {
            //Nada
        }
        return saldo;
        
    }

    public void setSaldo(double saldo) {
        try{
            sleep(1000);
            this.saldo = saldo;
        }catch(Exception e)
        {
            //Nada
        }
    }

    //La funcion de añadir una cantidad mostrando a su vez la cantidad anterior que habia antes del depositamiento 
    public void anadir(double cantidad, String nombre)
    {
        synchronized(saldo)
        {
        double primerSaldo=saldo;
        saldo+=cantidad;
        System.out.println("La cantidad anterior al ingreso: "+primerSaldo+"\n La cantidad despues del ingreso es: "+saldo+"\n La persona ingresora es: "+nombre);
        }
    }
    
    
}
