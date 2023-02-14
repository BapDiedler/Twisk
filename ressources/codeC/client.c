#include <stdlib.h>
#include <stdio.h>
#include "def.h"

//etapes du monde
#define sasEntree 0
#define sasSortie 1
#define guichet1 2
#define activite1 3
#define guichet2 4
#define activite2 5
//semaphores des guichets
#define semaphoreGuichet1 1
#define semaphoreGuichet2 2

void simulation(int ids){
    entrer(sasEntree);//on commence par entrée dans le sasEntrée
    delai(5,2);
    transfert(sasEntree,guichet1);//l'étape d'après est une activité
    P(ids,semaphoreGuichet1);
    transfert(guichet1,activite1);
    delai(4,2);
    V(ids,semaphoreGuichet1);
    transfert(activite1,guichet2);
    P(ids, semaphoreGuichet2);
    transfert(guichet2,activite2);
    delai(5,3);
    V(ids,semaphoreGuichet2);
    transfert(activite2,sasSortie);
}