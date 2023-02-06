package twisk.monde;

/**
 * Classe représentant une activité dans le monde de Twisk.
 * Cette classe étend la classe `Etape` et ajoute des informations supplémentaires sur la durée et la variance de la durée d'une activité.
 *
 * @author Diedler Litchner
 */
public class Activite extends Etape {

    /**
     * Durée de temps de l'activité en minutes
     */
    private int temps;

    /**
     * Variance de la durée de temps de l'activité en minutes
     */
    private int ecartTemps;

    /**
     * Constructeur par défaut de la classe Activité.
     * Initialise une activité vide avec un nom "activité", une durée de 0 minutes et une variance de 0 minutes.
     */
    public Activite() {
        this("Activité", 0, 0);
    }

    /**
     * Constructeur de la classe Activité avec un nom spécifié.
     * Initialise une activité avec le nom spécifié et une durée de 0 minutes et une variance de 0 minutes.
     *
     * @param nom le nom de l'activité
     */
    public Activite(String nom) {
        this(nom, 0, 0);
    }

    /**
     * Constructeur de la classe Activité avec toutes les informations spécifiées.
     *
     * @param nom        le nom de l'activité
     * @param temps      la durée de l'activité en minutes
     * @param ecartTemps la variance de la durée de l'activité en minutes
     */
    public Activite(String nom, int temps, int ecartTemps) {
        super(nom);
        this.temps = temps;
        this.ecartTemps = ecartTemps;
    }

    /**
     * Retourne si l'étape est une activité.
     * Cette méthode redéfinit la méthode de la classe mère Etape.
     *
     * @return toujours vrai, car cette classe représente une activité
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }
}