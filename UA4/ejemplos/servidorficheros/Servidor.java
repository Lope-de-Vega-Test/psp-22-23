<<<<<<< HEAD

import java.io.*;
import java.net.*;
import javax.swing.*;

//SERVIDOR
public class Servidor {
	static Integer PUERTO = 44441;
	static public EstructuraFicheros NF;
	static ServerSocket servidor;

	// MAIN
	public static void main(String[] args) throws IOException {		
		String Directorio = "";
		JFileChooser f =new JFileChooser();			
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE EST�N LOS FICHEROS");
		int returnVal = f.showDialog(f, "Seleccionar");
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			Directorio = file.getAbsolutePath();		
			System.out.println(Directorio);
		}
		
		if(Directorio.equals("")){
			System.out.println("Debe seleccionar un directorio .....");
			System.exit(1);
		}
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor Iniciado en Puerto: "+PUERTO);		
	
		while (true) {
			try {
				Socket cliente = servidor.accept();
				System.out.println("Bienvenido al cliente");
				NF = new EstructuraFicheros(Directorio);	
				HiloServidor hilo = new HiloServidor(cliente, NF);
				hilo.start(); // Ejecutamos el hilo
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}// while	
		
	} // main
	
} // ..fin SERVIDOR
=======

import java.io.*;
import java.net.*;
import javax.swing.*;

//SERVIDOR
public class Servidor {
	static Integer PUERTO = 44441;
	static public EstructuraFicheros NF;
	static ServerSocket servidor;

	// MAIN
	public static void main(String[] args) throws IOException {		
		String Directorio = "";
		JFileChooser f =new JFileChooser();			
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE EST�N LOS FICHEROS");
		int returnVal = f.showDialog(f, "Seleccionar");
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			Directorio = file.getAbsolutePath();		
			System.out.println(Directorio);
		}
		
		if(Directorio.equals("")){
			System.out.println("Debe seleccionar un directorio .....");
			System.exit(1);
		}
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor Iniciado en Puerto: "+PUERTO);		
	
		while (true) {
			try {
				Socket cliente = servidor.accept();
				System.out.println("Bienvenido al cliente");
				NF = new EstructuraFicheros(Directorio);	
				HiloServidor hilo = new HiloServidor(cliente, NF);
				hilo.start(); // Ejecutamos el hilo
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}// while	
		
	} // main
	
} // ..fin SERVIDOR
>>>>>>> 66dadf1 (UA4 Assigment)
