/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Ignacio
 */
public class cadenaCaracteres {
    
        public static void main(String[] args) {
        
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (in);
        
        try{
            int salida=0; //Variable para salida de do-while posterior
            String texto=""; //Donde guardamos todo el texto leido aqui
            String lectura; //String que lee
            
            do{
                System.out.println("Introduce una cadena de caracteres\nEl caracter de terminación es '*'\n");
                lectura= br.readLine(); //Lee un string entero
                
                for(int i=0;i<lectura.length();i++){
                    //Recorre el string leido y guarda todo hasta encontrar un * si lo hay
                    if(lectura.charAt(i)=='*'){
                        salida=1;//Encontrar el * activa la condición de salida del do-while
                        break;//Este break es necesario para que no siga guardando caracteres tras el *
                    }else{
                        texto+=lectura.charAt(i);
                    }
                }
                
            }while(salida!=1); //si no había un *, repetirá el proceso hasta recibir un string que lo contenga
            
            System.out.println("Total texto almacenado: "+texto);
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }	
    }    
}
