package twisk.mondeIG;

import twisk.vues.Observateur;

import java.util.ArrayList;

/**
 * Classe qui indique aux vues qu'un qu'il y a eu un changement et leur demande mettre à jour leurs composants
 */
public class SujetObserve {

    /**
     * Collection d'observateurs qui sera utilisée pour appeler la méthode reagir() à chacun d'eux
     */
    private ArrayList<Observateur> obs;

    public SujetObserve(){
        obs = new ArrayList<>(10);
    }

    /**
     * Demande à toutes les vues connues par le monde de mettre à jour leurs composants
     */
    public void notifierObs() {
        for (int i = obs.size() - 1; i >= 0; i--) {
            obs.get(i).reagir();
        }
    }

    /**
     * Ajoute l'observateur dans la collection associée
     * @param obs l'observateur à ajouter
     */
    public void ajouterObs(Observateur obs){
        this.obs.add(obs);
    }
}
