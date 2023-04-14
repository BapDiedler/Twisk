package twisk.mondeIG;

import twisk.outils.TailleComposants;

/**
 * la classe permet une représentation d'un guichet (fil d'attente) dans le monde twisk
 */
public class GuichetIG extends EtapeIG{

    /**
     * Nombre de jetons du guichet
     */
    int nbJetons;


    /**
     * constructeur de la classe guichet qui prend qu'un paramètre
     * @param nom nom donné au guichet
     */
    public GuichetIG(String nom){
        this(nom, 3);
    }

    /**
     * Constructeur complet de la classe guichet
     * @param nom nom donné au guichet
     * @param nbJetons le nombre de jetons
     */
    public GuichetIG(String nom, int nbJetons){
        super(nom);
        defineTaille();
        definePosition();
        definePointDeControles();
        this.nbJetons = nbJetons;
    }

    /**
     * constructeur vide de la classe
     */
    public GuichetIG(){
        this("guichet");
    }

    @Override
    protected void defineTaille() {
        hauteur = TailleComposants.getHauteurGuichet();
        largeur = TailleComposants.getLargeurGuichet();
    }

    /**
     * Donne le nombre de jetons du guichet
     * @return Le nombre de jetons du guichet
     */
    public int getNbJetons(){
        return nbJetons;
    }

    /**
     * méthode qui permet de connaitre le nombre de jetons
     * @param nbJetons Le nouveau nombre de jetons
     */
    public void setNbJetons(int nbJetons){
        this.nbJetons = nbJetons;
    }

    @Override
    public boolean possedeUneSortie() {
        boolean valide =  super.possedeUneSortie();
        if(successeurs.size()==1){
            successeurs.get(0).setEstRestreint(true);
            return valide;
        }else{
            return false;
        }
    }
}
