package tarea_2;

public class IndexCC {

	public static void main(String[] args) {
		//CREACION DE LA CLASE BASE CON LA CANTIDAD INICIAL DE DINERO DE LA CUENTA
		CuentaCorriente cuenta = new CuentaCorriente(100);
		//CREACION DE VARIOS HILOS CON EL INCREMENTO DE SALDO Y EL NOMBRE DE QUIEN REALIZA
		HiloCC user1 = new HiloCC(cuenta, 500, "Pepe");
		HiloCC user2 = new HiloCC(cuenta, 700, "Ana");
		HiloCC user3 = new HiloCC(cuenta, 750, "Ruz");
		//COMIENZA EJECUCIÓN DE LOS HILOS
		user1.start();
		user2.start();
		user3.start();
		//CREA UN ORDEN DE EJECUCIÓN DE LLEGADA
		try {
			user1.join();
			user2.join();
			user3.join();

		} catch (Exception e) {

		}
		System.out.println("Saldo total: " + cuenta.getSaldo());

	}

}
/*
 * Al iniciar todos los hilos el flujo de datos devuelve los datos en un orden
 * 1,3,2 en lugar de 1,2,3 estamos investigando aún por que lanza correctamente
 * el primero y luego hace un orden inverso empezando por el final Lo que queda
 * claro es que la forma synchronized dan rigor al fluyo creando un orden
 * persistente todo lo contrario al no usar la forma synchronized que devuelve
 * los valores en orden pseudoaleatorio
 */
