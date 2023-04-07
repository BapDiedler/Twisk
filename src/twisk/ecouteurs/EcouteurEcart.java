package twisk.ecouteurs;

import twisk.exceptions.TwiskIncorrectInput;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.util.Duration;
import twisk.mondeIG.MondeIG;

import java.util.Optional;


/**
 * Ecouteur lié au bouton écart qui permet de changer la valeur de l'écart temps de l'activité
 */
public class EcouteurEcart implements EventHandler<ActionEvent> {

    /**
     * La boîte de dialogue qui permettra de changer l'écart temps de l'activité
     */
    private TextInputDialog dialog;

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Constructeur de l'écouteur
     * @param monde Le monde
     */
    public EcouteurEcart(MondeIG monde){
        this.monde = monde;
        this.dialog = new TextInputDialog();
    }

    /**
     * Crée la boîte de dialogue qui permettra de changer l'écart temps de l'activité
     */
    private void createDialog(){
        dialog.setTitle("Nouvel écart temps");
        dialog.setHeaderText("Changement de l'écart temps de l'activité");
        dialog.setContentText("Veuillez saisir le nouvel écart temps de l'activité: ");
    }

    /**
     * Crée l'alerte qui sera affichée si l'input de l'utilisateur est incorrecte
     */
    private void createAlert(){
        // Chrnomètre pour enlever l'alerte au bout d'un certain temps
        PauseTransition chrono = new PauseTransition(Duration.seconds(10));
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle("Valeur donnée incorecte");
        alerte.setContentText("La valeur doit comprise entre 1 et le délai de l'étape.");
        alerte.setWidth(450);
        alerte.setHeight(300);
        alerte.getDialogPane().setStyle("-fx-font-size: 16;");
        alerte.show();
        chrono.play();
        chrono.setOnFinished(e -> alerte.close());
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        createDialog();
        Optional<String> inputNom = dialog.showAndWait();
        if(inputNom.isPresent()){
            String input = inputNom.get();
            try {
                monde.setEcartEtape(input);
            } catch (TwiskIncorrectInput e) {
                createAlert();
            }
        }
    }
}
