#include <stdlib.h>
#include <stdio.h>
#include "def.h"

#define sasEntree 0
#define sasSortie 1
#define activite 2

void simulation(int ids){
    entrer(sasEntree);//on commence par entrée dans le sasEntrée
    delai(6,2);
    transfert(sasEntree,activite);//l'étape d'après est une activité
    delai(8,3);
    transfert(activite,sasSortie);//on finit par la sortie
}