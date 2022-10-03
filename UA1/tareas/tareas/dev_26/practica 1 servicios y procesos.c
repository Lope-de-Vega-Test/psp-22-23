#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main() {
    pid_t pid, Hijo_pid;
    int var;
    
    printf("Ingrese un numero: ");
    scanf("%d", &var);

    //Creamos la ramificacion de procesos
    pid = fork();

    //Ha ocurrido un error 
    if (pid == -1 ){ 
        printf("No se ha podido crear el proceso hijo...");
        exit(-1);       
    }

    //Nos encontramos en Proceso hijo 
     if (pid == 0 ){        
        printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n",  
            getpid(), getppid() );
            var = var+4;
    }

    //Nos encontramos en Proceso padre
    else{ 
        Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
        printf("Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);
        var = var-5;
    }
    printf("El dato del proceso %d es: %d\n", getpid(),var);
    
    exit(0);

}
