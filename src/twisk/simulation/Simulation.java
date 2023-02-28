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
        System.load("/tmp/twisk/libTwisk.so") ;
    }

    private void appel_main(Monde monde){
        int nbCLients = 10;
        int nbEtapes = monde.nbEtapes();
        int nbGuichets = monde.nbGuichet();
        int[] pid = new int[nbCLients];
        int[] tab = new int[nbEtapes];
        int[] tabJetonsGuichets = new int[nbGuichets];

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

    /**
     * fonction qui affiche une sortie
     * @param tab tableau de clients
     * @param nbClients nombre de clients
     */
    private void afficherSortie(int[] tab, int nbClients){
        System.out.println("SasSortie : "+tab[nbClients+1]+" client(s)");
        for(int i=0; i<tab[nbClients+1]; i++){
            System.out.println(tab[nbClients+2+i]+" | ");
        }
        System.out.println("\n");
    }

    /**
     * fonction qui affiche une entrée
     * @param tab tableau d'étapes
     */
    private void afficherEntree(int[] tab){
        System.out.println("SasEntree : "+tab[0]+" client(s)    ");
        for(int i=0; i<tab[0]; i++){
            System.out.println(tab[1+i]+" | ");
        }
        System.out.println("\n");
    }

    /**
     * fonction qui affiche une activité
     * @param tab tableau d'étapes
     * @param numero numéro de l'activité
     * @param nbClients nombre de clients
     */
    void afficherActivity(int[] tab, int numero, int nbClients){
        int positionEtape = (nbClients+1)*(1+numero);
        System.out.println("Activité "+numero+": "+tab[positionEtape]+" client(s)    ");
        for(int i=0; i<tab[positionEtape]; i++){
            System.out.println(tab[positionEtape]+" | ");
        }
        System.out.println("\n");
    }
}
