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
        KitC kit = new KitC();
        kit.creerEnvironment();
        kit.compilation();
        kit.construireLaLibrairie();
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

    /**
     * méthode permettant de lancer la simulation
     * @param nbEtapes nombre d'étapes
     * @param nbGuichets nombre de guichets
     * @param nbClients nombre de clients
     * @param tabJetonsGuichets nombre de jetons dans les guichets
     * @return tableau retenant les clients
     */
    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichets);

    /**
     * méthode qui donne la place des clients dans le monde
     * @param nbEtapes nombre d'étapes
     * @param nbClients nombre de clients
     * @return un tableau avec la position des clients
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * méthode qui permet de nettoyer le monde
     */
    public native void nettoyage() ;
}
