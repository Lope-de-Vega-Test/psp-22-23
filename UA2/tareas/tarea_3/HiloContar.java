package tarea_3;

public class HiloContar extends Thread {
	private ContarCaracteres contar;
	private String argumento;
	private int id;

	HiloContar(ContarCaracteres cc, String argumento, int id) {
		this.contar = cc;
		this.argumento = argumento;
		this.id = id;
	}

	public void run() {
		// USAMOS UN CRONOMETRO EXTERNO
		ExecutionTimer timer = new ExecutionTimer();
		timer.start();
		// CREAMOS LA RUTA DEL ARCHIVO A CONTAR
		String nombreFichero = contar.crearRuta(argumento);
		// ABRIMOS Y CONTAMOS LOS CARACTERES DEL ARCHIVO
		int contador = contar.abrirYContar(nombreFichero);
		// MOSTRAMOS LA CANTIDAD DE CARACTERES
		System.out
				.println("El contador " + id + " nos dice que su archivo tiene: " + contar.mostrarCaracteres(contador));

		// PARAMOS EL CRONOMETRO Y MOSTRAMOS EL TIEMPO QUE HA TARDADO EN REALIZAR LA
		// OPERACION
		timer.stop();
		System.out.println("El contador " + id + " ha tardado: " + timer.printElapsedTime());

	}

}
