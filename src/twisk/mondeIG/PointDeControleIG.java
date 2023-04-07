package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;

/**
 * Cette classe décrit un point de contrôle rattaché à une étape
 */
public class PointDeControleIG {
    /**
     * La position du centre du point de controle sur l'axe des abscisses
     */
    private int posXCentre;
    /**
     * La position du centre du point de contrôle sur l'axe des ordonnés
     */
    private int posYCentre;
    /**
     * L'identifiant du point de controle
     */
    private String identifiant;
    /**
     * L'étape à laquelle est rataché le point de contrôle
     */
    private EtapeIG etape;

    /**
     * Constructeur du point de contrôle graphique
     * @param posXCentre la position sur l'axe des abscisses
     * @param posYCentre la position sur l'axe des ordonnées
     * @param etape l'étape graphique
     */
    public PointDeControleIG(int posXCentre, int posYCentre, EtapeIG etape){
        this.posXCentre = posXCentre;
        this.posYCentre = posYCentre;
        this.etape = etape;
        this.identifiant = FabriqueIdentifiant.getNumeroPointControle();
    }

    /**
     * Donne la position sur l'axe des abscisses du point de contrôle
     * @return pos X
     */
    public int getPosXCentre() {
        return posXCentre;
    }

    /**
     * Donne la position sur l'axe des ordonnées du point de contrôle
     * @return pos Y
     */
    public int getPosYCentre(){
        return posYCentre;
    }

    /**
     * Donne l'étape à laquelle le point de contrôle est lié
     * @return l'étape correspondant au point de contrôle
     */
    public EtapeIG getEtape(){
        return etape;
    }

    /**
     * Donne l'identifiant du point de contrôle
     * @return l'identifiant
     */
    public String getIdentifiant(){
        return identifiant;
    }

    /**
     * Change la position du point de contrôle
     * @param posXCentre La nouvelle position sur l'axe des abscisses
     * @param posYCentre La nouvelle position sur l'axe des ordonnées
     */
    public void setPosition(int posXCentre, int posYCentre){
        this.posXCentre = posXCentre;
        this.posYCentre = posYCentre;
    }
}
