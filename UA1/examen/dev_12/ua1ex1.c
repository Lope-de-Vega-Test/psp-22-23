#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
// Para evitar WARNINGS
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdbool.h>
int codigo;
void funcionfork(){
    if(codigo==1){
        printf("Soy el hijo 1, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());
    }
    if(codigo==2){
        printf("Soy el hijo 2, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());
    }
    if(codigo==3){
        printf("Soy el hijo 3, Mi padre es: %d, Mi PID es: %d\n",getppid(),getpid());

    }
    
    return 0;
}
void main(){
  pid_t pid_hijo1,pid_hijo2,pid_hijo3;
  pid_hijo1=fork();
  if (pid_hijo1 == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo 1...");
    exit(-1);       
  }
  if(pid_hijo1==0){ //Crea el Hijo 1
    codigo=1;
    funcionfork();
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
  codigo=2;
    funcionfork();
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
    codigo=3;
    funcionfork();
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
