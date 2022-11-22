/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1.dani.tarea3;

/**
 *
 * @author RafaelRomero
 */
public class principal {

    public static void main(String[] args) {
		contar contar = new contar();
		// NECESITAREMOS 3 ARGUMENTOS PARA QUE NUESTRO PROGRAMA FUNCIONE
		// LES PASAMOS A CADA HILO UN OBJETO TIPO CONTARCARACTER=PARA USAR SUS METODOS,
		// EL ARGUMENTO A CONTAR Y UN IDENTIFICADOR
		hilos hilo1 = new hilos(contar, args[0], 1);
		hilos hilo2 = new hilos(contar, args[1], 2);
		hilos hilo3 = new hilos(contar, args[2], 3);
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

