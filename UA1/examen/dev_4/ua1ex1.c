
/*
NOMBRE: José Elías Alonso Trenas
FECHA: 26 de octubre de 2022
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
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

/* Funcion Princial*/
int main()
{
    pid_t pid,pid1,pid2,pid3;
    int status1, status2, status3;
    
    if((pid1=fork())==0)
    {
        /*Hijo 1*/
        printf("Soy el primer hijo (%d, hijo de %d)\n",  getpid(), getppid());
    }else
    {
        if((pid2=fork())==0)
        {
            /*Hijo 2*/
            printf("Soy el segundo hijo (%d, hijo de %d)\n",  getpid(), getppid());
        }
        else
        {
            if((pid3=fork())==0)
            {
                /*Hijo 3*/
                printf("Soy el tercer hijo (%d, hijo de %d)\n",  getpid(), getppid());
            }
            else
            {
                /*Esperamos a los tres hijos del padre*/
                wait(pid1, &status1, 0);
                wait(pid2, &status2, 0);
                wait(pid3, &status3, 0);
                printf("Soy el padre %d \n", getpid(), getppid());
            }
        }
    }
    
    return 0;
}
