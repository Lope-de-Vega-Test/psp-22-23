package tarea_2;

public class CuentaCorriente {
	//VARIABLES PRIVADAS DE LA CLASE
	private double saldo;
	//CONSTRUCTORES CON SOBRECARGA
	CuentaCorriente(double saldo) {
		this.saldo = saldo;
	}

	CuentaCorriente() {
		saldo = 0;
	}
	//VER SALDO CON TIEMPO DE ESPERA DE EJECUCIÓN ALEATORIO
	public double getSaldo() {
		try {
			Thread.sleep(numRandom());
		} catch (InterruptedException e) {

		}
		return saldo;
	}
	//ASIGNAR SALDO CON TIEMPO DE ESPERA DE EJECUCIÓN ALEATORIO
	public void setSaldo(double saldo) {

		this.saldo = saldo;
		try {
			Thread.sleep(numRandom());
		} catch (InterruptedException e) {

		}
	}
	//METODO, RECIBE SALDO Y NOMBRE, MUESTRA SALDO, INCREMENTA SALDO Y MUESTRA TODOS
	public synchronized void IncrementarS(double saldo, String nombre) {

		double s_inicial = getSaldo();
		setSaldo(s_inicial + saldo);
		double s_final = getSaldo();
		System.out.println("Saldo Inicial: " + s_inicial);
		System.out.println("Saldo Final: " + s_final);
		System.out.println("Operacion realizada por: " + nombre);
	}
	//METODO, DEVUELE UN NUMERO ALEATORIO ENTRE 250 Y 2000
	private static long numRandom() {
		double num = (Math.random() * ((2000 - 250) + 1)) + 250;
		long num_redondeo = (long) (Math.round(num * 100.0) / 100.0);
		return num_redondeo;
	}
}
