/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

void main(){
    
 pid_t pid, pid_hijo1,pid_hijo2,pid_hijo3;//Inicializamos las variables del proceso.
 
  pid = fork(); //creamos el primer proceso.
  
    if (pid == -1 ) //Ha ocurrido un error
  {
         printf("NO SE PUEDE CREAR EL PROCESO HIJO...");
            exit(0); 
  }
  
  pid_hijo1=fork();//creamos el proceso del primer hijo.
  
     if(pid_hijo1==0)
  {
         printf("Soy el hijo 1, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
            exit(0);
  }
  
  pid_hijo2=fork();//creamos el proceso del segundo hijo.
     if(pid_hijo2==0)
  {
        printf("Soy el hijo 2, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
            exit(0);
  }
  
  pid_hijo3=fork();//creamos el proceso del tercer hijo.
    if(pid_hijo3==0)
  {
        printf("Soy el hijo 3, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
            exit(0);
  }
  
     printf("Proceso PADRE = %d\n",getppid());//mostramos el proceso del padre con su pid
  exit(0);
}
