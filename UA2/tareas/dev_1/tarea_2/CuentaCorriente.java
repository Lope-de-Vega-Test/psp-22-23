package ua2tarea2;


import static java.lang.Thread.sleep;
    
/**
 *
 * @author Irene Alba Posadas
 */
public class CuentaCorriente {
     Double saldo;//inicializamos la variable
     
    //constructor para dar valor a la variable desde el inicio
    public CuentaCorriente(double saldo){
        this.saldo = saldo;
    }

    //setter para darle valor a la variable en cualquier momento
    public void setSaldo(double saldo) {
        try{
            sleep(1000);//espera 1 segundo
            this.saldo = saldo;
        }catch(Exception e){
            //Nothing...
        }
    }

    //getter para consultar el valor de la variable en cualquier momento
    public double getSaldo() {
        try{
            sleep(1000);//espera 1 segundo
        }catch(Exception e){
            //Nothing...
        }
        return saldo;
    }
    
    //función para añadir la cantidad pasada al saldo y mostrarlo
    public void ingresarSaldo(double cantidad, String nombre){                        
        synchronized(saldo){
            double oldSaldo = saldo;//una variable para almacenar el saldo anterior al ingreso
            saldo += cantidad;
            System.out.println("Estado de la cuenta previo al ingreso: " + oldSaldo + ". \n"//se muestra el saldo anterior al ingreso
                    + "Estado actual de la cuenta tras el ingreso: " + saldo + ". \n"//se muestra el saldo tras el ingreso
                            + "Ingreso realizado por: " + nombre + ".");//se muestra el nombre de la persona que ha realizado el ingreso
            System.out.println("-------------------------------------------------");
        }
    }
    /*
    *   usando el synchronized en la función anterior podemos comprobar como los hilos quedan ordenados
    *   no se ordenan por orden de llamada, pero hasta que no acaba un hilo no empieza el otro
    *   en cambio, si quitamos el synchronized podemos observar como se entremezclan los hilos
    *   por lo que si usamos el synchronized nos aseguramos de que todos los hilos han realizado correctamente su función
    *   además de estar ordenados
    */
}
