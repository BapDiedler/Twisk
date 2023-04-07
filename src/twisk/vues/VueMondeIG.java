package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurDeplacement;
import twisk.ecouteurs.EcouteurDrop;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

import java.util.Iterator;

/**
 * Affiche toutes les activités répertoriées dans le MondeIG
 */
public class VueMondeIG extends Pane implements Observateur {

    /**
     * Le monde
     */
    private MondeIG monde;

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
            if(monde.etapeSelectionne(e.getIdentifiant())){
                this.getChildren().add(new VueActiviteIG(monde,e,true));
            }
            else{
                this.getChildren().add(new VueActiviteIG(monde,e,false));
            }
            for(PointDeControleIG pdc: e){
                this.getChildren().add(new VuePointDeControleIG(pdc,monde));
            }
        }
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
        createVues();
    }
}
