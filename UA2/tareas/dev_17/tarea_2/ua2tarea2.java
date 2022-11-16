//clase Cuenta Corriente, con la variable saldo y los metodos getter y setter.
class CuentaCorriente {
	private int saldo ;

	public CuentaCorriente(int s) {
		 saldo = s; 
	}
	
	public int getSaldo() {
		 return saldo; 
	}

	public void setSaldo(int saldo) {
		this.saldo=saldo;; 
   }
   //metodo que suma cada vez que se le llama
	public void sumar(int cantidad) {
		 saldo=saldo+cantidad; 
	}
	//agregamos saldo hasta 100 si llegase, mostrando cada vez que se añade el saldo actual, el saldo a agregar y el saldo final.
	synchronized void AgregarDinero(int cant, String nom) {
		if (getSaldo() < 100) {
			System.out.println(nom+" -> Saldo actual:  ("+getSaldo()+ ")" );
			try {
				//sleep
				Thread.sleep(500);
			} catch (InterruptedException ex) {	}
			//metodo sumar cada vez que se llama a este metodo
			sumar(cant);		
			
			System.out.println(""+ nom+ " Agrega :"+cant + " . Nuevo saldo:("+getSaldo()+")\n" );	
		} else {
			//si el saldo llega a mas de 100
			System.out.println(nom+ " Saldo mayor de 100...("+getSaldo()+")" );
		}
		if (getSaldo() < 0) {
			System.out.println("SALDO NEGATIVO => "+getSaldo());
		}
		
	}//agregar
}
//clase que etiende de Thread
class AgregarDinero2 extends Thread {
	private CuentaCorriente c;
	String nom;
	int cantidad=0;
	//metodo para agregar una cantidad detrminada de dinero en cada objeto creado
	public AgregarDinero2(int cantidad,String n, CuentaCorriente c) {		
		super(n);
		this.c = c;
		this.cantidad = cantidad;
	}
	//metodo run() que llama a AgregarDinero, con una cantidad de dinero a agregar determinada en main.
	public  void run() {	
		//realizan dos acciones cada hilo	
		for (int x = 1; x<= 2; x++) {		
			c.AgregarDinero(cantidad, getName());			
		}		
	}// run
	
}
public class ua2tarea2 {
	public static void main(String[] fer) {
		//Objeto CuentaCorriente con 40 euros de inicio
		CuentaCorriente c = new CuentaCorriente(40);
		//dos hilos que añaden diferentes cantidades en la misma cuenta
		AgregarDinero2 h1 = new AgregarDinero2(10,"Jose", c);
		AgregarDinero2 h2 = new AgregarDinero2(20,"Pablo", c);

		h1.start();
		h2.start();
	}
}

//FR4: al eliminar synchronized del metodo AgregarDinero vemos como los datos obtenidos por pantalla fallan, ya el orden varía a hacerlo
//con synchronized. Con synchronized se ejecuta el metodo AgregarDinero entero por cada hilo, sin él ejecuta hasta el sleep del metodo
//con cada hilo y despues el resto.