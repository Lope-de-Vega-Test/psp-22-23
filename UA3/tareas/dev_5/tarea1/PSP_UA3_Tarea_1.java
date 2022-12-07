/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package ua3.tareas.dev_5.tarea1;

/**
 *
 * @author elena
 */


import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PSP_UA3_Tarea_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Scanner sca =new Scanner(System.in);
      String path="";
      System.out.println("introduce un path: ");
      do{
        path=sca.nextLine();
        InetAddress dir = null;
        System.out.println("========================================================");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
          //LOCALHOST
         dir = InetAddress.getByName("localhost");
         pruebaMetodos(dir);//
 
         //URL	www.google.es
         System.out.println("========================================================");
         System.out.println("SALIDA PARA UNA URL:");
         dir = InetAddress.getByName(path);
         pruebaMetodos(dir);
 
          // Array de tipo InetAddress con todas las direcciones IP
          //asignadas a google.es
          System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
          InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
          for (int i = 0; i < direcciones.length; i++)
               System.out.println("\t\t"+direcciones[i].toString());
 
         System.out.println("========================================================");
 
       } catch (UnknownHostException e1) {e1.printStackTrace();}
     }

      while(!path.equals("localhost"));
      
    }
       
     private static void pruebaMetodos(InetAddress dir) {
           System.out.println("\tMetodo getByName():  " + dir);
         InetAddress dir2;
         try {
             dir2 = InetAddress.getLocalHost();
             System.out.println("\tMetodo getLocalHost(): " + dir2);
         } catch (UnknownHostException e) {e.printStackTrace();}
 
         // USAMOS METODOS DE LA CLASE
         System.out.println("\tMetodo getHostName(): " + dir.getHostName());
         System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
         System.out.println("\tMetodo toString(): " + dir.toString());
         System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
         //pruebaMetodos
 
   
} 
        
        
    }
    
