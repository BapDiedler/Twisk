package twisk;

import twisk.monde.Etape;
import twisk.mondeIG.EtapeIG;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe qui va faire le lien entre les étapes graphiques et celle du monde
 */
public class CorrespondanceEtapes {
    HashMap<EtapeIG, Etape> etapes;

    /**
     * constructeur vide
     */
    public CorrespondanceEtapes(){
        etapes = new HashMap<>(10);
    }

    /**
     * méthode qui permet d'ajouter des relations entre les étapes
     *
     * @param etapeIG étape graphique
     * @param etape étape
     */
    public void ajouter(EtapeIG etapeIG, Etape etape){
        etapes.put(etapeIG,etape);
    }

    /**
     * getter qui permet d'obtenir un Etape à l'aide de l'étape graphique
     *
     * @param etapeIG étape graphique
     *
     * @return l'étape qui est en relation avec etapeIG
     */
    public Etape get(EtapeIG etapeIG){
        return etapes.get(etapeIG);
    }
}
