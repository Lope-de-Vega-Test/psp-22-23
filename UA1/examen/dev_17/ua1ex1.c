/*
NOMBRE: Jose Pablo Jaraba
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

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main(){
    //declaramos las variables que nos serivarn como procesos hijos.
    pid_t pid_hijo1,pid_hijo2,pid_hijo3;
    //llamamos al primero hijo con fork().
    pid_hijo1=fork();
    //si fork() devuelve 0, el proceso ha sido creado correctamente, por lo que se imprime por pantalla la info y se cierra.
    if(pid_hijo1==0){
        printf("Hijo1 ,mi PID es: %d; el PID de mi padre es: %d.\n",getpid(),getppid());
        exit(0);
    }else{
        //cuando sale de hijo 1, pasa por hijo2, que lo crea y muestra la info
        pid_hijo2=fork();
        if(pid_hijo2==0){
            printf("Hijo2 ,mi PID es: %d; el PID de mi padre es: %d.\n",getpid(),getppid());
            exit(0);
        }else{
            //cuando sale de hijo2, toca iniciarse a hijo3, con fork().
            pid_hijo3=fork();
            if(pid_hijo3==0){
                printf("Hijo3 ,mi PID es: %d; el PID de mi padre es: %d.\n",getpid(),getppid());
                exit(0);
            }
        }
    }
    
    //esperamos la finalizacion de los procesos hijos
    pid_hijo1 = wait(NULL);
    pid_hijo2 = wait(NULL);
    pid_hijo3 = wait(NULL);
    //ya estamos en el proceso padre, ya que noe stamos en ninguno de los hijos, por lo que podemos mostrar la informacion de padre
    printf("Proceso PADRE = %d\n",getpid());
    exit(0);
}
