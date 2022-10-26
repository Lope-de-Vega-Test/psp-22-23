/*
NOMBRE: Javier Castilla Tejeda
FECHA: 26/10/22
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
#include <sys/wait.h>
#include <unistd.h>
#include <stdbool.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main(){
    //Declaramos los PIDS
    pid_t pid1, pid2, pid3;
    int status1, status2, status3;
    //Si el PID del primer hijo es igual a 0 ejecutamos el primer hijo.
    if ( (pid1=fork()) == 0 ) {//Primer hijo. Mostramos el PID del padre y el suyo.
        printf("Soy el primer hijo, mi PID es: %d, hijo de %d\n",  getpid(), getppid());
    }
    else{
        //Paramos el PID del hijo 1 para que se muestre el primero.
        waitpid(pid1, &status1, 0);
        //Si el PID del segundo hijo es igual a 0 ejecutamos el segundo hijo.
        if ( (pid2=fork()) == 0 ){//Segundo hijo. Mostramos el PID del padre y el suyo.
            printf("Soy el segundo hijo, mi PID es: %d, hijo de %d\n",  getpid(), getppid());
        }
        else{//Paramos el PID del hijo 2 para que se muestre despues del primero.
            waitpid(pid2, &status2, 0);
            //Si el PID del tercer hijo es igual a 0 ejecutamos el tercer hijo.
            if ( (pid3=fork()) == 0 ){//Tercer hijo. Mostramos el PID del padre y el suyo.
                printf("Soy el tercer hijo, mi PID es: %d, hijo de %d\n",  getpid(), getppid());
        }
        else{//Paramos el PID del tercer hijo para que se muestre despues del segundo 
            waitpid(pid3, &status3, 0);
            //Al haber parado todos los hijos mostramos el PID del padre.
            printf("Soy el padre, mi PID es: %d\n", getpid());
        }
    }
    }
}
