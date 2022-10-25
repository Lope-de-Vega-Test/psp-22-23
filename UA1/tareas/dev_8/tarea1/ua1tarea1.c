#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  // Declaramos la variables
  pid_t pid, Hijo_pid;
  int n = 0;
  
  // Pedimos al usuario un número
  printf("Introduce un número: ");
  scanf("%d", &n);
  //printf("Tu número es: %d", n);
  
  // Restamos como padre 5

  
  printf("\n");
  printf("Restamos como padre al número, 5. ");
  printf("\n");
  printf("El resultado es: %d", n);
  printf("\n");

  // Creamos un proceso hijo
  pid = fork();
  
  if (pid == 0) 
  {
      
      // Sumamos al 4
      n = n + 4;
      
      printf("\n");
      printf("Sumamos como hijo, 4. El resultado es: %d", n);

  }
  else    //Nos encontramos en Proceso padre 
  { 
    Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
    n = n - 5;
    printf("\n");
    printf("El resultado com padre: %d", n);
    
   }
  
  
}

/*
mj@mj-ubuntu12:~/Escritorio/ProgramasC$ gcc ejemplo1Fork.c -o ejemplo1Fork
mj@mj-ubuntu12:~/Escritorio/ProgramasC$ ./ejemplo1Fork
Soy el proceso hijo 
	 Mi PID es 4037, El PID de mi padre es: 4036.
Soy el proceso padre:
	 Mi PID es 4036, El PID de mi padre es: 3586.
	 Mi hijo: 4037 terminó.
mj@mj-ubuntu12:~/Escritorio/ProgramasC$ ps
  PID TTY          TIME CMD
 3586 pts/2    00:00:01 bash
 4044 pts/2    00:00:00 ps
mj@mj-ubuntu12:~/Escritorio/ProgramasC$ 


*/