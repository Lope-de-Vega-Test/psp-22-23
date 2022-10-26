#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
/*-------------------------------------------*/
/* gestión de señales en proceso padre       */
void manejador( int segnal )
{
 printf("Hijo recibe señal..%d\n", segnal);
}
/*-------------------------------------------*/
int main()
{
  int childProcess;  
  childProcess = fork(); //creamos hijo   
  
  switch(childProcess)
  {
     case -1: // error
          printf( "Error al crear el proceso hijo...\n");
          exit( -1 );        
     case 0:   // el proceso hijo...    	         
          signal(SIGUSR1, manejador); //MANEJADOR DE SEÑAL EN HIJO
          while(1) {};       
     break;    
     default: // el proceso PADRE... envia 2 señales
          sleep(1);
          kill(childProcess, SIGUSR1);//ENVIA SEÑAL AL HIJO 
          sleep(1);
          kill(childProcess, SIGUSR1);//ENVIA SEÑAL AL HIJO 
          sleep(1);
     break;
  } 
  return 0;
}
