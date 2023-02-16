package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

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
    public Simulation(){
        new KitC().creerEnvironment();
    }

    /**
     * méthode qui permet de faire une simulation du monde
     * @param monde mode qui est simulé
     */
    public void simuler(Monde monde){
        System.out.println("Le monde:");
        System.out.println(monde);
        System.out.println("Le code C généré:");
        System.out.println(monde.toC());
    }
}
