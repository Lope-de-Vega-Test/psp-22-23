/*
NOMBRE:Carlos Piña de la Torre
FECHA:26/10/2022
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
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
    int num=0; //Declaramos un entero que representara el numero de proceso
    pid_t pid1, pid2, pid3; //Declaramos los procesos
    
    pid1 = fork(); //Creamos el proceso hijo 1
    
    if (pid1 == -1 ) { //Si no se puede crear el hijo
        printf("\nNo se ha podido crear el proceso hijo...");
        exit(-1);       
    }
    
    if (pid1 == 0 ) { //Si el proceso es el hijo 1
        num++;
        mostrar(num);
    } else { //Si no es el hijo 1 es el padre
        pid1 = wait(NULL); //Espera que acabe el proceso
        pid2 = fork(); //Creamos el proceso hijo 2
        
        if (pid2 == -1 ) { //Si no se puede crear el hijo
            printf("\nNo se ha podido crear el proceso hijo...");
            exit(-1);       
        }
        
        if (pid2 == 0 ) { //Si el proceso es el hijo 2
          
            num=2;
            mostrar(num);
        } else { //Si no es el hijo 2 es el padre
            pid2 = wait(NULL); //Espera que acabe el proceso
            pid3 = fork(); //Hijo 3
            
            if (pid3 == -1 ) { //Si no se puede crear el hijo
                printf("\nNo se ha podido crear el proceso hijo...");
                exit(-1);       
            }
            
            if (pid3 == 0 ) { //Si el proceso es el hijo 3
                num=3;
                mostrar(num);
            } else { //Si no es el hijo 3 es el padre
                //Espera que acabe el proceso
                pid3 = wait(NULL); 
                //Mostramos
                printf("\n\nSoy el proceso padre mi numero es %d y mi pid es %d \n - mi hijo 1 es: %d \n - mi hijo 2 es: %d \n - mi hijo 3 es: %d",num, getpid(), pid1, pid2, pid3); 
            }
    }
}
    
    exit(0);
}

void mostrar(num){
    printf("\nSoy el proceso %d (%d, hijo de %d)",num, getpid(), getppid() );
}
