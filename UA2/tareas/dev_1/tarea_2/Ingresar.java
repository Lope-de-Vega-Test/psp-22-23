package ua2tarea2;

import java.util.Scanner;


/**
 *
 * @author Irene Alba Posadas
 */
public class Ingresar extends Thread{
    Scanner scan = new Scanner(System.in);
    
    //inicializamos las variables necesarias
    CuentaCorriente cuenta;
    double cantidad;
    
    //constructor para darle valores al hilo
    public Ingresar(CuentaCorriente cuenta, double cantidad, String nombre){
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        setName(nombre);
    }
    
    //llamamos a la función ingresarSaldo de la clase CuentaCorriente pasandole la cantidad a añadir y el nombre del que lo ingresa
    public void run(){
        
        cuenta.ingresarSaldo(cantidad, getName());
    }
}
