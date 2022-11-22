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
		// Hacemos uso del executionTimer
		ExecutionTimer timer = new ExecutionTimer();
		timer.start();
		// Crea la ruta del fichero
		String nombreFichero = contar.rutadirectorio(argumento);
		// Abre el archivo y cuenta los caracteres
		int contador = contar.abrir_contador(nombreFichero);
		// Mostramos el numero de caracteres
		System.out.println("El contador " + id + " nos dice que su archivo tiene: " + contar.mostrarCaracteres(contador));
		// Paramos el executiontimer y lo mostramos
		timer.stop();
		System.out.println("El contador " + id + " ha tardado: " + timer.printElapsedTime());

	}

}