package twisk.vues;

import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

/**
 * Décrit le composant graphique d'un guichet
 */
public class VueGuichetIG extends VueEtapeIG{

    /**
     * Le constructeur de VueGuichetIG
     * @param monde
     * @param guichet
     * @param estCLique
     */
    public VueGuichetIG(MondeIG monde, EtapeIG guichet, boolean estCLique){
        super(monde,guichet,estCLique);
    }
}
