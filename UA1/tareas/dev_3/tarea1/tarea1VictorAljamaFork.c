#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
void main(){
    
    int num1;
    printf("Introduce el numero que desees: ");
    scanf("%i", &num1);
    printf("El numero introducido es: %i\n",num1 );
    
    pid_t padre, hijo;
    padre = fork();
    
    if (padre==-1){
        printf("No ha sido posible encontrar al hijo\n");
        exit(-1);
    }
    
    if (padre==0){
        num1=num1+4;
        printf("El resultado del hijo es: %i\n", num1);
        exit(0);
    }
    
    else{
        hijo=wait(NULL);
        num1=num1-5;
        printf("El resultado del padre es: %i\n", num1);
    }

}
