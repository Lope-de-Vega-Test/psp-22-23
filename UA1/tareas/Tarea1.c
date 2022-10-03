#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t proceso1;
  
  int numero;//Numero que pediremos al usuario
  
  //Pedimos al usuario el numero para operar con él
  printf("Introduzca un numero");
  scanf("%d", &numero);
  
  //Creamos el proceso hijo
  proceso1 = fork();
  
  //Comprobamos que el proceso se haya creado correctamente
  if(proceso1 == -1){
      printf("Error al crear el proceso");
  }
  
  //Acciones del proceso hijo
  else if(proceso1 == 0){
      numero = numero + 4;
  }
  
  //Acciones del proceso padre
  else{
      wait(NULL); //Espera a que la ejecución el proceso hijo finalice
      numero = numero - 5;
  }
    //Muestra los resultados por pantalla
      printf("Variable:%d\n",numero);
}
