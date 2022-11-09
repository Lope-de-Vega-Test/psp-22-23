/*
NOMBRE:Irene Alba Posadas
FECHA:09/11/2022
PARTE PRÁCTICA
FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos

*/

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

//creamos una función independiente a la que llamará cada hijo y mostrará cuál hijo es, su PID y el del padre
int mostrarHijo(int i, int pidPadre){
    
    printf("Soy el hijo %d, mi PID es %d y el PID de mi padre es %d. \n \n", i+1, getpid(), pidPadre);
    
    exit(0);
    
}

int main()
{
   pid_t pid, Hijo_pid;//inicializamos las variables que vamos a usar 
   int pidPadre = getpid();
   int nHijos = 3;
   
   //usamos un for para controlar todos los hijos
   for(int i = 0; i < nHijos; i++){
       
       Hijo_pid = fork();//iniciamos el proceso hijo
       
       if(Hijo_pid == -1){//si Hijo_pid devuelve -1, no se pudo crear el proceso hijo
       
           printf("No se pudo crear el proceso hijo %d... \n", i+1);
           exit(-1);
           
       }
       
       if(Hijo_pid == 0){//si Hijo_pid devuelve 0, se puede crear el proceso hijo y se devuelve la función mostrarHijo
       
           exit(mostrarHijo(i,pidPadre));
           
       }else{//si devuelve cualquier valor mayor que 0, se encuentra en el proceso padre
           
           Hijo_pid = wait(NULL);
           continue;
           
       }
   }
   
   printf("Soy el padre y mi PID es %d. \n", getpid());//por último se mostrará el proceso padre
   
   
   exit(0);
}
