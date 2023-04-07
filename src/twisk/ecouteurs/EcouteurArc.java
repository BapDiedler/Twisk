package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

/**
 * Ecouteur d'un point de contrôle. Permet de savoir lorsqu'un point de contrôle est sélectionné
 */
public class EcouteurArc implements EventHandler<MouseEvent> {

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * L'arc
     */
    private ArcIG arc;

    /**
     * Constructeur de l'écouteur
     * @param monde Le monde
     * @param arc L'arc
     */
    public EcouteurArc(MondeIG monde, ArcIG arc){
        this.monde = monde;
        this.arc = arc;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        monde.ajouterArcSelectionne(arc);
    }
}
