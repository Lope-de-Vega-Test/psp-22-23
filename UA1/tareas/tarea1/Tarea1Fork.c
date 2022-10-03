#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t pid;
  int numero;
  printf("Introduzca un número:\n");
  scanf("%d",&numero);
  pid = fork();
  
if (pid==-1) //Ha ocurrido un error 
  {
    printf("No se pudo crear el proceso hijo.");
    exit(-1);       
  }
if (pid==0)    //Proceso hijo 
  { 
    pid=wait(NULL);   //Espera a la ejecucuón del proceso padre
    numero+=4;
    //printf("%d",numero);
  }
else    //Proceso padre 
  { 
   numero-=5;
   //printf("%d\n",numero);
   }
   printf("%d\n",numero); //Muestra el valor de número de ambos procesos primero el resultado del proceso hijo y el segundo resultado del proceso padre
   exit(0);
   
}