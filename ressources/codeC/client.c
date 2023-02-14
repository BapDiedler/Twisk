#include <stdlib.h>
#include <stdio.h>
#include "def.h"

#define sasEntree 0
#define sasSortie 1
#define guichet 2
#define semaphoreGuichet 1
#define activite 3

void simulation(int ids){
    entrer(sasEntree);//on commence par entrée dans le sasEntrée
    delai(5,2);
    transfert(sasEntree,guichet);//l'étape d'après est une activité
    P(ids,semaphoreGuichet);
    //delai(4,2);
    transfert(guichet,activite);
    delai(4,2);
    V(ids,semaphoreGuichet);
    transfert(activite,sasSortie);
}