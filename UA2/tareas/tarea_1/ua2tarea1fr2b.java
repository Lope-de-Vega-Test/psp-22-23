package tarea_1;

class Contador3 {
	private int c = 0; // Atributo Contador

	Contador3(int c) {
		this.c = c;
	}

	public void incrementa() {
		c++;
	}

	public int valor() {
		return c;
	}
} // Fin Class Contador2

class HiloSumador3 implements Runnable {
	private Contador contador;

	public HiloSumador3(String nombre, Contador c) {
		new Thread().setName(nombre);
		contador = c;
	}

	/* SINCRONIZAR */
	public synchronized void run() {

		for (int j = 0; j < 1000; j++) {
			contador.incrementa();

		}
		System.out.println(new Thread().getName() + " - contador vale " + contador.valor());

	}

} // Fin Class HiloSumador2

public class ua2tarea1fr2b {
	public static void main(String[] args) {
		System.out.println("-------------------------------");
		System.out.println("Hilos: Bloques Sincronizados");
		System.out.println("-------------------------------");

		Contador cont = new Contador(0);
		HiloSumador hiloSuma[] = new HiloSumador[5];
		System.out.println("Comienza la ejecución de los hilos ...");
		System.out.println("--------------------------------------");
		for (int i = 0; i < 5; i++) {
			hiloSuma[i] = new HiloSumador("Hilo Sumador " + i, cont);

			new Thread(hiloSuma[i]).start();
		}

		for (int i = 0; i < 5; i++) {

			try {
				/*
				 * EL METODO JOIN PERTENECE A LA CLASE THREAD Y NO A RUNNABLE CREANDO PROBLEMAS
				 * EN EL TIEMPO DE REACCIÓN
				 */
				new Thread(hiloSuma[i]).join();

			} catch (InterruptedException e) {
				// Nothing to do here ...
			}
		}
		System.out.println("--------------------------------------");
		System.out.println("... Finaliza la ejecución de los hilos");
		System.out.println("--------------------------------------");
		System.out.println("Valor Final del Contador: " + cont.valor());
		System.out.println("--------------------------------------");

	}

}
