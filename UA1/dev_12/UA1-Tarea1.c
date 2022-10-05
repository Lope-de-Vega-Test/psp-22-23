#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
    
    printf("Introduce un numero: ");
    int numero; 
    scanf("%d",&numero);
    
  pid_t pid, Hijo_pid;
  pid = fork();

  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  {        
    printf("Soy el proceso hijo \n\t Mi numero es ",numero,  
            getpid(), getppid() );
            numero+=4;
  }
  else    //Nos encontramos en Proceso padre 
  { 
   Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
   printf("\n Soy el proceso padre:\n\t Mi numero es ",numero,  
            getpid(), getppid() ,pid); 
   numero-=5;
   }
   
   printf("%d",numero);
   exit(0);
}