package ua2tarea2;


/**
 *
 * @author Irene Alba Posadas
 */
public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(25);//creamos el objeto CuentaCorriente dandole un valor inicial al saldo de 25
        
        System.out.println("El valor inicial es: " + cuenta.getSaldo());//mostramos el valor inicial del saldo
        
        //creamos varios hilos de la clase ingresar a los que les pasamos el objeto común CuentaCorriente, una cantidad a añadir y el nombre de la persona que ingresa el dinero
        Ingresar hilo1 = new Ingresar(cuenta,35, "Pablo");
        Ingresar hilo2 = new Ingresar(cuenta, 4, "Antonio");
        Ingresar hilo3 = new Ingresar(cuenta, 500, "Pepito");
        Ingresar hilo4 = new Ingresar(cuenta, 150, "Paco-tronics");
        Ingresar hilo5 = new Ingresar(cuenta, 200, "Joseca");
        
        System.out.println("Comienzan las operaciones...");
        System.out.println("----------------------------");
        
        //iniciamos los procesos de los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }catch(Exception e){
            //Nothing...
        }
        
        System.out.println("---------------------------");
        System.out.println("Operaciones finalizadas...");
        System.out.println("---------------------------");
        System.out.println("Saldo final: " + cuenta.getSaldo());//hilos finalizados se muestra el saldo final en la cuenta
    }
}
