package tarea_1;

class Contador2 {
	private int c = 0; // Atributo Contador

	Contador2(int c) {
		this.c = c;
	}

	/*
	 * SINCRONIZANDO ESTE METODO TENEMOS PROBLEMAS DE PRIORIADA QUE PUEDE TERMINAR
	 * EN PERDIDA DE OPERACIONES
	 */
	public synchronized void incrementa() {
		c++;
	}

	public int valor() {
		return c;
	}
} // Fin Class Contador2

class HiloSumador2 extends Thread {
	private Contador contador;

	public HiloSumador2(String nombre, Contador c) {
		setName(nombre);
		contador = c;
	}

	public void run() {

		/* SINCRONIZAR */
//		synchronized (contador) {

		for (int j = 0; j < 1000; j++) {
			contador.incrementa();

		}
		System.out.println(getName() + " - contador vale " + contador.valor());
//		}
	}

} // Fin Class HiloSumador2

public class ua2tarea1fr2 {
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
			hiloSuma[i].start();
		}

		for (int i = 0; i < 5; i++) {

			try {
				hiloSuma[i].join();

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