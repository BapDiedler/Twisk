#include "def.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    int* pid;
    pid = start_simulation(3, 0, 10, NULL);
    int* tab = ou_sont_les_clients(3, 10) ;
    printf("les clients :  ");
    for(int i=1; i<11; i++){
        printf("%d,",tab[i]);
    }
    printf("\n");
    nettoyage();
    return 0 ;
}