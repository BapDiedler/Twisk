package twisk.outils;

/**
 * @author Diedler et Baptiste
 * Fabrique des différents paquets de cartes du uno
 */
public class FabriqueNumero {

    /**
     * Compteur d'étapes
     */
    private static int cptEtapes = 0;

    /**
     * Instance de FabriqueNumero
     */
    private static final FabriqueNumero instance = new FabriqueNumero();

    /**
     * constructeur de la fabrique de nombres
     */
    private FabriqueNumero(){
    }

    /**
     * Récupère l'instance du singleton
     * @return l'instance
     */
    public static FabriqueNumero getInstance(){
        cptEtapes++;
        return instance;
    }

    /**
     * Donne le numéro de l'étape
     * @return le numéro de l'étape
     */
    public int getNumeroEtape(){
        return cptEtapes;
    }

    /**
     * méthode qui remet à 0 le compteur de nombre
     */
    public void reset(){
        cptEtapes = 0;
    }
    
}
