/* FR3 [2 puntos]. Clase HilosClientes: que extienda Thread. Deberá recibir un nombre como argumento en el constructor y asignarlo al hilo usando la función adecuada. El método run() deberá implementar el siguiente algoritmo:

    Indicar que el hilo está ejecutándose
    Infinitamente repetir:
        Pedir un vaso de Cerveza
        Beber la rica y deliciosa Cerveza
        Ir contabilizando la cantidad total de cerveza bebida (en LITROS)
        Devolver el vaso de Cerveza
        Esperar antes de pedir otra Cerveza (dormir al cliente un tiempo aleatorio entre 250 ms y 1000 ms)

FR4 [2 puntos]. Crea una Aplicación (main) que genere un Camarero, de nombre Mou y creará los siguientes Clientes: Homer, Barney, Carl, Lenny y Lurleen. Cada Cliente recibirá el objeto compartido Camarero. Adicionalmente, se deberá sincronizar el uso del objeto compartido y se deberán realizar varias pruebas de ejecución para garantizar que el sistema implementado funciona correctamente.

FR5 [2 puntos]. Se debe cumplir con las siguientes características adicionales de implementación:

    Crear todo el código en un único fichero Java: UA2\examen\dev_X\ua2ex1.java ... en tu propia rama de desarrollo dev_X
    Todos los métodos (de todas las clases) deben imprimir por pantalla información respecto a su estado de ejecución.
    Implementa el control de errores
    Documenta el código: utiliza el estilo/herramienta doxygen ... ¿la conocías?
        https://hackmd.io/@Jon97/Sk5QwpuBL
        Documenta el Fichero, Clases, Métodos y Aplicación Principal.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Rafael Romero Benítez
 */

public class ua2ex1 {

    public static void main(String[] args) {
		contar contar = new contar();
		// NECESITAREMOS 3 ARGUMENTOS PARA QUE NUESTRO PROGRAMA FUNCIONE
		// LES PASAMOS A CADA HILO UN OBJETO TIPO CONTARCARACTER=PARA USAR SUS METODOS,
		// EL ARGUMENTO A CONTAR Y UN IDENTIFICADOR
                
		HilosClientes hilo1 = new HilosClientes(contar, args[0], 1);
		HilosClientes hilo2 = new HilosClientes(contar, args[1], 2);
		HilosClientes hilo3 = new HilosClientes(contar, args[2], 3);
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

/*FR1 [2 puntos]. Clase VasoCerveza: representa el elemento que será consumido 
por los Clientes y preparado por los Camareros.

    1.1. Atributos:
        id: int (identificador del vaso) - Valores aceptados: 0,1,2,3, ...
        tipo: int - Valores aceptados: 0 media pinta, 1 pinta 
        (https://es.wikipedia.org/wiki/Pinta)
    1.2. Métodos:
        Constructor
        Getters y setters
        toString
*/
class VasoCerveza {
    int id;
    int mediaPinta;
    int pinta;
    
    VasoCerveza(){
        id=0;
        mediaPinta=0;
        pinta=1;
        
    }
    
    VasoCerveza(int id, int mediaPinta, int pinta){
        this.id=id;
        this.mediaPinta=mediaPinta;
        this.pinta=pinta;
    }

    public int getId() {
        return id;
    }

    public int getMediaPinta() {
        return mediaPinta;
    }

    public int getPinta() {
        return pinta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMediaPinta(int mediaPinta) {
        this.mediaPinta = mediaPinta;
    }

    public void setPinta(int pinta) {
        this.pinta = pinta;
    }

    @Override
    public String toString() {
        return "VasoCerveza{" + "id=" + id + ", mediaPinta=" + mediaPinta + ", pinta=" + pinta + '}';
    }
            
}

class ExecutionTimer {
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

/*FR2 [2 puntos]. Clase Camarero: simula la persona encargada de servir y 
devolver vasos de cerveza. Deberá recibir un nombre como argumento en el 
constructor.
    2.1. Atributos:
    listaVasos: lista que contendrá los Vasos de Cerveza.
    2.2. Métodos:
        Constructor: deberá crear 3 vasos (de tipo aleatorio (0 o 1)) y 
añadirlos a la lista así como asignarse a si mismo un nombre.
        servirCerveza: elegirá un vaso aleatoriamente de la lista, lo sacará de 
ella y lo entregará al cliente para que pueda beber su cerveza.
        devolverCerveza: inserta de nuevo en la lista el vaso devuelto.
        contarVasos: imprime los vasos disponibles en el bar
*/
class camarero {
                public synchronized int crearRuta(int args) {
		int listaVasos=0;
		return listaVasos;
	}

	public synchronized int abrirYContar(String file) {
		int contador = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			// LEER PRIMER CARACTER QUE DEVOLVERA EL ENTERO CORRESPONDIENTE QUE DEFINE AL
			// CARACTER

			int caract = br.read();
			/***************** BUCLE DE LECTURA *****************************/

			// MIENTRAS HAYA CARACTERES CONTARA LAS VECES QUE HACE EL BUCLE
			while (caract != -1) {
				contador++;
				// MOSTRAR CARACTERES
//       		 System.out.print((char)caract);
				// LEE EL SIGUIENTE CARACTER
				caract = br.read();
			}
		} catch (FileNotFoundException e) {
			// NO ENCUENTRA FICHERO
			System.out.println("Error: Fichero no encontrado");
			// MENSAJE DE ERROR
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// ERROR GENERAL
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		} finally {
			// EJECUCION FINAL SI O SI TRAS EL TRY/CATCH
			try {
				// CERRAR ARCHIVO Y BUFFER
				if (fr != null) {
					br.close();
					fr.close();
				}
			} catch (Exception e) {
				// ERROR AL CERRAR EL FICHERO
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}

		return contador;
	}

	public synchronized String mostrarCaracteres(int contador) {
		String mensaje;
		mensaje = " " + contador + " caracteres";
		System.out.println("");

		return mensaje;
	}
}

class HilosClientes extends Thread {
    private contar contar;
	private String argumento;
	private int id;

	HilosClientes(contar cc, String argumento, int id) {
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
