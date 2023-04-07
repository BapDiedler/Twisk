package twisk.outils;

/**
 * Cette classe représente une fabrique d'identifiant
 * Cette classe reprend un modèle Singleton
 */
public class FabriqueIdentifiant {

    /**
     * Numéro d'identifiant mis à jour à chaque fois qu'un numéro est attribué
     */
    private static int numIdentifiant;

    /**
     * Numéro d'identifiant des points de contrôle
     */
    private static int identifiantPointDeControle;

    /**
     * instance unique de la classe FabriqueNumero
     */
    private static final FabriqueIdentifiant instance = new FabriqueIdentifiant();

    /**
     * Constructeur privé pour empêcher la création d'une autre instance de la classe en dehors de celle déjà déclarée
     */
    private FabriqueIdentifiant(){
        numIdentifiant = 0;
        identifiantPointDeControle = 0;
    }

    /**
     * Récupère l'instance de la fabrique
     * @return l'instance unique
     */
    public static FabriqueIdentifiant getInstance(){
        return instance;
    }

    /**
     * Donne le numéro d'identifiant actuel
     * @return le numéro d'identifiant
     */
    public static String getNumeroEtape(){return Integer.toString(numIdentifiant++);}

    /**
     * Donne le numéro d'identifiant du point de contrôle actuel
     * @return le numéro d'identifiant du point de contrôle
     */
    public static String getNumeroPointControle(){return Integer.toString(identifiantPointDeControle++);}

    /**
     * Réinitialise le numéro d'étape à 0
     */
    public void resetId(){numIdentifiant = 0;}

    /**
     * Réinitialise le numéro du point de contrôle à 0
     */
    public void resetPointDeControle(){identifiantPointDeControle = 0;}

}
