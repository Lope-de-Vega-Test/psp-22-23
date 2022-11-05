/*
NOMBRE:Alfonso Alcaraz Valera
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
//variable global para conocer el id del padre
pid_t padre;
//Funcion para que detecta si es padre o hijo y devuelve su id
void Informacion(int i,pid_t padre) {
    
        if(padre){
            printf("Soy el proceso padre:\n\t Mi PID es %d.\n\t  terminó.\n",  padre);
        }else{
             sleep(1);
            printf( "PID = %d, mi padre=%d, hijo numero = %d\n", getpid(),getppid(), (i+1));
        }
       
        
}
void main() {
    //variable de proceso 
  pid_t pid[3], padre;
  //id del padre
  padre=getpid();
   //queremos crear 3 hijos
  for(int i=0;i<3;i++){
        //iniciamos un fork por cada hijo
         pid_t se_pid = fork();
         pid[i]=se_pid;
     
     if (pid[i] == -1 ) //Ha ocurrido un error 
    {
        printf("No se ha podido crear el proceso hijo...");
        exit(-1);       
    }
    if (pid[i] == 0 )  //Nos encontramos en Proceso hijo 
     { 
         
         Informacion(i,NULL);//llamamos a la informacion de id
     
        exit(0);// acabamos con el proceso hijo
    	
    }else{
        wait(pid[i]);//espera la finalización del proceso hijo
        //si es el ultimo bucle llama al padre
        if(i==2){
                 
                 Informacion(i,padre);//llama a la informacion de id
       }
     
    }
      
  }

  }