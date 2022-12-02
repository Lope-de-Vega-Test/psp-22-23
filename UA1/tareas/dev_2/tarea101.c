#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>


void main() {
    //Pedimos un valor al usuario
    int valor,p;
    printf("Introduzca un numero entero: ");
    scanf("%d", &valor);
   
    pid_t pid, Hijo_pid;
    pid = fork();
    

  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  {     
      valor=valor+4;
    printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n\t El valor es %d \n",  
            getpid(), getppid(),valor );	
  }
  else    //Nos encontramos en Proceso padre 
  { 
   Hijo_pid = wait(pid); //espera la finalización del proceso hijo
   valor=valor-5;
   printf("Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n\t El valor es %d \n",  getpid(), getppid(), pid,valor);          
   }
   //Muestra el valor de cada proceso
   printf("El valor es del proceso %d es %d \n",getpid(),valor);
   exit(0);
}
