#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t pid, Hijo_pid;
    
  int numero = 0;
  
  printf("Introduce un numero:");
  scanf ("%d",&numero);
  printf("%d",numero);
  
 pid = fork();

  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  {        
    printf("\ Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n",  
            getpid(), getppid() );
            numero += 4; //El hijo suma 4
  }
  else    //Nos encontramos en Proceso padre 
  { 
   
          wait(NULL); //Se detiene para realizar todo lo anterior y después continua con el resto de código
         
    printf("\n Soy el proceso padre \n\t Mi PID es %d, El PID de mi hijo es: %d.\n",  
            getppid(), getpid() );
         numero -= 5; //El padre resta 5
         
         
   }
  
  printf("\n la variable vale: %d\n", numero);
 exit(0);
}
