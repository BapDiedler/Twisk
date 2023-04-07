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
     * @param monde monde sur lequel se trouve le guichet
     * @param guichet guichet qui est représenté par notre vue
     * @param estCLique booléen qui nous permet de savoir si le guichet est sélectionné
     */
    public VueGuichetIG(MondeIG monde, EtapeIG guichet, boolean estCLique){
        super(monde,guichet,estCLique);
    }
}
