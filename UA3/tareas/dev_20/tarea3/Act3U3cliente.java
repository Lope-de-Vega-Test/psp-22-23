package com.mycompany.act3u3cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Lucía Luna
 */


public class Act3U3cliente {

    public static void main(String[] args)  throws IOException{
        
        Socket socket = new Socket("localhost",9999);
        
        //Objeto que permite imprimir caracteres mediante una salida de stream de texto
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        //Esta clase lee el texto desde un flujo de entrada y  almacena los caracteres
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        Scanner scan = new Scanner(System.in);
        //Declaramos texto 
        String texto;
        //Declaramos devuelto
        String devuelto;
        
        //Pedimos mensaje al usuario
        System.out.println("Introducir mensaje: ");
        texto = scan.nextLine();
        out.println(texto);
        devuelto = in.readLine();
        System.out.println(devuelto);
        
        //Cerramos
        socket.close();
        

    }

}
