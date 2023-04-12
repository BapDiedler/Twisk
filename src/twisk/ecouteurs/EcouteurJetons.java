package twisk.ecouteurs;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.exception.TwiskIncorrectInput;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

public class EcouteurJetons implements EventHandler<ActionEvent> {

    /**
     * La boîte de dialogue qui permettra de changer le nombre de jetons du guichet
     */
    private TextInputDialog dialog;

    /**
     * Le monde
     */
    private MondeIG monde;

    public EcouteurJetons(MondeIG monde){
        this.monde = monde;
        dialog = new TextInputDialog();
    }

    /**
     * Crée la boîte de dialogue qui permettra de changer le nombre de jetons de l'activité
     */
    private void createDialog(){
        dialog.setTitle("Nouveau nombre de jeton");
        dialog.setHeaderText("Changement du nombre de jeton du guichet");
        dialog.setContentText("Veuillez saisir le nouveau nombre de jeton du guichet: ");
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
                monde.setNbJetonsEtape(input);
            } catch (TwiskIncorrectInput e) {
                createAlert();
            }
        }
    }
}
