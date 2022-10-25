#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
/*-------------------------------------------*/
/* gesti�n de se�ales en proceso padre       */
void manejador( int segnal )
{
 printf("Hijo recibe se�al..%d\n", segnal);
}
/*-------------------------------------------*/
int main()
{
  int pid_hijo;  
  pid_hijo = fork(); //creamos hijo   
  
  switch(pid_hijo)
  {
     case -1:
          printf( "Error al crear el proceso hijo...\n");
          exit( -1 );        
     case 0:   //HIJO     	         
          signal(10, manejador); //MANEJADOR DE SE�AL EN HIJO
          while(1) {
          };       
     break;    
     default: //PADRE envia 2 se�ales
          sleep(1);
          kill(pid_hijo, 10);//ENVIA SE�AL AL HIJO 
          sleep(1);
          kill(pid_hijo, 10);//ENVIA SE�AL AL HIJO 
          sleep(1);
     break;
  } 
  return 0;
}
