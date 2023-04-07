package twisk.ecouteurs;

import twisk.exception.TwiskIncorrectInput;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Ecouteur lié au bouton délai et permet de changer sa valeur.
 */
public class EcouteurDelai implements EventHandler<ActionEvent> {

    /**
     * La boîte de dialogue qui permettra de changer le délai de l'activité
     */
    private TextInputDialog dialog;

    /**
     * Le monde
     */
    private MondeIG monde;

    public EcouteurDelai(MondeIG monde){
        this.monde = monde;
        dialog = new TextInputDialog();
    }

    /**
     * Crée la boîte de dialogue qui permettra de changer le délai de l'activité
     */
    private void createDialog(){
        dialog.setTitle("Nouveau délai");
        dialog.setHeaderText("Changement du délai de l'activité");
        dialog.setContentText("Veuillez saisir le nouveau délai de l'activité: ");
    }

    /**
     * Crée l'alerte qui sera affichée en cas d'erreur
     */
    private void createAlert(){
        // Chrnomètre pour enlever l'alerte au bout d'un certain temps
        PauseTransition chrono = new PauseTransition(Duration.seconds(10));
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle("Valeur donnée incorecte");
        alerte.setContentText("La valeur doit être supérieure à l'écart temps de l'étape");
        alerte.setWidth(450);
        alerte.setHeight(300);
        alerte.getDialogPane().setStyle("-fx-font-size: 16;");
        alerte.show();
        chrono.play();
        chrono.setOnFinished(e -> alerte.close());
    }

    @Override
    public void handle(ActionEvent actionEvent){
        createDialog();
        Optional<String> inputNom = dialog.showAndWait();
        if(inputNom.isPresent()){
            String input = inputNom.get();
            try {
                monde.setDelaiEtape(input);
            } catch (TwiskIncorrectInput e) {
                createAlert();
            }
        }
    }
}
