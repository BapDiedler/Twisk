package twisk.monde;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Diedler et Litchner
 *
 * classe permettant de gérer les collections d'étapes
 */
public class GestionnaireEtapes implements Iterable<Etape>{

    /**
     * etapes correspond à la liste d'étapes qui représente les successeurs
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
     * @return la taille de l'attribut etapes
     */
    public int nbEtapes(){
        return etapes.size();
    }

    public int nbGuichet(){
        int cpt = 0;
        for(Etape e: etapes){
            if(e.estUnGuichet()){
                cpt++;
            }
        }
        return cpt;
    }

    /**
     * methode qui nous permet d'itérer sur étapes
     * @return un iterator d'Etape
     */
    @Override
    public Iterator<Etape> iterator() {
        return etapes.iterator();
    }
}
