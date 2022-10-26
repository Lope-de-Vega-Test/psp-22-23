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

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        InputStreamReader istream = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(istream);
        char c;
        boolean done=false;
        System.out.println("Escribe cualquier texto: ");
        
        /*try {
            while ( Character.valueOf(c = (char)br.read())!='\u002A') {
                done=true;
            }
        } catch (Exception e){
            System.out.println("Error");
        }
        */
        for(char c : charSeq){
            if(Character.valueOf(c)!='\u002A')
                System.out.print(c);
            else break;
        }
        
        // ProcessBuilder pb = new ProcessBuilder("java", );
    }
}
