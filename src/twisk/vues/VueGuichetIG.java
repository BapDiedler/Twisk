package twisk.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

/**
 * Décrit le composant graphique d'un guichet
 */
public class VueGuichetIG extends VueEtapeIG{

    private final HBox filAttente;
    private Rectangle[] zoneAttente;

    /**
     * Le constructeur de VueGuichetIG
     * @param monde monde sur lequel se trouve le guichet
     * @param guichet guichet qui est représenté par notre vue
     * @param estCLique booléen qui nous permet de savoir si le guichet est sélectionné
     */
    public VueGuichetIG(MondeIG monde, EtapeIG guichet, boolean estCLique){
        super(monde,guichet,estCLique);
        filAttente = new HBox();
        this.getChildren().add(filAttente);
        zoneAttente = new Rectangle[6];
        for(int i=0; i< zoneAttente.length; i++){
            zoneAttente[i] = new Rectangle(TailleComposants.getLargeurGuichet()*0.8/6,TailleComposants.getLargeurGuichet()*0.8/6);
        }
        filAttente.getChildren().addAll(zoneAttente);
        defineStyle();
    }

    /**
     * Défini le style de l'étape graphique
     */
    private void defineStyle(){
        if(estSelectionne) {
            for (Rectangle rectangle : zoneAttente) {
                rectangle.setFill(Color.WHITE);
                rectangle.setArcHeight(10);
                rectangle.setArcWidth(10);
                rectangle.setStroke(Color.RED);
            }
        }
        else{
            for (Rectangle rectangle : zoneAttente) {
                rectangle.setFill(Color.WHITE);
                rectangle.setArcHeight(10);
                rectangle.setArcWidth(10);
                rectangle.setStroke(Color.WHITE);
            }
        }
        filAttente.setSpacing(1);
        filAttente.setAlignment(Pos.CENTER);
    }

    protected void defineNomLabel(){
        String nom = etape.getNom();
        String jetons = etape.getNbJetons() + " jetons";
        titre.setText(nom + ": " + jetons);
        Image entree = new Image(getClass().getResourceAsStream("/check-in.png"), 30,30,true,true);
        Image sortie = new Image(getClass().getResourceAsStream("/check-out.png"), 30,30,true,true);
        ImageView entreeView = new ImageView(entree);
        ImageView sortieView = new ImageView(sortie);
        if(etape.estEntreeEtEstSortie()){
            HBox images = new HBox();
            images.getChildren().addAll(entreeView,sortieView);
            titre.setGraphic(images);
        } else if (etape.getEstEntree()) {
            titre.setGraphic(entreeView);
        } else if (etape.getEstSortie()) {
            titre.setGraphic(sortieView);
        }
    }
}