package twisk.mondeIG;

/**
 * Cette classe décrit un arc défini par deux points
 */
public class ArcIG {
    /**
     * Le premier point de contrôle
     */
    private PointDeControleIG premPoint;

    /**
     * Le deuxième point de contrôle
     */
    private PointDeControleIG deuxPoint;

    /**
     * Le constructeur d'un ArcIG
     */
    public ArcIG(PointDeControleIG premPoint, PointDeControleIG deuxPoint){
        this.premPoint = premPoint;
        this.deuxPoint = deuxPoint;
    }

    /**
     * Donne le premier point de contrôle de l'arc
     * @return Le premier point de contrôle
     */
    public PointDeControleIG getPremPoint(){return premPoint;}

    /**
     * Donne le deuxième point de contrôle de l'arc
     * @return Le deuxième point de contrôle
     */
    public PointDeControleIG getDeuxPoint(){return deuxPoint;}
}
