package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;

/**
 * Cette classe représente le bouton qui permet d'ajouter une étape
 */
public class VueOutils extends TilePane implements Observateur {

    /**
     * Connaît le monde graphique
     */
    private MondeIG monde;

    /**
     * Le bouton qui permet d'ajouter des activités
     */
    private Button ajoutActivite;

    /**
     * Constructeur de la VueOutils
     * @param monde le monde
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        this.ajoutActivite = new Button("+");
        ajoutActivite.setStyle("-fx-font-size: 30;");
        ajoutActivite.setMinSize(70,70);
        ajoutActivite.setOnAction(event -> monde.ajouter("Activité"));
        ajoutActivite.setTooltip(new Tooltip());
        this.getChildren().add(ajoutActivite);
    }

    @Override
    public void reagir() {

    }
}
