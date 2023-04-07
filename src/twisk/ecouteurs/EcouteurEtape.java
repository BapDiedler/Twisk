package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Ecouteur d'une étape. Est actionné lorsque l'étape est cliquée
 */
public class EcouteurEtape implements EventHandler<MouseEvent> {

    /**
     * L'étape correspondante à l'écouteur
     */
    private EtapeIG etape;

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Constructeur de l'écouteur
     * @param etape L'étape correspondante
     */
    public EcouteurEtape(EtapeIG etape, MondeIG monde){
        this.etape = etape;
        this.monde = monde;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        monde.ajouterEtapeSelectionne(etape);
    }
}
