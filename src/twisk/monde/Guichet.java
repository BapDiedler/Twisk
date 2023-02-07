package twisk.monde;

import twisk.outils.FabriqueNumero;

/**
 * cette classe se trouve dans le package twisk.monde.
 * permet de représenter un guichet (file d'attente).
 *
 * @author Diedler et Litchner
 */

public class Guichet extends Etape{
    /**
     * nombre de jetons que possède le guichet
     */
    private int nbJetons;

    private final int cptSemaphore;

    /**
     * constructeur de la classe Guichet
     *
     * @param nom le nom du Guichet
     * @param nbJetons le nombre de jetons qu'il possède
     */
    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
        this.cptSemaphore = FabriqueNumero.getCptSemaphore();
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

    /**
     * setter sur le nombre de jetons que possède le guichet
     * @param nbJetons qui est le nombre de jetons actuel
     */
    public void setNbJetons(int nbJetons){
        this.nbJetons = nbJetons;
    }

    /**
     * méthode qui permet de connaître le numéro du guichet
     * @return int qui est le numéro du guichet
     */
    public int getNumeroSemaphore() {
        return cptSemaphore;
    }
}
