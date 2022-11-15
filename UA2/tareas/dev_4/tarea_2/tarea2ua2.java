package tarea.pkg2.ua2;

public class Tarea2UA2 {

    
    public static void main(String[] args) {
        //Llamamos a la clase creando una cuenta, a su vez le damos un saldo generado por nosotros
        CuentaCorriente cuenta= new CuentaCorriente(0);
        
        
        //La clase ingresar nos permitira crear tantos ingresos como queramos, 
        //rellendando las variables esenciales, tal el nombre de la cuenta, la cantidad que quedramos añadir y el nombre de la persona,
        
        Ingresar ingreso1= new Ingresar(cuenta,200.0,"Pablo");
        Ingresar ingreso2= new Ingresar(cuenta,500.0,"Antonio");
        Ingresar ingreso3= new Ingresar(cuenta,700.0,"Manuel");
        Ingresar ingreso4= new Ingresar(cuenta,900.0,"Paco");
        
        
        //Inicializamos los hilos
        ingreso1.start();
        ingreso2.start();
        ingreso3.start();
        ingreso4.start();
        
        
        //Un trycatch para que los hilos de conecten para que se puedan realizar las funciones de sus clases correspondientes
        try{
            ingreso1.join();
            ingreso2.join();
            ingreso3.join();
            ingreso4.join();
        }catch(Exception e)
        {
            //Nada
        }
        
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecucion de los hilos");
        System.out.println("--------------------------------------");
        
    }
    
}
