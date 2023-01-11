public class ExecutionTimer {
	private long startTime = 0;
	private long endTime = 0;
	private long timeElapsed = 0;

	// Funcion iniciar timer
	public void start() {
		startTime = System.nanoTime();
	}

	// Funcion parar timer
	public void stop() {
		endTime = System.nanoTime();
	}

	// Funcion calcular tiempo de ejecucion
	public void elapsedTime() {
		timeElapsed = endTime - startTime;
	}

	// Funcion mostrar tiempo de ejecucion
	public String printElapsedTime() {
		String mensaje;
		elapsedTime();
		mensaje = timeElapsed / 1000000 + " milliseconds ";

		return mensaje;
	}
}