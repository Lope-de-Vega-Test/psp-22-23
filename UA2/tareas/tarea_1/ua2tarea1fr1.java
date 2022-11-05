package tarea_1;

class Contador {
	private int c = 0; // Atributo Contador

	Contador(int c) {
		this.c = c;
	}

	public void incrementa() {
		c++;
	}

	public int valor() {
		return c;
	}
} // Fin Class Contador

class HiloSumador extends Thread {
	private Contador contador;

	public HiloSumador(String nombre, Contador c) {
		setName(nombre);
		contador = c;
	}

	public void run() {

		for (int j = 0; j < 1000; j++) {
			contador.incrementa();

		}
		System.out.println(getName() + " - contador vale " + contador.valor());
	}

} // Fin Class HiloSumador

public class ua2tarea1fr1 {
	public static void main(String[] args) {
		System.out.println("-------------------------------");
		System.out.println("Hilos: Bloques NO Sincronizados");
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
/* AL NO ESTAR SINCRONIADO PUEDE TENER PERDIDA DE INFORMACIÓN Y SINCRONIZACIÓN*/
