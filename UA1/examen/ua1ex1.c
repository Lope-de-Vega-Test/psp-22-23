#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>


//funcion para mostrar la informacion de cada proceso
void informacion(int numero);
void main(){
    int contador=0;
    //declaración de los procesos
  pid_t pid_hijo1,pid_hijo2,pid_hijo3;
  //Realizamos los procesos
  pid_hijo1=fork();
   contador++;
    if(pid_hijo1==0){
   informacion(contador);
  }
  
  pid_hijo2=fork();
 contador++;
 
    if(pid_hijo2==0){
   informacion(contador);
  }
  
  pid_hijo3=fork();
 contador++;
 
     if(pid_hijo3==0){
   informacion(contador);
  }
    else{
      //Esperamos al primer hijo
      wait(pid_hijo1,0);
      //Esperamos al segundo hijo
      wait(pid_hijo2,0);
      //Esperamos al tercer hijo
      wait(pid_hijo3,0);
  }
     printf("Proceso PADRE PID = %d\n",getpid());
  exit(0);
}
void informacion(int numero){

    printf("Soy el hijo %d (%d , Mi padre es = %d)\n",numero,getpid(),getppid());
    exit(0);
       
}
