package twisk.simulation;

import twisk.monde.Etape;

/**
 * classe représentant les clients du monde
 */
public class Client {

    /**
     * numéro du client
     */
    int numeroClient;

    /**
     * etape dans lequel se trouve le client
     */
    Etape etape;

    /**
     * rang du client dans une file d'attente
     */
    int rang;


    /**
     * constructeur de la classe
     */
    public Client(int numero){
        this.numeroClient = numero;
    }


    /**
     * méthode qui permet de déplacer un client dans une nouvelle étape
     *
     * @param etape nouvelle étape du client
     * @param rang position dans la file d'attente du client
     */
    public void allerA(Etape etape, int rang){
        this.etape = etape;
        this.rang = rang;
    }
}
