//Clase CuentaCorriente
class CuentaCorriente{
private float saldo=0.0f;
CuentaCorriente(float saldo){
   this.saldo=saldo;
}
//funcion en la cual se  sincronizan los hilos
public synchronized void Incrementa(float anadir,String nombre){
    
    System.out.println("saldo actual es: "+saldo);
    this.saldo+=anadir;
    
    System.out.println("Saldo nuevo: "+saldo);
    System.out.println("Operacion realizada por: "+nombre);
}
    public float getSaldo() {
    try{
        //Sleep aleatorio entre 250 y 2000 milisegundos
        
        Thread.sleep((int) (Math.random()*(1751)+250));
        
    }catch(Exception e){
        
    }
        return saldo;
    }

    public void setSaldo(float saldo) {
    try{
        Thread.sleep((int) (Math.random()*(1751)+250));
        
    }catch(Exception e){
        
    }
        this.saldo = saldo;
    }
    

}
//Clase hilo
class hilo extends Thread{
    float monedas;
    CuentaCorriente cuenta;
    String nombre;

    //Constructor hilo
    public hilo(float monedas, CuentaCorriente cuenta, String nombre) {
        this.monedas = monedas;
        this.cuenta = cuenta;
        this.nombre = nombre;
    }
    public void run(){
        cuenta.Incrementa(monedas,nombre);
    }
}
public class tarea_2 {

    
    public static void main(String[] args) {
       
        //Se crea una variable de la clase CuentaCorriente
        CuentaCorriente cuenta= new CuentaCorriente(100);
        
        //Se crean los hilos con parámetros
        hilo hilo1=new hilo(1200,cuenta,"Lucia");
        hilo hilo2=new hilo(1300,cuenta,"Carlos");
        hilo hilo3=new hilo(200,cuenta,"Manuel");
        hilo hilo4=new hilo(12,cuenta,"Dani");
        hilo hilo5=new hilo(12000,cuenta,"Elena");
        
        //Comienzan los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
         try{
             //Se juntan los hilos
              hilo1.join(); 
              hilo2.join();
              hilo3.join(); 
              hilo4.join();
              hilo5.join();
         }     
         
         catch(Exception e){
                
    }
         System.out.println("El total es: "+cuenta.getSaldo());
    
}
}
