package twisk.mondeIG;

import twisk.outils.TailleComposants;

/**
 * la classe permet une représentation d'un guichet (fil d'attente) dans le monde twisk
 */
public class GuichetIG extends EtapeIG{


    /**
     * constructeur de la classe guichet
     *
     * @param nom nom donné au guichet
     */
    public GuichetIG(String nom){
        super(nom);
        defineTaille();
        definePosition();
        definePointDeControles();
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
}
