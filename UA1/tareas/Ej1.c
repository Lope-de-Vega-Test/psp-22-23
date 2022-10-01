#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
int main()
{
  pid_t pid, Hijo_pid;
  int numero;	//Numero es el que pediremos despues
  
    printf ("Introduce un numero\n");
      scanf ("%d", &numero);//Pedimos el numero con el que trabajaremos en los dos procesos
      
  pid = fork();
  //Designamos ambos procesos y el fork creara ambos y hara de pid el proceso padre

  if (pid == -1)		//Ha ocurrido un error creando el proceso hijo
    {
      printf ("No se ha podido crear el proceso hijo");
      exit (-1);
    }
  if (pid == 0)			//Nos encontramos en el proceso hijo
    {
      numero+=4;//El proceso hijo debe sumar 4 y mostrarlo en pantalla
      printf("Soy el proceso hijo \n\t Me han pasado un numero y le he sumado 4 dando como resultado %d.\n",
	 numero);
    }
  else				//Nos encontramos en Proceso padre 
    {
      Hijo_pid = wait (NULL);//espera la finalizacion del proceso hijo
      numero-=5;//El proceso padre debe restar 5 y mostrar el resultado
      printf("Soy el proceso padre:\n\t Me han pasado un numero y le he restado 5 dando como resultado %d.\n",
	 numero);
    }
  return(0);
}