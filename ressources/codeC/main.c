#include "def.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    int* pid;
    pid = start_simulation(3, 0, 10, NULL);
    printf("les clients :  ");
    for(int i=0; i<10; i++){
        printf("%d,",pid[i]);
    }
    printf("\n");
    int* tab = ou_sont_les_clients(3, 10) ;
    for(int i=0; i<33; i+=11){
        printf("étape : %d client(s)     ",tab[i]);
        if(tab[i]>0){
            for(int j=0; j<tab[i]; j++){
                printf("%d,",tab[i+j+1]);
            }
        }
        printf("\n");
    }
    printf("\n");
    nettoyage();
    return 0 ;
}