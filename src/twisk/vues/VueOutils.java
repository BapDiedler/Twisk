package twisk.vues;

import javafx.animation.PauseTransition;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import twisk.exception.MondeException;
import twisk.mondeIG.MondeIG;

import java.util.Objects;

/**
 * Cette classe représente le bouton qui permet d'ajouter une étape
 */
public class VueOutils extends TilePane implements Observateur {

    /**
     * Connaît le monde graphique
     */
    private final MondeIG monde;

    /**
     * Le bouton qui permet d'ajouter des activités
     */
    private Button ajoutActivite;

    private Button jouer;

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
        defineJouer();
        this.getChildren().addAll(typeEtape,jouer);
    }

    /**
     * Crée l'alerte qui sera affichée si l'input de l'utilisateur est incorrecte
     */
    private void createAlert(){
        // Chrnomètre pour enlever l'alerte au bout d'un certain temps
        PauseTransition chrono = new PauseTransition(Duration.seconds(10));
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle("Monde incorrect");
        alerte.setContentText("Le monde sur lequel on a lancé la simulation est incorrect.");
        alerte.setWidth(450);
        alerte.setHeight(300);
        alerte.getDialogPane().setStyle("-fx-font-size: 16;");
        alerte.show();
        chrono.play();
        chrono.setOnFinished(e -> alerte.close());
    }

    /**
     * Défini le bouton qui permet de lancer la simulation
     */
    private void defineJouer(){
        jouer = new Button();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/jouer.png")), 70,70,true,true);
        ImageView imageView = new ImageView(image);
        jouer.setGraphic(imageView);
        jouer.setMinSize(70,70);
        jouer.setOnAction(event -> {
            try {
                monde.simuler();
            } catch (MondeException e) {
                createAlert();
            }
        });
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
