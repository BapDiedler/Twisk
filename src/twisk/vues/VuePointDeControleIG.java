package twisk.vues;

import twisk.ecouteurs.EcouteurPointDeControle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

public class VuePointDeControleIG extends Circle implements Observateur {
    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Le point de contrôle
     */
    private PointDeControleIG pointDeControleIG;

    /**
     * Ecouteur du Point de contrôle
     */
    private EventHandler<MouseEvent> ecouteur;

    public VuePointDeControleIG(PointDeControleIG pointDeControleIG, MondeIG monde){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        this.pointDeControleIG = pointDeControleIG;
        setPos();
        this.ecouteur = new EcouteurPointDeControle(monde, pointDeControleIG);
        this.setOnMouseClicked(ecouteur);
        defineStyle();
    }

    private void setPos(){
        int x = pointDeControleIG.getPosXCentre();
        int y = pointDeControleIG.getPosYCentre();
        this.setCenterX(x);
        this.setCenterY(y);
    }

    private void defineStyle(){
        this.setRadius(8);
        this.setFill(Color.valueOf("01a3ff"));
    }
    @Override
    public void reagir() {
        setPos();
    }
}
