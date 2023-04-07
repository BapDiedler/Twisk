package twisk.mondeIG;

import twisk.outils.TailleComposants;

/**
 * Cette classe décrit une activité graphique
 */
public class ActiviteIG extends EtapeIG {

    /**
     * Délai de l'activité
     */
    private int delai;

    /**
     * Ecart temps de l'activité
     */
    private int ecart;

    /**
     * Constructeur de l'étape graphique
     */
    public ActiviteIG() {
        super("Activité");
        defineTaille();
        definePosition();
        definePointDeControles();
        this.delai = 3;
        this.ecart = 1;
    }

    /**
     * méthode permettant de changer la taille de l'étape
     */
    protected void defineTaille(){
        this.hauteur = TailleComposants.getHauteurActivite();
        this.largeur = TailleComposants.getLargeurActivite();
    }

   @Override
    public void setDelai(int delai){
        this.delai = delai;
    }

    @Override
    public void setEcart(int ecart){
        this.ecart = ecart;
    }

    @Override
    public int getDelai(){
        return delai;
    }

    @Override
    public int getEcart(){
        return ecart;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }
}
