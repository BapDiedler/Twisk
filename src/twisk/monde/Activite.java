package twisk.monde;

import java.util.Iterator;
import java.util.Random;

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
        this("Activité", 2, 1);
    }

    /**
     * Constructeur de la classe Activité avec un nom spécifié.
     * Initialise une activité avec le nom spécifié et une durée de 0 minutes et une variance de 0 minutes.
     *
     * @param nom le nom de l'activité
     */
    public Activite(String nom) {
        this(nom, 2, 1);
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

    /**
     * Donne le temps de l'activité
     * @return le temps
     */
    public int getTemps(){return temps;}

    /**
     * Donne l'écart temps de l'activité
     * @return l'écart temps
     */
    public int getEcartTemps(){return ecartTemps;}

    /**
     * Donne la chaîne de caractère correspondante au delai de l'activité
     * @return le délai de l'activité
     */
    public String delai(){
            String tmp = Integer.toString(getTemps());
            String delta = Integer.toString(getEcartTemps());
            return "\n\t//Met du délai\n" +
                    "\tdelai(" + tmp + "," + delta + ");\n";
    }

    public String transfert(Etape successeur){
        int numSuccesseur = successeur.getNumero();
        return "\n\t//Passage de mon activité au successeur\n" +
                "\ttransfert("+ nom + getNumero() + "," + successeur.nom + numSuccesseur + ");\n";
    }

    /**
     * méthode qui permet d'afficher le delay et le transfère avec un successeur précis
     * @param successeur successeur de l'étape
     * @return le toC
     */
    protected String complementToC(Etape successeur){
        return delai() + transfert(successeur) + successeur.toC();
    }

    @Override
    public String toC() {
        if(nbSuccesseurs()>1) {//si l'activité pointe vers plusieurs étapes
            String currentC;
            StringBuilder code = new StringBuilder("\n\tint nb = (int)(rand() % ")
                    .append(nbSuccesseurs())
                    .append(");\n");
            int count = 0;
            for (Etape etp : etapes){
                currentC = complementToC(etp);
                code.append("\tif (nb == ")
                        .append(count)
                        .append("){\n")
                        .append(currentC)
                        .append("\t}");
                count++;
            }
            return code.toString();
        }else{
            Etape successeur = getSuccesseur();
            return complementToC(successeur);
        }
    }

    @Override
    public String constantes() {
        Etape successeur = getSuccesseur();
        String val = "#define " + getNom() + getNumero() + " " + getNumero() + "\n";
        for(Etape suc: etapes){
            val+=suc.constantes();
        }
        return val;
    }
}