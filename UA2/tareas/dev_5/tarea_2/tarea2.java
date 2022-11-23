

class CuentaCorriente{

    private float saldo=0.0f;

    CuentaCorriente(float saldo)
    
    {
    this.saldo=saldo;

    }

    //AQUI SE SINCRONIZAN LOS HILOS  

    public synchronized void Incrementa(float meter,String nombre)
    {
        
        System.out.println("SALDO ACTUAL: "+saldo);
        this.saldo+=meter;
        
        System.out.println("SALDO NUEVO: "+saldo);
        System.out.println("REALIZADO: "+nombre);

    }
        public float getSaldo() {
        try{
            
            
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
    //CLASE HILO
    class hilo extends Thread{
        float monedas;
        CuentaCorriente cuenta;
        String nombre;
    
       
        public hilo(float monedas, CuentaCorriente cuenta, String nombre) {
            this.monedas = monedas;
            this.cuenta = cuenta;
            this.nombre = nombre;
        }
        public void run(){
            cuenta.Incrementa(monedas,nombre);
        }
    }
    public class tarea2 {
    
        
        public static void main(String[] args) {

            
            CuentaCorriente cuenta= new CuentaCorriente(100);
            
            //CREAMOS LOS HILOS
            hilo hilo1=new hilo(1000,cuenta,"Manuel");
            hilo hilo2=new hilo(1500,cuenta,"Pepe");
            hilo hilo3=new hilo(400,cuenta,"Alejandra");
            hilo hilo4=new hilo(20,cuenta,"Fernando");
            hilo hilo5=new hilo(11000,cuenta,"Paula");
            
            //EMPIEZAN LOS HILOS
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
             }     
             
             catch(Exception e){
                    
        }
             System.out.println("EL SALDO TOTAL ES: "+cuenta.getSaldo());
        
    }
    }