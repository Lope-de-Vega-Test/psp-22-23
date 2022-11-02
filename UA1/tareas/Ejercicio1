#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>


void main()
{

int numero;

printf("Introduce un numero: ");
scanf("%d",&numero);

//Declaramos las variables
pid_t pid, Hijo_pid;

pid = fork();

if (pid == -1)
{

    printf("\nEl proceso hijo no se ha podido crear");

}

if (pid == 0)
{

numero = numero  + 4;
printf("Soy el proceso hijo \n\t Mi PID es %d, el PID de mi padre es: %d.\n", getpid(), getppid());
printf("\n Valor: %d",numero);
printf("\n");

}

else
{

wait(NULL);
   printf("\n Soy el proceso padre:\n\t Mi PID es %d, el PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);
   numero = numero - 5;
printf("\n Valor: %d",numero,"\n");
  printf("\n");
   }

}
