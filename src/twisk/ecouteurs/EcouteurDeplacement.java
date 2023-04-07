package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import twisk.vues.VueMondeIG;

/**
 * Ecouteur qui permet d'accepter le drop
 */
public class EcouteurDeplacement implements EventHandler<DragEvent> {

    /**
     * Le monde
     */
    private VueMondeIG monde;

    /**
     * Constructeur de l'écouteur
     * @param monde Le monde
     */
    public EcouteurDeplacement(VueMondeIG monde){this.monde = monde;}

    @Override
    public void handle(DragEvent dragEvent) {
        if(dragEvent.getGestureSource() != monde && dragEvent.getDragboard().hasString()){
            dragEvent.acceptTransferModes(TransferMode.MOVE);
        }
        dragEvent.consume();
    }
}
