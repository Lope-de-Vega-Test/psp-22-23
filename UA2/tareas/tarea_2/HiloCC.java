package tarea_2;

public class HiloCC extends Thread{
	//VARIABLES PRIVADAS DE LA CLASE
	private CuentaCorriente cuenta;
	private double saldo;
	private String nombre;
	//CONSTRUCTOR
	HiloCC(CuentaCorriente cc,double saldo,String nombre){
		this.saldo=saldo;
		this.nombre=nombre;
		cuenta=cc;
	}
	//METODO, EJECUTA EL METODO LLAMADO COMO HILO
	public void run() {
		cuenta.IncrementarS(saldo,nombre);
	}
	
}
