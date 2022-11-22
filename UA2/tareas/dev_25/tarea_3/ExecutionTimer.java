/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.tarea3;

/**
 *
 * @author RafaelRomero
 */
public class ExecutionTimer {
    private long startTime = 0;
	private long endTime = 0;
	private long timeElapsed = 0;

	public void start() {
		startTime = System.nanoTime();
	}

	public void stop() {
		endTime = System.nanoTime();
	}

	public void elapsedTime() {
		timeElapsed = endTime - startTime;
	}

	public String printElapsedTime() {
		String mensaje;
		elapsedTime();
		mensaje = timeElapsed / 1000000 + " milliseconds ";

		return mensaje;
	}
}
