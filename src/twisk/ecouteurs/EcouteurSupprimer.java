package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

/**
 * Ecouteur correspondant à l'item supprimer du Menu Edition
 */
public class EcouteurSupprimer implements EventHandler<ActionEvent> {

    private MondeIG monde;

    public EcouteurSupprimer(MondeIG monde){
        this.monde = monde;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        monde.supprimeSelection();
    }
}
