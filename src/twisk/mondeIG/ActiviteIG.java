package twisk.mondeIG;

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
        this.delai = 3;
        this.ecart = 1;
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
}
