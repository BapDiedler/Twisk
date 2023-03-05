package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * classe permettant de gérer les collections d'étapes
 *
 * @author Diedler et Litchner
 */
public class GestionnaireEtapes implements Iterable<Etape>{

    /**
     * Étapes correspond à la liste d'étapes qui représente les successeurs
     *                          d'une étape ou les éléments du twist. Monde.
     */
    private ArrayList<Etape> etapes;

    /**
     * constructeur de la classe GestionnaireEtapes
     */
    public GestionnaireEtapes(){
        etapes = new ArrayList<>();
    }

    /**
     * methode ajouter qui ajoute une collection d'étape dans la liste
     * @param etapes collection à ajouter
     */
    public void ajouter(Etape... etapes){
        this.etapes.addAll(List.of(etapes));
    }

    /**
     * methode qui nous don ne le nombre d'étapes
     * @return la taille de l'attribut étapes
     */
    public int nbEtapes(){
        return etapes.size();
    }

    /**
     * méthode qui permet d'obtenir une étape
     * @param i indice de l'étape
     * @return l'étape demandée
     */
    public Etape getEtape(int i){
        return etapes.get(i);
    }

    /**
     * methode qui nous permet d'itérer sur étapes
     * @return un iterator d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }

    /**
     * methode qui affiche toutes les étapes d'un gestionnaire d'étapes
     * @return le string d'un
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Etape etape: etapes){
            builder.append(etape).append("\n");
        }
        return builder.toString();
    }
}
