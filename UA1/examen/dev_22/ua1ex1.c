/*
NOMBRE: Ignacio Javier Martínez Sánchez
FECHA: 26-10-2022
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
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\
*/
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void muestraId(int idHijo, int idPadre){
    /////// FUNCIÓN DE MUESTRA ID PADRE-HIJO ///////
    
    printf("Soy el hijo %d (%d, hijo de %d)\n", idHijo-idPadre, idHijo, idPadre);
    
    /* Cada hijo tiene un identificador cuyo valor es el del
    padre +1 si es e primero, +2 si es el segundo, +3 si es el
    tercero y así si hubiese más hijos, entonces idHijo-idPadre
    debe darme el nº del hijo teniendo solo su identificador y
    su padre sin tener que poner un printf por cada hijo y poner
    hijo 1, hijo 2, hijo 3...
    
    Por otra parte, los int que recibe el void son los getpid y 
    getppid, que nos dan la id de cada hijo y cada padre
    Getppid nos debe dar el mismo número siempre.*/
}

int main() {
    pid_t pidHijo1, pidHijo2, pidHijo3; //Lo usaremos para el 
                                        //pid de cada hijo
                                        
    int status1, status2, status3; //Estos int nos servirán
                                //para asegurarnos de que el padre
                                //espere a sus hijos
    
    	if ( (pidHijo1=fork()) == 0 ){
            /////// HIJO 1 ///////
    
   	    muestraId(getpid(),getppid());
   	    //Debe mostrar:
   	    //Soy el hijo 1 (idhijo, hijo de idpadre)
   	  
	}else if ( (pidHijo2=fork()) == 0 ){
	    /////// HIJO 2 ///////
	
   	    muestraId(getpid(),getppid());
   	    //Debe mostrar:
   	    //Soy el hijo 2 (idhijo, hijo de idpadre)
   	  
	}else if ( (pidHijo2=fork()) == 0 ){ 
	    /////// HIJO 3 ///////
	
   	    muestraId(getpid(),getppid());
   	    //Debe mostrar:
   	    //Soy el hijo 3 (idhijo, hijo de idpadre)
   	  
	}else{ 
	    /////// CONTROL DE ERRORES ///////
	    if(pidHijo1 == -1 ){
	        printf("\nError creando hijo 1\n");
	        exit(-1);
	    }else if(pidHijo2 == -1){
	        printf("\nError creando hijo 2\n");
	        exit(-1);
	    }else if(pidHijo3 == -1){
	        printf("\nError creando hijo 3\n");
	        exit(-1);
	        
	        /* Una vez hechos, o intentados crear, los fork de cada
	       hijo, comprobaremos si en su id hay un -1, esto es, un 
	       error. Identificamos cuál falla vía print y cerramos
	       el programa vía exit(-1). Si no falla nada, podremos
	       esperar a que los hijos se ejecuten y luego el padre */
	        
	    }else{
	    /////// PADRE ///////
            wait(&status1);
            wait(&status2);
            wait(&status3); //Esto hace que el padre no ejecute
                            //hasta que sus tres hijos acaben
                        
	        printf("\n\nSoy el padre %d\n", getpid());
	    }
	}
    

    return 0;
}
