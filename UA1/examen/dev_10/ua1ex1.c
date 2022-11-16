//Incluimos las librerías
#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>

//Llamamos a la función
void mostrarpidhijo(pid_s);
int main() {
    
// Declaramos la variable
pid_t pid;
    
    printf("Soy el padre, mi ID es: %d\n", getpid());
    
        // Blucle para crear el hijo
        for(int i = 0; i < 3; i++ ) {  
            pid = fork(); 
            if (pid == -1) { 
                exit(-1); 
            }
            //Entra si es hijo
            if (pid == 0) { 
                printf("Niño PID: %d; PPID: %d\n", getpid(), getppid() );
                mostrarpidhijo(pid);    
            } else {
                //espera a que finalice el proceso hijo
                pid = wait(NULL); 
                printf("\tSoy el proceso padre: Mi PID es %d, el PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);      
            }
        
        }   
    
}

    //Esta funcion lo que hace es mostrar el pid actual
    void mostrarpidhijo(pid) {printf("%d Finalizado\n", getpid());}
