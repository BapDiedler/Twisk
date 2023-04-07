package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.mondeIG.MondeIG;


/**
 * Ecouteur activé au moment du drop
 */
public class EcouteurDrop implements EventHandler<DragEvent> {

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Constructeur de l'écouteur
     * @param monde Le monde
     */
    public EcouteurDrop(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        boolean valide = false;
        if(dragboard.hasString()){
            String id = dragboard.getString();
            int posX = (int) dragEvent.getX();
            int posY = (int) dragEvent.getY();
            monde.deplacerEtape(id,posX,posY);
            monde.notifierObs();
            valide = true;
        }
        dragEvent.setDropCompleted(valide);
        dragEvent.consume();
    }
}
