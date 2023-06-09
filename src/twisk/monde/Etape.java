package twisk.monde;

import twisk.outils.FabriqueNumero;
import java.util.Iterator;

/**
 * classe représentant une étape du twisk.monde
 * cette classe permet de représenter et factoriser le comportement d'une étape du monde
 *
 * @author Diedler et Litchner
 */
public abstract class Etape implements Iterable<Etape> {

    /**
     * numéro de l'étape
     */
    protected int numero;
    /**
     * nom de l'étape
     */
    protected String nom;
    /**
     * successeur de notre étape
     */
    protected GestionnaireEtapes etapes;

    /**
     * constructeur de la classe Etape
     *
     * @param nom le nom de l'Etape
     */
    public Etape(String nom) {
        this.nom = nom;
        this.numero = FabriqueNumero.getNumeroEtape();
        this.etapes = new GestionnaireEtapes();
    }

    /**
     * méthode qui transforme le nom en un nom valide pour le define
     */
    protected String passNom(){
        String val = nom.replaceAll("[^a-zA-Z0-9]","_");
        return "_"+val+"_";
    }

    /**
     * methode qui permet d'ajouter des successeurs à notre Etape
     *
     * @param etapes étapes à ajouter
     */
    public void ajouterSuccesseur(Etape... etapes) {
        if(etapes != null) {//si l'étape n'a pas été initialisée
            if (estUnGuichet() && nbSuccesseurs() == 0 && etapes[0].estUneActivite()) {
                this.etapes.ajouter(etapes[0]);
            } else if (estUneActivite()) {
                this.etapes.ajouter(etapes);
            }
        }
    }

    /**
     * setter sur le nom de l'étape
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * getter qui nous permet de savoir si une etape est une activité
     *
     * @return vrai si l'étape est une activité sinon faux
     */
    public boolean estUneActivite() {
        return false;
    }

    /**
     * getter qui nous permet de savoir si une etape est un guichet
     *
     * @return vrai si l'étape est un guichet sinon faux
     */
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * methode qui permet d'avoir un iterator sur les successeurs
     *
     * @return un iterator de successeur
     */
    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }

    /**
     * methode qui donne le nombre de successeurs que possède l'étape
     *
     * @return un int étant le nombre d'étapes
     */
    public int nbSuccesseurs() {
        return etapes.nbEtapes();
    }

    /**
     * getter du numéro de l'étape
     *
     * @return le numéro de l'étape
     */
    public int getNumero(){
        return numero;
    }

    /**
     * méthode qui retourne le numéro de semaphore
     *
     * @return 0 si c'est une activité sinon le numéro du sémaphore
     */
    public int getNumeroSemaphore(){
        return 0;
    }

    /**
     * Donne le nom de l'étape
     *
     * @return Le nom de l'étape
     */
    public String getNom(){ return nom;}

    /**
     * Dans le cas où dans notre monde, chaque étape connait un unique successeur (sauf la sortie)
     * donne le successeur de l'étape
     *
     * @return le successeur de l'étape
     */
    public Etape getSuccesseur(){
        Iterator<Etape> ite = iterator();
        return ite.next();
    }

    /**
     * getter du nombre de jetons dans le guichet
     * @return le nombre de jetons
     */
    public int getNbJetons(){
        return 0;
    }

    /**
     * methode qui affiche une étape et ses successeurs
     *
     * @return le string d'une étape
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.nom)
                .append(" : ")
                .append(nbSuccesseurs())
                .append(" successeur(s)");
        for (Etape etape : etapes) {
            builder.append("-")
                    .append(etape.nom);
        }
        return builder.toString();
    }

    /**
     * Donne la chaîne de caractère qui a la fonction de delai en c.
     *
     * @return une chaîne de caractère
     */
    public String delai(){
        return "";
    }

    /**
     * Donne la chaîne de caractère qui correspondant à la fonction du transfert de l'Etape actuelle
     * au successeur
     *
     * @return la chaîne de caractère du transfert
     */
    protected String transfert(){
        Etape successeur = getSuccesseur();
        int numSuccesseur = successeur.getNumero();
        return "\n\t//Passage de mon activité au successeur\n" +
                "\ttransfert("+ passNom() + getNumero() + "," +
                successeur.passNom() + numSuccesseur + ");\n";
    }

    /**
     * Donne le code C correspondant à l'étape sous forme de String
     *
     * @return Le code C correspondant à l'étape sous forme de String
     */
    public abstract String toC();

    /**
     * Méthode qui renvoie le String correspondant aux constantes définies après les includes
     * dans le code C généré
     *
     * @return le String contenant les constantes
     */
    public abstract String constantes();
}
