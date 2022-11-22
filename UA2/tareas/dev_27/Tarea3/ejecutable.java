public class ejecutable {

	public static void main(String[] args) {
		contador contar = new contador();
		// Iniciamos todos los hilos
		// Cada uno recibira como argumento el nombre del fichero que queramos contar
		// junto a un identificador para poder diferenciarlos
		hilocontador hilo1 = new hilocontador(contar, args[0], 1);
		hilocontador hilo2 = new hilocontador(contar, args[1], 2);
		hilocontador hilo3 = new hilocontador(contar, args[2], 3);
		// Iniciamos la ejecucion con start
		hilo1.start();
		hilo2.start();
		hilo3.start();
		try {
			// hacemos un join de cada hilo
			hilo1.join();
			hilo2.join();
			hilo3.join();

		} catch (Exception e) {
			//realizamos control de errores

		}

	}

}