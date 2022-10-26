/*

NOMBRE: CARLOS VILLATORO NEVADO
FECHA: 26/10/2022

PARTE PRÁCTICA

FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos

Para evitar complicaciones con máquinas virtuales, si lo prefieres puedes utilizar el compilador online: https://www.onlinegdb.com/online_c_compiler

Notas:

Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdbool.h>
/*Para la realizacion de este programa es muy importante añadir sleep cada vez que creemos un hijo, para que se muestren
de manera ordenada en pantalla.*/

//Este programa incluye Control de errores que se muestra con comentarios abajo

//Programa realizado por CARLOS VILLATORO NEVADO, alumno de 2º de DAM
int main()
{
    //Creamos el padre y los hijos
    pid_t pid;
  
    // Hacemos un fork con el padre
    pid = fork();
    //Hacemos control de errores, para que en caso de no poder crearlo, nos lo muestre por pantalla
  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
    //Si al realizar el fork devuelve 0, en ese caso sería el hijo
    if (pid == 0) {
    //Despues de llamar al hijo, hacemos un sleep hasta llamar al siguiente
        sleep(3);
    //Ahora mostramos el PID del hijo y el de su padre
        printf("Soy el hijo 1, mi pid es: %d, mi padre es = %d\n",
               getpid(), getppid());
        }
        else {
    //volvemos a hacer un fork al mismo padre y nos mostrara el 2o hijo
        pid = fork();
    //Hacemos control de errores, para que en caso de no poder crearlo, nos lo muestre por pantalla
    if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
        if (pid == 0) {
            sleep(2);
        printf("Soy el hijo 2, mi pid es: %d, mi padre es = %d\n",
                   getpid(), getppid());
        }
        else {
    //por ultima vez hacemos un fork y nos mostrara el 3er hijo
        pid = fork();
    //Hacemos control de errores, para que en caso de no poder crearlo, nos lo muestre por pantalla
    if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
        if (pid == 0) {
        printf("Soy el hijo 3, mi pid es: %d, mi padre es = %d\n",
                       getpid(), getppid());
            }
    // Si el valor del fork es mayor a 0, en ese caso seria el padre y lo mostramos
            else {
    //Mostramos el PID del proceso padre y cerramos el programa
                sleep(3);
                printf("SOY EL PADRE, MI PID ES --> %d\n", getpid());
            }
        }
    }
    exit(0);
}