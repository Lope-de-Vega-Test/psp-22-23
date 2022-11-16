/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javahere;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

public class Tarea2ProcessBuilder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        System.out.println("Escribe cualquier texto: ");

        String texto = sc.nextLine();
        char charSeq[] = texto.toCharArray();

        for(char c : charSeq) {
            if (Character.valueOf(c) != '\u002A') {
                System.out.print(c);
            } else {
                break;
            }
        }

        /*try {
            while ( Character.valueOf(c = (char)br.read())!='\u002A') {
                done=true;
            }
        } catch (Exception e){
            System.out.println("Error");
        }
         */
        try{is.close();} catch (Exception e) {e.printStackTrace();}
    }
}
