package twisk.ecouteurs;

import twisk.exception.TwiskArcIncorrect;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Ecouteur d'un point de contrôle. Est actionné lorsque le point de contrôle correspondant est cliqué
 */
public class EcouteurPointDeControle implements EventHandler<MouseEvent> {

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Le point de contrôle correspondant
     */
    private PointDeControleIG point;

    /**
     * Constructeur de l'écouteur
     * @param monde le monde
     */
    public EcouteurPointDeControle(MondeIG monde, PointDeControleIG point){
        this.monde = monde;
        this.point = point;
    }

    /**
     * Crée l'alerte qui sera affichée en cas d'erreur
     */
    private void createAlert(){
        // Chrnomètre pour enlever l'alerte au bout d'un certain temps
        PauseTransition chrono = new PauseTransition(Duration.seconds(10));
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle("Création de l'arc impossible");
        alerte.setContentText("L'arc n'a pas été crée car il ne respecte pas les contraintes.");
        alerte.setWidth(450);
        alerte.setHeight(300);
        alerte.getDialogPane().setStyle("-fx-font-size: 16;");
        alerte.show();
        chrono.play();
        chrono.setOnFinished(e -> alerte.close());
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try{
            monde.pointClique(point);
        }
        catch(TwiskArcIncorrect e){
            createAlert();
        }
    }
}
