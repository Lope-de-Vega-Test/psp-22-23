/*
NOMBRE: Lucia Marina Ayllón Pozo
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
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main(){
  pid_t hijo1_pid, hijo2_pid, hijo3_pid;
  int status;
  
  hijo1_pid=fork();
  
  if(hijo1_pid==0){
      wait(&status);
    printf("Soy el hijo 1, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    exit(0);
  }
  wait(&status);
  hijo2_pid=fork();
  
  if(hijo2_pid==0){
      
    printf("Soy el hijo 2, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    exit(0);
  }
  wait(&status);
  hijo3_pid=fork();
  
  if(hijo3_pid==0){
    wait(&status);
    printf("Soy el hijo 3, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    exit(0);
  }
  wait(&status);
  printf("Proceso PADRE = %d",getpid());
  exit(0);
}
