#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
void main() {
  int n;
  printf("Escribe un numero: ");
  scanf("%d", &n);
  
  pid_t pid, Hijo_pid;
  pid = fork(); //aqui se crea el proceso hijo

  if (pid == -1 ) // si fork devuelve 1 ha ocurrido un error
  {
   printf("No se ha podido crear el proceso hijo...");
   exit(-1);       
  }
  if (pid == 0 )  //Si fork nos devuelve 0, se ha creado el proceso hijo correctamente
  {    
   printf("Soy el Hijo y voy a sumarle 4 a %i\n",n);
   printf("El resultado es %d\n",n+4);
  }
  else    //Aqui hemos llegado por el proceso del Padre, ya que no ha devuelto ni 0 ni -1
  { 
   Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
   printf("Soy el Padre y voy a restar 5 a %i\n",n);
   printf("El resultado es %d\n",n-5);
  }
   exit(0);
}