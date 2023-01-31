package twisk.outils;

/**
 * @author Diedler et Baptiste
 * Fabrique des différents paquets de cartes du uno
 */
public class FabriqueNumero {

    /**
     * Compteur d'étapes
     */
    private int cptEtapes;

    /**
     * Instance de FabriqueNumero
     */
    private static FabriqueNumero instance = new FabriqueNumero();

    /**
     *
     */
    private FabriqueNumero(){
        this.cptEtapes = 0;
    }

    /**
     * Récupère l'instance du singleton
     * @return l'instance
     */
    public static FabriqueNumero getInstance(){
        return instance;
    }

    /**
     * Donne le numéro de l'étape
     * @return le numéro de l'étape
     */
    public int getNumeroEtape(){
        return cptEtapes;
    }

    public void reset(){
        this.cptEtapes = 0;
    }
    
}
