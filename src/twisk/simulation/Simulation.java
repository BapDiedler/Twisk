package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;

import java.util.Iterator;

/**
 * @author Diedler et Litchner
 *
 * cette classe permet de simuler le monde cré par le client dans un premier temps l'affichage se
 *                      fait sur la sortie standard.
 */
public class Simulation implements Iterable<Client>{

    /**
     * Le nombre de clients
     */
    private int nbClients;

    /**
     * gestionnaires pour les clients du monde
     */
    private GestionnaireClients gestionnaireClients;

    /**
     * kitC pour pouvoir manipuler le monde
     */
    private KitC kitC;

    /**
     * constructeur de la simulation
     */
    public Simulation(){
        nbClients = 0;
    }

    /**
     * Change le nombre de clients
     * @param nbClients Le nouveau nombre de clients
     */
    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    /**
     * méthode qui permet de faire une simulation du monde
     * @param monde monde qui est simulé
     */
    public void simuler(Monde monde){
        //initialisation
        initialisationSimuler(monde);
        int nbEtapes = monde.nbEtapes();
        int[] tab = new int[(nbClients+1)*nbEtapes];
        int[] tabJetonsGuichet = initialisationTabGuichets(monde);
        //affichage des clients
        affichageDesClients(tabJetonsGuichet,monde);
        //affichage du monde au cours du temps
        affichageEtapesDuMonde(monde,tab);
        System.out.println("\n");
        nettoyage();
    }

    /**
     * méthode permettant de lancer la simulation
     * @param nbEtapes nombre d'étapes
     * @param nbGuichets nombre de guichets
     * @param nbClients nombre de clients
     * @param tabJetonsGuichets nombre de jetons dans les guichets
     * @return tableau contenant les clients
     */
    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichets);

    /**
     * méthode qui donne l'emplacement des clients dans le monde
     * @param nbEtapes nombre d'étapes
     * @param nbClients nombre de clients
     * @return un tableau qui contient l'emplacement des clients
     */
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    /**
     * méthode qui permet de nettoyer le monde
     */
    public native void nettoyage() ;


    /**
     * méthode qui permet d'afficher les clients du monde
     * @param tabJetonsGuichet tableau des jetons du monde
     * @param monde monde dans lequel se trouve les clients
     */
    private void affichageDesClients(int[] tabJetonsGuichet, Monde monde){
        //affichage des clients du monde (les PID)
        int[] pid = start_simulation(monde.nbEtapes(), monde.nbGuichet(), nbClients, tabJetonsGuichet);
        this.gestionnaireClients = new GestionnaireClients(pid);
        System.out.println("les clients :  ");
        for(int i=0; i<nbClients; i++){
            System.out.print(pid[i]+" | ");
        }
        System.out.println("\n");
    }

    /**
     * affichage de toutes les étapes du monde
     * @param monde monde où se trouve les étapes
     * @param tab tableau avec les étapes et les clients
     */
    private void affichageEtapesDuMonde(Monde monde, int[] tab){
        while(tab[nbClients+1] != nbClients) {//on regarde tab[nbClients+1] car la sortie se trouve à la place nb+1
            tab = ou_sont_les_clients(monde.nbEtapes(), nbClients);
            miseAjourClient(monde,tab);
            afficherEntree(tab);
            for (int i = 1; i < monde.nbEtapes() - 1; i++)//affichage de toutes les étapes
                afficherEtape(tab, monde.getEtape(i + 1));
            afficherSortie(tab, nbClients);
            System.out.print("\n");
            sleep();
        }
    }


    /**
     * méthode qui permet de mettre à jour les clients du monde
     *
     * @param tab tableau dans lequel se trouve les clients du monde
     */
    private void miseAjourClient(Monde monde, int[] tab){
        int nbClientsEtape ,numClient;
        int numeroEtape = -1;
        Etape etape;
        for(int i=0; i<tab.length; i+=(nbClients+1)){
            nbClientsEtape = tab[i];
            numeroEtape++;
            for(int j=1+i; j<=nbClientsEtape+i; j++){
                numClient = tab[j];
                etape = monde.getEtape(numeroEtape);
                gestionnaireClients.allerA(numClient,etape,j-i-1);
            }
        }
    }

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
     * fonction qui affiche une etape
     * @param tab tableau d'étapes
     */
    void afficherEtape(int[] tab, Etape etape){
        int positionEtape = (nbClients+1)*(etape.getNumero());
        System.out.print("Etape "+(etape.getNumero()-1)+" ("+etape.getNom()+"): "+tab[positionEtape]+" client(s) :  ");
        for(int i=0; i<tab[positionEtape]; i++){
            System.out.print(tab[positionEtape+i+1]+" | ");
        }
        System.out.print("\n");
    }

    /**
     * méthode qui permet la mise en place de la methode simuler
     * @param monde monde que l'on va simuler
     */
    private void initialisationSimuler(Monde monde){
        String codeC = monde.toC();

        kitC = new KitC();
        kitC.creerEnvironment();
        kitC.creerFichier(codeC);
        kitC.compilation();
        kitC.construireLaLibrairie();

        System.out.println("Le monde:");
        System.out.println(monde);
        System.out.println("Le code C généré:");
        System.out.println(monde.toC());
        int numLibrairie = FabriqueNumero.getCurrentCptLibrairie();
        System.out.println("La librairie utilisée: " + "/tmp/twisk/libTwisk" + numLibrairie +".so");
        System.load("/tmp/twisk/libTwisk" + numLibrairie +".so") ;
    }

    /**
     * méthode qui initialise le tableau des jetons de guichet
     * @param monde monde dans lequel se trouve les guichets
     * @return le tableau de jetons
     */
    private int[] initialisationTabGuichets(Monde monde){
        int[] tabJetonsGuichet = new int[monde.nbGuichet()];
        //initialisation des jetons de guichet
        int j=0;
        for(Etape etape : monde){
            if(etape.estUnGuichet()){
                tabJetonsGuichet[j]=etape.getNbJetons();
                j++;
            }
        }
        return tabJetonsGuichet;
    }

    /**
     * méthode qui met en pause le programme
     */
    private void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<Client> iterator() {
        return gestionnaireClients.iterator();
    }
}
