/*
NOMBRE: Ignacio Javier Martínez Sánchez
FECHA: 26-10-2022
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

void muestraId(int idHijo, int idPadre){
    printf("Soy el hijo %d (%d, hijo de %d)\n", idHijo-idPadre, idHijo, idPadre);
}

int main() {
    pid_t pidHijo1, pidHijo2, pidHijo3; //Lo usaremos para el 
                                        //pid de cada hijo
                                        
    int status1, status2, status3; //Estos int nos servirán
                                //para asegurarnos de que el padre
                                //espere a sus hijos
    
    if ( (pidHijo1=fork()) == 0 ){ /* hijo 1, ejecuta el último*/
	  //int status1;
   	  //printf("Soy el hijo 1 (%d, hijo de %d)\n", getpid(),getppid());
   	  muestraId(getpid(),getppid());
   	  
	}else if ( (pidHijo2=fork()) == 0 ){ /* hijo 2, ejecuta el segundo*/
	  //int status2;
   	  //printf("Soy el hijo 2 (%d, hijo de %d)\n", getpid(),getppid());
   	  muestraId(getpid(),getppid());
   	  
	}else if ( (pidHijo2=fork()) == 0 ){ /* hijo 3, ejecuta el primero*/
	  //int status3;
   	  //printf("Soy el hijo 3 (%d, hijo de %d)\n", getpid(),getppid());
   	  muestraId(getpid(),getppid());
   	  
	}else{ /* padre */
          wait(&status1);
          wait(&status2);
          wait(&status3); //Esto hace que el padre no ejecute hasta
                        //que sus tres hijos acaben
                        
	  printf("Soy el padre %d y el padre de todos es %d)\n", getpid(),getppid());
	}
    

    return 0;
}





