/*NOMBRE: Lucía Luna Posadas
FECHA: 26/10/22

PARTE PRÁCTICA
FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos
Para evitar complicaciones con máquinas virtuales, si lo prefieres puedes utilizar el compilador online: https://www.onlinegdb.com/online_c_compiler

Notas:
Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\ */




# include <sys/types.h>
  
int main()
{
  int pid;
  pid = fork();
  
  //HIJO 1
  if (pid == -1 ) //Error
  {
    printf("No se ha podido crear el proceso hijo1...");
   
    exit(-1);
  }

  
  
 else if (pid == 0)
  {
    printf ("Soy el proceso hijo1\n");
    printf ("Hijo PID: %d\n", getpid());
    printf ("Mi Padre es PID: %d\n", getppid());
    
   
  }
  else
  {
     
    printf ("Soy el proceso padre\n");
    printf ("Soy el proceso Padre PID: %d\n", getpid());
    printf ("Soy el proceso Hijo1  PID: %d\n", pid);
  }
  
  
  
  pid = fork();
  
  //HIJO2
  if (pid == -1 ) //Error
  {
    printf("No se ha podido crear el proceso hijo2...");
   
    exit(-1);
  }

  
  
  if (pid == 0)
  {
    printf ("Soy el proceso hijo2\n");
    printf ("Hijo PID: %d\n", getpid());
    printf ("Mi Padre es PID: %d\n", getppid());
    
   
  }
  else
  {
     
    printf ("Soy el proceso padre\n");
    printf ("Soy el proceso Padre PID: %d\n", getpid());
    printf ("Soy el proceso Hijo2  PID: %d\n", pid);
  }
  
  
  
  pid = fork();
  //HIJO3
  if (pid == -1 ) //Error
  {
    printf("No se ha podido crear el proceso hijo3...");
   
    exit(-1);
  }

  
  
  if (pid == 0)
  {
    printf ("Soy el proceso hijo3\n");
    printf ("Hijo PID: %d\n", getpid());
    printf ("Mi Padre es PID: %d\n", getppid());
    
   
  }
  else
  {
     
    printf ("Soy el proceso padre\n");
    printf ("Soy el proceso Padre PID: %d\n", getpid());
    printf ("Soy el proceso Hijo3  PID: %d\n", pid);
  }
  
  
  
  

  
}
