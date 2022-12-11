
//Declaramos la clase CuentaCorriente
class CuentaCorriente{
    //Declaramos las variables que llevara la clase, en este caso son saldo e incremento donde los dos son int.
    int saldo;
    int incremento;
    //Creamos el constructor vacio de la clase.
    public CuentaCorriente(){
        this.saldo=0;
        this.incremento=0;
    }
    //Creamos el constructor de la clase.
    public CuentaCorriente(int saldo){
        this.saldo=saldo;
    }
    //Ponemos los Setter y Getter de la variable saldo.
    public void setSaldo(int saldo) {
        this.saldo = saldo;
        //Ponemos un sleep para que cuando se use la funcion los hilos se paren un tiempo de forma aleatoria entre 0.25 a 2 segundos.
        try{
        Thread.sleep((int)(Math.random()*(1751)+250));
        }
        catch(InterruptedException e){}
    }
    public int getSaldo() {
        try{
        Thread.sleep((int)(Math.random()*(1751)+250));
        }
        catch(InterruptedException e){}
        return saldo;
    }
    //Esta funcion sera la que usaremos para incrementar el valor de la variable incremento que luego usaremos para sumarselo a la variable saldo.
    public synchronized void incrementar(){
        incremento++;
    }
    //Esta función sirve para mostrar los resulados de los hilos donde mostrara el nombre del hilo que lo ha ejecutado, el saldo antes de ser 
    //incrementado y el saldo incrementado.
    public synchronized void resultado(String nombre){
        System.out.println("--------------------------------------");
        System.out.println("El valor previo del saldo era: "+getSaldo()+"€");
        System.out.println("El saldo actual es: "+(saldo+=incremento)+"€");
        //Igualo el incremento a 0 para que la proxima vez que la funcion sea ejecutada no se acumulen los numeros incrementados.
        incremento=0;
        System.out.println("Quien ha realizado el ingreso es: "+nombre);
        System.out.println("--------------------------------------");
        System.out.println("");
        System.out.println("");
    }
}
//Creamos la clase Hilos que la usaremos para que incrementen el valor del saldo dentro de la clase CuentaCorriente.
class Hilos extends Thread{
    //Declaramos las variables dentro de la clase, en este caso son num que lo usaremos para saber cuanto queremos incrementar el valor 
    //de saldo y una variable de la clase Cuentacorriente que la usaremos para ejecutar las funciones de la otra clase.
    int num;
    private CuentaCorriente cuenta;
    //Creamos el constructor de la clase Hilos
    public Hilos(String nombre, int num, CuentaCorriente cuenta){
        setName(nombre);
        this.num=num;
        this.cuenta=cuenta;
    }
    //Usamos la funcion run() para ejecutar lo siguiente:
    public void run(){
        //Creamos un for que ejecurtará la funcion incrementar el valor de incremento hasta el numero que haya sido introducido.
        for (int i=0; i<num; i++){
            cuenta.incrementar();
        }
        //Despues de que se incremente el valor de incremento hasta el numero introducido se ejecuta la funcion de resultados para mostrar
        //todos los valores del hilo que esta ejecutando las funciones.
        cuenta.resultado(getName());
    }
}
public class ua2tarea2 {
    
    public static void main(String[] args) {
        //Declaramos una variable de la clase CuentaCorriente para introducir el numero inicial que quieras que tenga la variable saldo.
        CuentaCorriente cuenta=new CuentaCorriente(30);
        //Declaramos los hilos que vamos a usar, donde vamos a introducir el nombre del hilo, el numero con el que incrementara el valor de la
        //variable incremento y el numero inicial con el que empezaran a sumar.
        Hilos hilo1=new Hilos("Hilo1", 60, cuenta);
        Hilos hilo2=new Hilos("Hilo2", 200, cuenta);
        Hilos hilo3=new Hilos("Hilo3", 300, cuenta);
        Hilos hilo4=new Hilos("Hilo4", 56, cuenta);
        Hilos hilo5=new Hilos("Hilo5", 100, cuenta);
        //Ejecutamos los hilos
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
        catch(InterruptedException e){}
        //Mostramos el saldo final.
        System.out.println("El saldo final es: "+cuenta.saldo+" €");
    }
}
