
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

// Author: Daniel Fernandez Balsera

void main()
{

int numero;
// Introducimos el primer valor numerico

printf("Introduzca un numero: ");
scanf("%d",&numero);

// declaramos dos variables como un proceso

pid_t pid, Hijo_pid;

// duplicamos el proceso

pid = fork();

// Primer control de errores, si el valor que tiene pid, es menor que 1, es porque no se ha podido generar ningun proceso hijo

if (pid == -1)
{

    printf("\nNo se ha podido crear el proceso hijo..");
    
}

// Segundo control de errores, si el valor de pid es 0, te suma 4 al numero introducido y te indica el PID del hijo y el padre
if (pid == 0)
{

numero = numero  + 4;
printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n", getpid(), getppid());
printf("\n Valor: %d",numero);
printf("\n");

}

// Ultimo control de errores, si no se diese ninguno de los anteriores casos, te indica el PID principal, el del padre y el del hijo
// tambien resta 5 al valor introducido
else
{

wait(NULL);
   printf("\n Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);          
   numero = numero - 5;
printf("\n Valor: %d",numero,"\n");
  printf("\n");
   }

}


