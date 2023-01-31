package twisk.monde;

/**
 * @author Diedler Litchner
 * classe qui va représenter les activités de notre twisk.monde
 */
public class Activite extends Etape {

    /**
     * durée de temps de l'activité
     */
    private int temps;
    /**
     * variance de la durée de temps de l'activité
     */
    private int ecartTemps;

    /**
     * constructeur de la classe Activité
     */
    public Activite(String nom){
        super(nom);
    }

    /**
     * constructeur de la classe Activite
     *
     * @param nom        nom de la classe
     * @param temps      durée dans l'activité
     * @param ecartTemps variance de la durée
     */
    public Activite(String nom, int temps, int ecartTemps) {
        super(nom);
        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }

    /**
     * constructeur vide de la classe
     */
    public Activite() {
        this("activité", 0, 0);
    }

    /**
     * getter qui nous permet de savoir si une etape est une activité
     *
     * @return vrai si l'étape est une activité sinon faux
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }


}
