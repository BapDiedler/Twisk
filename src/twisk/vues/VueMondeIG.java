package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurDeplacement;
import twisk.ecouteurs.EcouteurDrop;
import twisk.mondeIG.*;

import java.util.Iterator;

/**
 * Affiche toutes les activités répertoriées dans le MondeIG
 */
public class VueMondeIG extends Pane implements Observateur {

    /**
     * Le monde qui est représenté par la vue
     */
    private final MondeIG monde;

    /**
     * Constructeur de la VueMonde
     */
    public VueMondeIG(MondeIG monde){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        createVues();
        this.setOnDragOver(new EcouteurDeplacement(this));
        this.setOnDragDropped(new EcouteurDrop(monde));
    }

    /**
     * Crée les Vues pour les étapes et les points de contrôles
     */
    private void createVues(){
        Iterator<ArcIG> iterator = monde.iteratorArc();
        while(iterator.hasNext()){
            ArcIG arc = iterator.next();
            this.getChildren().add(new VueArcIG(monde,arc));
        }
        for(EtapeIG e: monde){
            ajouterEtapes(e, monde.etapeSelectionne(e.getIdentifiant()));
            for(PointDeControleIG pdc: e){
                this.getChildren().add(new VuePointDeControleIG(pdc,monde));
            }
        }
    }

    /**
     * méthode qui permet d'ajouter les étapes au monde
     * @param etape étape que l'on ajoute dans les vues
     * @param estSelectionnee paramètre qui nous permet de savoir si l'étape est sélectionné
     */
    private void ajouterEtapes(EtapeIG etape, boolean estSelectionnee){
        if(etape.estUneActivite()) {
            this.getChildren().add(new VueActiviteIG(monde, etape, estSelectionnee));
        }else{
            this.getChildren().add(new VueGuichetIG(monde,etape,estSelectionnee));
        }
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
        createVues();
    }
}
