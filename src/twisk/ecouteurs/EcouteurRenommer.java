package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

import java.util.Optional;

/**
 * Ecouteur lié au bouton renommer qui se situe dans le Menu Edition.
 * Il est cliquable que si l'utilisateur n'a sélectionné une seule étape
 */
public class EcouteurRenommer implements EventHandler<ActionEvent> {

    /**
     * La boîte de dialogue qui permettra de renommer l'étape
     */
    private TextInputDialog dialog;

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Constructeur de l'écouteur
     */
    public EcouteurRenommer(MondeIG monde){
        this.dialog = new TextInputDialog();
        this.monde = monde;
    }

    /**
     * Crée la boîte de dialogue qui permettra de renommer l'étape
     */
    private void createDialog(){
        dialog.setTitle("Nouveau nom");
        dialog.setHeaderText("Renommage de l'étape");
        dialog.setContentText("Veuillez saisir le nouveau nom de l'étape: ");
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        createDialog();
        Optional<String> inputNom = dialog.showAndWait();
        if(inputNom.isPresent()){
            String nom = inputNom.get();
            monde.renommerEtape(nom);
        }
    }
}
