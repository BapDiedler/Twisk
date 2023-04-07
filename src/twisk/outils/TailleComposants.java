package twisk.outils;

public class TailleComposants {

    /**
     * La largeur de l'étape graphique
     */
    private static int largeur;

    /**
     * La hauteur de l'étape graphique
     */
    private static int hauteur;

    /**
     * Instance unique de la classe TailleComposants
     */
    private static final TailleComposants instance = new TailleComposants();

    /**
     * Récupère l'instance
     * @return l'instance de la classe
     */
    public TailleComposants getInstance(){return instance;}

    /**
     * Constructeur privé. Défini les constantes
     */
    private TailleComposants(){
        largeur = 250;
        hauteur = 200;
    }

    /**
     * Donne la largeur
     * @return la largeur
     */
    public static int getLargeur(){return largeur;}

    /**
     * Donne la hauteur
     * @return la hauteur
     */
    public static int getHauteur(){return hauteur;}
}
