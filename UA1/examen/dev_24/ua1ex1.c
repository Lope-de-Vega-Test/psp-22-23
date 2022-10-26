/*
NOMBRE: Luis Manuel Rodríguez lópez
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

//declaramos las librerias para que no nos salten warnings
#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdbool.h>

void main(){
  //declaramos las variables del padre y de los hijos
  pid_t pid_hijo1,pid_hijo2,pid_hijo3;

  //realizamos el fork del primer hijo
  pid_hijo1=fork();
  //control de errores
  if (pid_hijo1 == -1) {
    printf("no se ha podido crear el hijo");
    exit(-1);
    
}
//usamos un if para crear el hijo
  if(pid_hijo1==0){
    printf("Soy el hijo 1, Mi padre es= %d, Mi PID es= %d\n",getppid(),getpid());
    exit(0);
  }
  pid_hijo1 = wait(NULL);
  //realizamos el fork del segundo hijo
  pid_hijo2=fork();
    //control de errores
  if (pid_hijo2 == -1) {
    printf("no se ha podido crear el hijo");
    exit(-1);
}

    //usamos un if para crear el hijo
  if(pid_hijo2==0){
    printf("Soy el hijo 2, Mi padre es= %d, Mi PID es= %d\n",getppid(),getpid());
    exit(0);
  }
  pid_hijo2 = wait(NULL);
  //realizamos el fork del tercer hijo
  pid_hijo3=fork();
      //control de errores
  if (pid_hijo3 == -1) {
    printf("no se ha podido crear el hijo");
    exit(-1);
    
}
  //usamos un if para crear el hijo
  if(pid_hijo3==0){
    printf("Soy el hijo 3, Mi padre es= %d, Mi PID es= %d\n",getppid(),getpid());
    exit(0);
  }
  pid_hijo3 = wait(NULL);
  //decimos cual es el proceso padre
  printf("Proceso PADRE = %d\n",getpid());
  exit(0);
}

