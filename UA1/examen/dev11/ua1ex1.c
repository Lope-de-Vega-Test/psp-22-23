//Rafael Damian Cristea

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

//Funcion para mostrar los datos, usando getpid() y getppid()
void fMostrar(int dato){
    printf("Soy el proceso hijo %d : \n\t Mi PID es %d, El PID de mi padre es: %d.\n", dato, getpid(), getppid() );
}

int main()
{

for (int i = 0; i < 3; i++) {
    
    pid_t pid = fork();
    
    if(pid == -1){
        //En caso de que nose pueda crear los procesos hijo saltara un error
        printf("No se han podido crear los procesos hijo...");
        exit(-1);
    }
    else if (pid == 0){
        //Proceso hijo
        fMostrar(i); //Usamos la funcion anteriormente declarada
        exit(EXIT_SUCCESS); //En caso de que haya funcionado, se sale del if para evitar la creacion de más hijos
    }
    else{
        //Proceso padre
    }
    
}

for (int i = 0; i < 3; i++) {
    int status;
    pid_t pid = wait(&status); // Wait espera a que los hijos terminen de ejecutarse
}

printf("\nEl PID del PADRE DE TODOS es: %d",  getpid());
}

