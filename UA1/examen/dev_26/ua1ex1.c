/*
NOMBRE:FRANCISCO JAVIER VALVERDE LUQUE
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

//DECLARACIÓN FUNCIÓN PARA CREAR PROCESOS HIJOS
void crearHijos(pid_t pid_hijos[], int contador);

void main(){
    //DECLARACIÓN DE VARIABLES
    pid_t pid_hijos[3], hijo_pid;
    int contador = 0;
    
    //LLAMADA DE LA FUNCIÓN CREAR HIJOS
    crearHijos(pid_hijos,contador);

    //ESPERAMOS A QUE MUESTREN LOS DATOS LOS HIJOS
    hijo_pid = wait(NULL);
    //MOSTRAMOS LOS DATOS DEL PADRE
    printf("Soy el padre con PID %d", getpid());
    exit(0);
}

//FUNCION PARA CREAR HIJOS DEL PROCESO PRINCIPAL
void crearHijos(pid_t pid_hijos[], int contador){
    for(int i=0;i<3;i++){
        contador++;
        pid_hijos[i] = fork();
        if(pid_hijos[i] == 0){
            //MOSTRAMOS LOS DATOS DE LOS HIJOS Y EL PID DEL PADRE
            printf("Soy el hijo %d, Mi padre es= %d, Mi PID= %d\n",contador,getppid(),getpid());
            sleep(1);
            exit(0);
        }
        else{
            printf("Creando hijo...\n");
            sleep(1);
        }
    }
}