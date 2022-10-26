#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main()
{
int num  =0;
printf("Introduce un numero");
scanf("%d", &num);
pid_t pid , hijo;
pid = fork();
if (pid == -1) {
    printf("no se ha podido crear el hijo");
    exit(-1);
    
}
if (pid == 0)
 {
     num= num+4;
     printf("El numero en el proceso hijo es: %d\n", num);
 }
 else 
 {
     hijo == wait(NULL);
     num = num-5;
     printf( "El numero en el proceso padre es: %d\n",num);
 }
 exit(0);

}
