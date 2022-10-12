#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t pid, Hijo_pid;//inicializamos las variables del proceso
  
  int n;//inicializamos la variable que pedimos
  
  printf("Introduce un número:");
  scanf("%d", &n);//pedimos y registramos el número
  
  
  Hijo_pid = fork();//creamos un proceso

  if (Hijo_pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  
  if (Hijo_pid == 0 )  //Nos encontramos en Proceso hijo 
  {        
    printf("Soy el proceso hijo.");
    n += 4;
    printf("El número ahora es: %d\n", n);//nos indica que es el proceso hijo y realiza la operación de sumar 4 a la variable
    
  }
  else    //Nos encontramos en Proceso padre 
  { 
   Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
   printf("Soy el proceso padre.");  
   n -= 5;
   printf("El número ahora es: %d\n", n);//nos indica que es el proceso padre y realiza la operación de restar 5 a la variable 
   }
   
   exit(0);
}