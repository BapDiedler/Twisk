package twisk.outils;

public class TailleComposants {

    /**
     * La largeur de l'activité graphique
     */
    private static int largeurActivite;

    /**
     * La hauteur de l'activité graphique
     */
    private static int hauteurActivite;

    /**
     * La largeur d'un guichet graphique
     */
    private static int largeurGuichet;

    /**
     * La hauteur d'un guichet graphique
     */
    private static int hauteurGuichet;

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
        largeurActivite = 250;
        hauteurActivite = 200;
        largeurGuichet = 250;
        hauteurGuichet = 100;
    }

    /**
     * Donne la largeur de l'activité
     * @return la largeur
     */
    public static int getLargeurActivite(){return largeurActivite;}

    /**
     * Donne la hauteur l'activité
     * @return la hauteur
     */
    public static int getHauteurActivite(){return hauteurActivite;}

    /**
     * Donne la largeur du guichet
     * @return la largeur
     */
    public static int getLargeurGuichet(){return largeurActivite;}

    /**
     * Donne la hauteur du guichet
     * @return la hauteur
     */
    public static int getHauteurGuichet(){return hauteurActivite;}
}
