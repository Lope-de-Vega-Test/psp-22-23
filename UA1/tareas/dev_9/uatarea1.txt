#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
 void main(){
    int num=0; //Introducimos una variable nueva de tipo INT.
    printf("Introduce un numero: "); //Pedimos la variable.
    scanf("%d",&num); //Leemos la variable.
    pid_t pid, hijo_pid; 
    pid= fork();
    if(pid==-1){ //Ha dado un error.
        printf("No se ha podido crear el proceso hijo");
        exit(-1);
    }
    if(pid==0){ //Proceso del hijo.
        num=num + 4; //Se le suma a la variable 4.
        printf("La variable en el proceso del hijo es: %d\n", num); //Se muestra el resultado de la suma.
        
    }
    else{ //Proceso del padre.
        hijo_pid=wait(NULL); //Se espera hasta que el proceso del hijo acabe.
        num= num - 5; //Se le resta a la variable 5.
        printf("La variable en el proceso del padre es: %d\n", num); //Se muestra el resultado de la resta.
    }
    exit(0);
 }
