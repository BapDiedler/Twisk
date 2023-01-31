package twisk.monde;

public class ActiviteRestreinte extends Activite{
    /**
     * Constructeur de la classe ActivitéRestreinte
     * @param nom nom de l'activité
     * @param t temps
     * @param e écart de temps
     */
    public ActiviteRestreinte(String nom, int t, int e){
        super(nom,t,e);
    }

    /**
     * constructeur de la classe ActivitéRestreinte
     * @param nom nom de l'activité
     */
    public ActiviteRestreinte(String nom){
        this(nom,0,0);
    }
}
