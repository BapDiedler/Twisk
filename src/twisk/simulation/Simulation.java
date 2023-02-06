package twisk.simulation;

import twisk.monde.Monde;

/**
 * @author Diedler et Litchner
 *
 * cette classe permet de simuler le monde cré par le client dans un premier temps l'affichage se
 *                      fait sur la sortie standard.
 */
public class Simulation {

    /**
     * constructeur de la simulation
     */
    public Simulation(){}

    /**
     * méthode qui permet de faire une simulation du monde
     * @param monde mode qui est simulé
     */
    public void simuler(Monde monde){
        System.out.println(monde);
    }
}
