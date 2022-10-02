
/*
    CFGS Desarrollo Aplicaciones Multiplataforma
    Programación Servicios y Procesos
    CES Lope de Vega - Córdoba (Spain)
    Curso 2022/2023
*/

/*
    Tarea 1 - Programación de Procesos en C
    Criterios a), f), g), h)
    Crea un programa en C que cree un proceso (existirán en realidad dos procesos, uno padre y el otro hijo). 
    El programa principal pedirá al usuario una variable y el proceso padre restará 5 a dicha variable, mientras que el proceso hijo le sumará 4. 
    Muestra todos los valores por pantalla.
*/
//import java.*;
 
//package ua1;

// https://medium.com/@pelensky/java-tdd-with-junit-without-using-an-ide-cd24d38adff
#include <stdio.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t proceso,proceso1;
    
    int numero; //Numero que se le pide al usuario
    
    //Pedimos al usuario el numero con el que operaremos
    printf("introduce el numero que quieras");
    scanf("%d",&numero);
    
    //Se crea el proceso hijo
     proceso=fork();
     
     //Comprobamos que se ha creado correctamente
     if(proceso==-1){
         printf("Error");
     }
     if(proceso==0){
         numero=numero+4;
        
     }
     //Lo que realiza el proceso padre
     else {
    wait(NULL);
    numero=numero-5;
     }
     
     //Nos muestra el resultado por pantalla
    printf("variable: %d\n",numero);
    

}
