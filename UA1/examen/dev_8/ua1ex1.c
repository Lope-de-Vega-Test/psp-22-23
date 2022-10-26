/*

NOMBRE: Francisco Javier Campos Gutiérrez 
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
 
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

void muestraHijoProceso(pid_t);
int main() {
    
    pid_t pid, hijo1, hijo2, hijo3;  // Creamos las variables
    
    //printf("I am the Parent, my ID: %d\n", getpid());
    

        for(int i = 0; i < 3; i++ ) {  // Añadimos el bucle donde crearemos el hijo
            pid = fork(); // Creamos un hijo
            if (pid == -1) { 
                exit(-1); // Si entra aqui, para el programa
            }
            else if (pid == 0) { // En este caso los hijos entran y muestran su pid
                printf("Child: PID: %d; PPID: %d\n", getpid(), getppid() );
                muestraHijoProceso(pid);    
                exit(0);
            } else {
                pid = wait(NULL); //espera la finalización del proceso hijo
                //printf("Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);      
            }
        
        }   
        if (pid > 0) {
            printf("\nI am the Parent, my ID: %d",  getpid());      
        }
    
}

    /**
     * Funcion que mostrará el pid actual
     */
    void muestraHijoProceso(pid) {
    
    
        printf("%d says bye!\n", getpid());
    }