#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
    int num;
    pid_t pid, Hijo_pid; //pid_t representa la identificación del proceso, se usa para declararlos
    
    
    printf("Introduzca un numero: ");
    scanf("%d",&num); //Numero que pasaremos a los procesos
    
    pid = fork(); //Creamos el proceso
    
    if (pid == -1 ) { //Si no se puede crear el hijo
        printf("No se ha podido crear el proceso hijo...");
        exit(-1);       
    }
    
    if (pid == 0 ) { //Si el proceso es el hijo
        //Sumamos 4 al numero y se muestra
        num+=4;
        printf("Soy el proceso hijo \n\t Mi numero es ",num, getpid(), getppid() );
    } else { //Si no es el hijo, es el padre
        //Espera que acabe el proceso hijo en caso de estar en curso
        Hijo_pid = wait(NULL); 
        //Restamos 5 y lo mostramos
        num-=5;
        printf("\n Soy el proceso padre:\n\t Mi numero es ",num, getpid(), getppid() ,pid); 
    }
    
    printf("%d",num);
    exit(0);
}
