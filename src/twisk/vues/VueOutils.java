package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
     * Menu déroulant qui permet de choisir le type d'étape à ajouter
     */
    private MenuButton typeEtape;

    /**
     * Item qui permet de choisir d'ajouter une activité
     */
    private MenuItem typeActivite;

    /**
     * Item qui permet de choisir d'ajouter un guichet
     */
    private MenuItem typeGuichet;

    /**
     * Constructeur de la VueOutils
     * @param monde le monde
     */
    public VueOutils(MondeIG monde){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        defineBouton();
        defineMenuBouton();
        this.getChildren().add(typeEtape);
    }

    /**
     * Défini le bouton qui permet d'ajouter les activités
     */
    private void defineBouton(){
        this.ajoutActivite = new Button("+");
        ajoutActivite.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
        ajoutActivite.setMinSize(70,70);
        ajoutActivite.setOnAction(event -> monde.ajouter("Activité"));
        ajoutActivite.setTooltip(new Tooltip());
    }

    /**
     * Défini le menu déroulant qui permettra de choisir le type d'étape que l'on souhaite ajouter
     */
    private void defineMenuBouton(){
        this.typeEtape = new MenuButton("Ajouter étape");
        this.typeActivite = new MenuItem("Activité");
        typeActivite.setOnAction(event -> monde.ajouter("Activité"));
        this.typeGuichet = new MenuItem("Guichet");
        typeGuichet.setOnAction(event -> monde.ajouter("Guichet"));
        typeEtape.setMinSize(70,70);
        //typeEtape.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        typeEtape.getItems().addAll(typeActivite,typeGuichet);
    }

    @Override
    public void reagir() {

    }
}
