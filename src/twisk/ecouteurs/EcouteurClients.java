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

/**
 * Ecouteur qui permet de changer le nombre de client dans le monde
 */
public class EcouteurClients implements EventHandler<ActionEvent> {
    /**
     * La boîte de dialogue qui permettra de changer le nombre de clients dans le monde
     */
    private TextInputDialog dialog;

    /**
     * Le monde
     */
    private MondeIG monde;

    public EcouteurClients(MondeIG monde){
        this.monde = monde;
        this.dialog = new TextInputDialog();
    }

    /**
     * Crée la boîte de dialogue qui permettra de renommer l'étape
     */
    private void createDialog(){
        dialog.setTitle("Changement du nombre de clients dans le monde");
        dialog.setHeaderText("Changement du nombre de clients");
        dialog.setContentText("Le nombre de client actuel dans le monde est de: " + monde.getNbClients() + ".\nVeuillez saisir le nouveau nombre de clients dans le monde: ");
    }

    /**
     * Crée l'alerte qui sera affichée si l'input de l'utilisateur est incorrecte
     */
    private void createAlert(){
        // Chrnomètre pour enlever l'alerte au bout d'un certain temps
        PauseTransition chrono = new PauseTransition(Duration.seconds(10));
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle("Valeur donnée incorecte");
        alerte.setContentText("La valeur doit comprise entre 1 et 50.");
        alerte.setWidth(450);
        alerte.setHeight(300);
        alerte.getDialogPane().setStyle("-fx-font-size: 16;");
        alerte.show();
        chrono.play();
        chrono.setOnFinished(e -> alerte.close());
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            createDialog();
            Optional<String> inputNom = dialog.showAndWait();
            if(inputNom.isPresent()){
                String input = inputNom.get();
                monde.setNbClients(input);
            }
        }
        catch (TwiskIncorrectInput e){
            createAlert();
        }
    }
}
