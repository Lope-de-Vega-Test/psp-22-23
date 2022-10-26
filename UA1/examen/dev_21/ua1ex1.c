/*

NOMBRE: Adrián Luque Mantero (dev_21)
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

#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<fcntl.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>
#include<stdbool.h>

// Declaracion e inicializacion de variable
// que servira para incrementarla cada vez
// que se haga una iteracion de creacion de
// procesos hijo
int x=0;

// Funcion imprimir los datos del proceso
void imprimirDatos(){
    printf("Soy el proceso HIJO numero %d ", x);
    printf("\n");
    printf("- Mi pid es %d ", getpid());
    printf("\n");
    printf("- Mi PADRE tiene pid %d", getppid());
    printf("\n\n");
}

int main()
{
    /*
    // NO SIRVE
     //declaracion e inicializacion
    int pid=getpid();
     //declaracion de 3 variables
    pid_t pid_cp1, pid_cp2, pid_cp3;
    // inicializacion de 3 procesos hijos
    pid_cp1=fork();
    pid_cp2=fork();
    pid_cp3=fork();
    */
    
    // Declaracion de la variable
    pid_t pid_cp;
    
    // Bucle de 3 iteraciones:
    // En cada iteracion se hace un fork
    // (se crea un proceso hijo) y
    // se evalua si se ha creado o no
    // - Si no se ha creado lanza un codigo de salida
    // - Si se ha creado imprime su informacion y lanza un codigo de salida
    for(int i=0; i<3; i++){
        pid_cp=fork();
        switch(pid_cp){
        case -1: // SI HAY ERROR en la creacion del proceso HIJO
            printf("Error en la creacion del PROCESO HIJO");
            printf("\n");
            exit(-1);
        break;
        case 0: // SI SE CREA EL PROCESO HIJO
            imprimirDatos();
            exit(0);
        break;
        }
        x++;
        wait(pid_cp);
    }
    // Despues de haber creado los procesos hijos el proceso padre dice su sentencia
    printf("\n");
    printf("Soy el proceso PADRE.");
    printf("\n");
    printf("- Mi pid es %d ", getpid());
    
    return 0;
}