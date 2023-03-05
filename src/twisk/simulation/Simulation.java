package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.outils.KitC;

/**
 * @author Diedler et Litchner
 *
 * cette classe permet de simuler le monde cré par le client dans un premier temps l'affichage se
 *                      fait sur la sortie standard.
 */
public class Simulation {

    private int nbClients;

    /**
     * constructeur de la simulation
     */
    public Simulation(){
        nbClients = 0;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    /**
     * méthode qui permet de faire une simulation du monde
     * @param monde mode qui est simulé
     */
    public void simuler(Monde monde){

        String codeC = monde.toC();

        KitC kit = new KitC();
        kit.creerEnvironment();
        kit.creerFichier(codeC);
        kit.compilation();
        kit.construireLaLibrairie();


        System.out.println("Le monde:");
        System.out.println(monde);
        //System.out.println("Le code C généré:");
        //System.out.println(monde.toC());
        System.load("/tmp/twisk/libTwisk.so") ;

        int[] pid = null;
        int nbEtapes = monde.nbEtapes();
        int nbGuichets = monde.nbGuichet();
        int[] tab = new int[(nbClients+1)*nbEtapes];
        int[] tabJetonsGuichet = new int[nbGuichets];

        //initialisation des jetons de guichet
        int j=0;
        for(Etape etape : monde){
            if(etape.estUnGuichet()){
                tabJetonsGuichet[j]=etape.getNbJetons();
                j++;
            }
        }

        //affichage des clients du monde (les PID)
        pid = start_simulation(nbEtapes, nbGuichets, nbClients, tabJetonsGuichet);
        System.out.println("les clients :  ");
        for(int i=0; i<nbClients; i++){
            System.out.print(pid[i]+" | ");
        }
        System.out.println("\n");

        //affichage du monde au cours du temps
        while(tab[nbClients+1] != nbClients){//on regarde tab[nbClients+1] car la sortie se trouve à la place nb+1
            tab = ou_sont_les_clients(nbEtapes, nbClients) ;
            afficherEntree(tab);
            for(int i = 1; i<nbEtapes-1; i++)//affichage de toutes les étapes
                afficherActivity(tab,i,nbClients, monde);
            afficherSortie(tab,nbClients);
            System.out.print("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\n");
        nettoyage();

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
        System.out.print("SasSortie : "+tab[nbClients+1]+" client(s) :  ");
        for(int i=0; i<tab[nbClients+1]; i++){
            System.out.print(tab[nbClients+2+i]+" | ");
        }
        System.out.print("\n");
    }

    /**
     * fonction qui affiche une entrée
     * @param tab tableau d'étapes
     */
    private void afficherEntree(int[] tab){
        System.out.print("SasEntree : "+tab[0]+" client(s) :  ");
        for(int i=0; i<tab[0]; i++){
            System.out.print(tab[1+i]+" | ");
        }
        System.out.print("\n");
    }

    /**
     * fonction qui affiche une activité
     * @param tab tableau d'étapes
     * @param numero numéro de l'activité
     * @param nbClients nombre de clients
     */
    void afficherActivity(int[] tab, int numero, int nbClients, Monde monde){
        int positionEtape = (nbClients+1)*(1+numero);
        System.out.print("Activité "+numero+" ("+monde.getEtape(numero+1).getNom()+"): "+tab[positionEtape]+" client(s) :  ");
        for(int i=0; i<tab[positionEtape]; i++){
            System.out.print(tab[positionEtape+i+1]+" | ");
        }
        System.out.print("\n");
    }
}
