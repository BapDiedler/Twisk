package monde;

/**
 * @author Diedler Litchner
 * classe qui va représenter les activités de notre monde
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

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public void setEcartTemps(int ecartTemps) {
        this.ecartTemps = ecartTemps;
    }
}
