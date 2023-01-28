package monde;

public class Guichet extends Etape{
    /**
     * nombre de jetons que possède le guichet
     */
    private int nbJetons;

    /**
     * constructeur de la classe Guichet
     *
     * @param nom le nom du Guichet
     * @param nbJetons le nombre de jetons qu'il possède
     */
    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
    }

    /**
     * constructeur de la classe Guichet
     *
     * @param nom le nom du guichet
     */
    public Guichet(String nom) {
        this(nom,0);
    }

    /**
     * méthode qui permet de savoir si l'étape est un guichet
     *
     * @return true car l'étape est un guichet
     */
    @Override
    public boolean estUnGuichet() {
        return true;
    }
}
