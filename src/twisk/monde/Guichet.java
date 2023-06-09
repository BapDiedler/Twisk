package twisk.monde;

import twisk.outils.FabriqueNumero;

/**
 * Cette classe se trouve dans le package twisk. Monde.
 * Permet de représenter un guichet (file d'attente).
 *
 * @author Diedler et Litchner
 */

public class Guichet extends Etape{
    /**
     * nombre de jetons que possède le guichet
     */
    private int nbJetons;

    /**
     * numéro du semaphore du guichet
     */
    private final int numSemaphore;

    /**
     * constructeur de la classe Guichet
     *
     * @param nom le nom du Guichet
     * @param nbJetons le nombre de jetons qu'il possède
     */
    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
        this.numSemaphore = FabriqueNumero.getNumSemaphore();
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
        return numSemaphore;
    }

    /**
     * getter du nombre de jetons dans le guichet
     * @return le nombre de jetons
     */
    public int getNbJetons(){
        return nbJetons;
    }

    @Override
    public String toC() {
        Etape successeur = getSuccesseur();
        //nom du sémaphore dans le code C
        String semaphore = "SEM_" + passNom() + getNumeroSemaphore();
        //code C pour le passage d'un guichet à une activité
        return "\tP(ids," + semaphore + ");\n"
            + transfert() + successeur.delai() + "\n"
            + "\tV(ids," + semaphore + ");\n"
            + successeur.toC();
    }

    @Override
    public String constantes() {
        Etape successeur = getSuccesseur();
        //nom du sémaphore dans le code C
        String semaphore = "SEM_" + passNom() + getNumeroSemaphore() + " ";
        return "#define " + passNom() + getNumero() + " " + getNumero() +
                " \n#define " + semaphore + getNumeroSemaphore()
                + "\n" + successeur.constantes();
    }
}
