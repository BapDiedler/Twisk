package twisk.monde;

import java.util.Iterator;

/**
 * @author Diedler et Litchner
 * classe représentant une étape du twisk.monde
 */
public abstract class Etape implements Iterable<Etape>{

    protected String nom;
    protected GestionnaireEtapes etapes;

    /**
     * constructeur de la classe Etape
     * @param nom le nom de l'Etape
     */
    public Etape(String nom){
        this.nom = nom;
        this.etapes = new GestionnaireEtapes();
    }

    /**
     * methode qui permet d'ajouter des successeurs à notre Etape
     * @param etapes étapes à ajouter
     */
    public void ajouterSuccesseur(Etape... etapes){
        this.etapes.ajouter(etapes);
    }

    /**
     * getter qui nous permet de savoir si une etape est une activité
     * @return vrai si l'étape est une activité sinon faux
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * getter qui nous permet de savoir si une etape est un guichet
     * @return vrai si l'étape est un guichet sinon faux
     */
    public boolean estUnGuichet(){
        return false;
    }

    /**
     * methode qui permet d'avoir un iterator sur les successeurs
     * @return un iterator de successeur
     */
    public Iterator<Etape> iterator(){
        return etapes.iterator();
    }

    /**
     * methode qui donne le nombre de successeurs que possède l'étape
     * @return un int
     */
    public int nbSuccesseurs(){
        return etapes.nbEtapes();
    }
}
