// Online C compiler to run C program online
#include <stdio.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t proceso,proceso1;
    
    int numero; //Numero que se le pide al usuario
    
    //Pedimos al usuario el numero con el que operaremos
    printf("introduce el numero que quieras");
    scanf("%d",&numero);
    
    //Se crea el proceso hijo
     proceso=fork();
     
     //Comprobamos que se ha creado correctamente
     if(proceso==-1){
         printf("Error");
     }
     if(proceso==0){
         numero=numero+4;
        
     }
     //Lo que realiza el proceso padre
     else {
    wait(NULL);
    numero=numero-5;
     }
     
     //Nos muestra el resultado por pantalla
    printf("variable: %d\n",numero);
    

}