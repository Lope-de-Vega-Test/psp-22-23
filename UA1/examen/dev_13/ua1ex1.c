/*
NOMBRE:
FECHA:
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
#include <sys/wait.h>

//Funcion que recibe el numero del hijo y lo imprime junto al Pid y al Pid del padre por la salida estandar
void comprobarPid(int numeroHijo){
    printf("Soy el hijo %d \t mi PID es %d \t y el de mi padre es %d \n",  
            numeroHijo+1, getpid(), getppid());
}

int main()
{   
    //variable que almacena el pid del proceso padre para filtrar el paso a los hijos 
    pid_t pidpadre = getpid();
    
    //variable que almacenará el numero del hijo (1 2 o 3) en el que nos encontramos
    int numeroHijo=0;
    
    //variable que invocará al fork
    pid_t pid;
    
    for(numeroHijo=0;numeroHijo<3;numeroHijo++){
        
    //en caso de ser el padre, el proceso creará hijos
    if(getpid()==pidpadre){
        
    //crea un proceso hijo  
    pid = fork();
      
    switch(pid){
        
    //habrá ocurrido un error al crear el proceso y lo mostramos por pantalla
        case -1:
        printf("No se ha podido crear el proceso hijo");
        break;
        
    //nos encontramos en el proceso padre
        case 0:
        
    //llamamos a la funcion que muestra la informacion por pantalla
        comprobarPid(numeroHijo);   
        break;
        
    //nos encontramos en el proceso hijo
        default:
        
    //esperamos a que el proceso hijo muera
        wait(NULL);
        
    //en caso de que esteos en el ultimo hijo y para que se ejecute una sola vez, mostraremos la informacion del padre
        if(numeroHijo==2){
            printf("Hola, soy el padre y mi pid es %d \n",  
            getpid());
        }
        break;
        }
    }
}

    return 0;
}
