
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include<sys/wait.h>


void main()
{
    
    int numero;
    
    printf("Introduce un numero: "); //Pedimos el numero al usuario
    scanf("%i", &numero); //Recogemos el numero

    pid_t padre, hijo; //Creamos las variables
    padre = fork();
    
    if (padre == -1 ) {
        
        printf("No se ha podido crear el proceso hijo...\n");
        exit(-1);       
    }

    if (padre == 0){ //Estamos en el proceso hijo
        
        numero = numero + 4;
        printf("El numero en el proceso hijo es %i\n", numero); 
    }
     
    else { //Estamos en el proceso padre
        
        hijo = wait(NULL);
        numero = numero - 5;
        printf("El numero en el proceso padre es %i\n", numero);
    }
}
