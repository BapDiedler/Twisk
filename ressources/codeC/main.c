#include "def.h"
#include <stdio.h>
#include <stdlib.h>

//fonction qui affiche une sortie
void afficherSortie(int* tab, int nbClients){
    printf("SasSortie : %d client(s)    ",tab[nbClients+1]);
    for(int i=0; i<tab[nbClients+1]; i++){
        printf("%d | ",tab[nbClients+2+i]);
    }
    printf("\n");
}

//fonction qui affiche un entrée
void afficherEntree(int* tab){
    printf("SasEntree : %d client(s)    ",tab[0]);
    for(int i=0; i<tab[0]; i++){
        printf("%d | ",tab[1+i]);
    }
    printf("\n");
}

//fonction qui affiche une activité
void afficherActivity(int* tab, int numero, int nbClients){
    int positionEtape = (nbClients+1)*(1+numero);
    printf("Activité %d: %d client(s)    ",numero,tab[positionEtape]);
    for(int i=0; i<tab[positionEtape]; i++){
        printf("%d | ",tab[positionEtape+1+i]);
    }
    printf("\n");
}

/*--PROGRAMME PRINCIPAL--*/
int main(int argc, char** argv) {
    int* pid = NULL;
    int nbClients = 10;
    int nbEtapes = 3;
    int nbGuichets = 0;
    int* tab = malloc(sizeof(int) * (nbClients+1)*(nbEtapes+nbGuichets) );
    int cpt = 1; //compteur pour le nombre d'activité

    //affichage des clients du monde (les PID)
    pid = start_simulation(nbEtapes, nbGuichets, nbClients, NULL);
    printf("les clients :  ");
    for(int i=0; i<nbClients; i++){
        printf("%d,",pid[i]);
    }
    printf("\n");

    //affichage du monde au cours du temps
    while(tab[nbClients+1] != nbClients){//on regarde tab[nbClients+1] car la sortie se trouve à la place nb+1
        tab = ou_sont_les_clients(nbEtapes, nbClients) ;
        afficherEntree(tab);
        afficherActivity(tab,cpt,nbClients);
        afficherSortie(tab,nbClients);
        printf("\n");
        sleep(1);
        //cpt++;
    }
    printf("\n");
    nettoyage();
    return 0 ;
}