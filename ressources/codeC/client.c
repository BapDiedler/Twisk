#include <stdlib.h>
#include <stdio.h>
#include "def.h"

//etapes
#define sasEntree 0
#define sasSortie 1
#define guichet 2
#define activite 3
#define guichet2 4
#define activite2 5
//semaphore
#define semaphoreGuichet 1
#define semaphoreGuichet2 2

void simulation(int ids){
    entrer(sasEntree);//on commence par entrée dans le sasEntrée
    delai(5,2);
    transfert(sasEntree,guichet);//l'étape d'après est une activité
    P(ids,semaphoreGuichet);
        transfert(guichet,activite);
        delai(4,2);
    V(ids,semaphoreGuichet);
    transfert(activite,guichet2);
    P(ids,semaphoreGuichet);
        transfert(guichet,activite);
        delai(4,2);
        V(ids,semaphoreGuichet);
    transfert(activite,sasSortie);
}