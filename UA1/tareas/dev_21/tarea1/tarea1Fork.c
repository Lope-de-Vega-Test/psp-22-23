#include<stdlib.h>
#include<unistd.h>
#include<stdio.h>
#include <sys/types.h>

int main(){
    pid_t child_proccess;

    int x=0;
    printf("Introduce un numero: ");
    scanf("%d", &x); // escanear entrada %d (indicador entero), x variable entero

    child_proccess = fork(); // se crea un proceso (devuelve un entero)

    if(child_proccess == -1) // hubo un error en la creacion del proceso hijo
    {
        printf("No se pudo crear el proceso hijo.");
        exit(-1); // termina la ejecución del programa y devuelve código. Error -1
    }
    else if (child_proccess == 0) // se ejecuta en caso de que el proceso sea HIJO
    {
        x+=4;
        printf("Soy el proceso hijo, y voy a sumar 4 a tu numero: %d", x);
        printf("\n");
        exit(0);
    }
    else // se ejecuta en caso de que el proceso sea PADRE
    {
        wait(child_proccess);
        x-=5;
        printf("Soy el proceso padre, y voy a restar 5 a tu numero: %d", x);
        printf("\n");
        exit(0);
    }
}   
