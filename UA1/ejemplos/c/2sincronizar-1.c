#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
/*-------------------------------------------*/
/* gesti�n de se�ales en proceso padre       */
void manejador( int segnal )
{
 printf("Hijo recibe se�al..%d\n", segnal);
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
          signal(SIGUSR1, manejador); //MANEJADOR DE SE�AL EN HIJO
          while(1) {};       
     break;    
     default: // el proceso PADRE... envia 2 se�ales
          sleep(1);
          kill(childProcess, SIGUSR1);//ENVIA SE�AL AL HIJO 
          sleep(1);
          kill(childProcess, SIGUSR1);//ENVIA SE�AL AL HIJO 
          sleep(1);
     break;
  } 
  return 0;
}
