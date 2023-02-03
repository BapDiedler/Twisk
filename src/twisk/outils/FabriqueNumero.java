package twisk.outils;

/**
 * Classe représentant une fabrique de numéros.
 * Cette classe implémente un pattern Singleton pour garantir qu'il n'y ait qu'une seule instance de cette classe à tout moment.
 *
 * @author Diedler
 * @author Litchner
 */
public class FabriqueNumero {

    /**
     * Compteur d'étapes qui est partagé entre toutes les instances de cette classe
     */
    private static int cptEtapes;

    /**
     * Instance unique de la classe FabriqueNumero
     */
    private static final FabriqueNumero instance = new FabriqueNumero();

    /**
     * Constructeur privé pour empêcher la création d'une autre instance de la classe en dehors de celle déjà déclarée.
     */
    private FabriqueNumero() {
        cptEtapes = 0;
    }

    /**
     * Récupère l'instance unique de la classe FabriqueNumero
     *
     * @return l'instance unique de la classe FabriqueNumero
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Retourne le numéro de l'étape actuelle
     *
     * @return le numéro de l'étape actuelle
     */
    public int getNumeroEtape() {
        return cptEtapes++;
    }

    /**
     * Réinitialise le compteur d'étapes à 0
     */
    public void reset() {
        cptEtapes = 0;
    }
}