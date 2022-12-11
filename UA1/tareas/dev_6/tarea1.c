#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t pid, Hijo_pid;
  
  int numero;
  
  printf("Introduce un número:");
  scanf("%d", &numero);
  
  
  Hijo_pid = fork();

  if (Hijo_pid == -1 ) 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  
  if (Hijo_pid == 0 )  
  {        
    printf("Soy el proceso hijo.");
    numero += 4;
    printf("El número ahora es: %d\n", numero);
    
  }
  else  
  { 
   Hijo_pid = wait(NULL); 
   printf("Soy el proceso padre.");  
   numero -= 5;
   printf("El número ahora es: %d\n", numero);//nos indica que es el proceso padre y realiza la operación de restar 5 a la variable 
   }
   
   exit(0);
}
