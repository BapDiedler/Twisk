package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.outils.TailleComposants;
import twisk.vues.VueEtapeIG;

/**
 * Ecouteur qui permet de déterminer la source (i.e l'activité que l'on va déplacer)
 */
public class EcouteurSource implements EventHandler<MouseEvent> {

    /**
     * La source
     */
    private VueEtapeIG etape;

    /**
     * Constructeur de l'écouteur
     * @param etape la source
     */
    public EcouteurSource(VueEtapeIG etape){
        this.etape = etape;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Création du dragboard et du clipboardcontent
        Dragboard dragboard = etape.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent ids = new ClipboardContent();
        etape.setId(etape.getIdentifiant());
        ids.putString(etape.getId());
        // Création du cadre qui contiendra une capture d'écran de l'activité que l'on déplace
        int largeur = TailleComposants.getLargeurActivite() + 40; // On ajoute le padding pour la taille
        int hauteur = TailleComposants.getHauteurActivite() + 40;
        WritableImage image = new WritableImage(largeur,hauteur);
        etape.snapshot(null, image);
        // Met l'image et les identifiants dans le dragboard
        dragboard.setDragView(image);
        dragboard.setContent(ids);
        // Consomme ici l'évènement de la souris pour ne pas le laisser se propager
        mouseEvent.consume();
    }
}