#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  
  pid_t p1,p2;
  
  int numero;
  printf("INTRODUCE EL NUMERO QUE QUIERES:"); // AQUI PEDIMOS EL NUMERO CON EL QUE VAMOS A OPERAR
  scanf("%d",&numero);
  
  p1 = fork();
  
  if (p1 == -1 ) 
  {
    printf("SE HA CREADO UN ERROR EN EL PROCESO"); // VEMOS SI SE HA CREADO ALGUN ERROR
          
  }
  
  if (p1 == 0 )  // NO SE HA CREADO NINGUN ERROR Y COMENZAMOS CON EL P1 (HIJO)
  {        
    printf("YO SOY EL HIJO");
    //printf ("MI PROCESO 1 ES:", numero );	

    
    numero = numero + 4;
     
  }
  
  else    
  { 
   p2 = wait(NULL);  //  COMENZAMOS CON EL P2 (PADRE)
   numero = numero - 5; 
   //printf("SOY EL PROCESO PADRE:", numero); 
   }
   printf("numero: %d\n", numero); 
   exit(0);
}
  