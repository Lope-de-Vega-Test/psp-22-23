#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
void main(){
  pid_t pid_hijo1,pid_hijo2,pid_hijo3;
  pid_hijo1=fork();
  if (pid_hijo1 == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo 1...");
    exit(-1);       
  }
  if(pid_hijo1==0){ //Crea el Hijo 1
    printf("Soy el hijo 1, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  pid_hijo2=fork();
  if (pid_hijo2 == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo 2...");
    exit(-1);       
  }
  if(pid_hijo2==0){ //Crea el Hijo 2
    printf("Soy el hijo 2, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  pid_hijo3=fork();
  if (pid_hijo3 == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo 3...");
    exit(-1);       
  }
  if(pid_hijo3==0){ //Crea el Hijo 3
    printf("Soy el hijo 3, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  else{
      //Nos encontramos en Proceso Padre
  }
  pid_hijo1 = wait(NULL); //espera la finalización del proceso hijo 1
  pid_hijo2 = wait(NULL); //espera la finalización del proceso hijo 2
  pid_hijo3 = wait(NULL); //espera la finalización del proceso hijo 3
  printf("Soy el Proceso PADRE, mi PID es: %d\n",getpid());
  exit(0);
}
