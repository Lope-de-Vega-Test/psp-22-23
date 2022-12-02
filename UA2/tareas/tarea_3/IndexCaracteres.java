/*
 * @AUTHOR ALFONSO ALCARAZ*/
package tarea_3;

public class IndexCaracteres {

	public static void main(String[] args) {
		ContarCaracteres contar = new ContarCaracteres();
		// NECESITAREMOS 3 ARGUMENTOS PARA QUE NUESTRO PROGRAMA FUNCIONE
		// LES PASAMOS A CADA HILO UN OBJETO TIPO CONTARCARACTER=PARA USAR SUS METODOS,
		// EL ARGUMENTO A CONTAR Y UN IDENTIFICADOR
		HiloContar hilo1 = new HiloContar(contar, args[0], 1);
		HiloContar hilo2 = new HiloContar(contar, args[1], 2);
		HiloContar hilo3 = new HiloContar(contar, args[2], 3);
		// EMPEZAMOS SU EJECUCIÓN
		hilo1.start();
		hilo2.start();
		hilo3.start();
		try {
			// ESPERAMOS QUE LOS HILOS VAYAN TERMINANDO
			hilo1.join();
			hilo2.join();
			hilo3.join();

		} catch (Exception e) {

		}

	}

}
