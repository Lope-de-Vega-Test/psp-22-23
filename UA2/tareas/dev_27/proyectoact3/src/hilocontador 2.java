public class hilocontador extends Thread {
	private contador contar;
	private String argumento;
	private int id;

	hilocontador(contador cc, String argumento, int id) {
		this.contar = cc;
		this.argumento = argumento;
		this.id = id;
	}

	public void run() {
		// USAMOS UN CRONOMETRO EXTERNO
		ExecutionTimer timer = new ExecutionTimer();
		timer.start();
		// CREAMOS LA RUTA DEL ARCHIVO A CONTAR
		String nombreFichero = contar.rutadirectorio(argumento);
		// ABRIMOS Y CONTAMOS LOS CARACTERES DEL ARCHIVO
		int contador = contar.abrir_contador(nombreFichero);
		// MOSTRAMOS LA CANTIDAD DE CARACTERES
		System.out
				.println("El contador " + id + " nos dice que su archivo tiene: " + contar.mostrarCaracteres(contador));

		// PARAMOS EL CRONOMETRO Y MOSTRAMOS EL TIEMPO QUE HA TARDADO EN REALIZAR LA
		// OPERACION
		timer.stop();
		System.out.println("El contador " + id + " ha tardado: " + timer.printElapsedTime());

	}

}