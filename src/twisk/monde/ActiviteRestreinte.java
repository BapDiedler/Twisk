package twisk.monde;

/**

 * Classe représentant une activité restreinte dans le monde de Twisk.
 * Cette classe étend la classe Activité et ajoute des restrictions sur l'accès à l'activité.
 *
 * @author Diedler et Litchner
 */
public class ActiviteRestreinte extends Activite {

    /**
     * Constructeur de la classe ActivitéRestreinte avec toutes les informations spécifiées.
     *
     * @param nom nom de l'activité
     * @param temps durée de l'activité en minutes
     * @param ecartTemps variance de la durée de l'activité en minutes
     */
    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    /**
     * Constructeur de la classe ActivitéRestreinte avec un nom spécifié.
     * Initialise une activité restreinte avec le nom spécifié et une durée et une variance de 0 minutes.
     *
     * @param nom nom de l'activité
     */
    public ActiviteRestreinte(String nom) {
        this(nom, 3, 2);
    }

    /**
     * méthode qui permet d'afficher le delay et le transfère avec un successeur précis
     *
     * @param successeur successeur de l'étape
     * @return le toC
     */
    protected String complementToC(Etape successeur){
        return transfert(successeur) + successeur.toC();
    }
}