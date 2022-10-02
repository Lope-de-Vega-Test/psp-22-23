
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

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
   pid_t proceso, proceso2;//Se crean las derivadas
   int num;
   
   printf("Introduce un numero");//se le pide un numero al usuario
   scanf("%d",&num);
   
   proceso=fork();
   
   if(proceso==-1){//Si ocurre un error el programa termina
       printf("ERROR, No se ha creado al proceso hijo");
       exit(-1);  
   }
   if(proceso==0){//se pone el calculo a realizar en el proceso hijo
       num=num+4;

   }
   else{//se pone el calculo a realizar en el proceso padre
       wait(NULL);
       num=num-5;
   }
  
printf("Resultado: %d\n",num);//Se imprimen los resultados en pantalla
   exit(0);
}
