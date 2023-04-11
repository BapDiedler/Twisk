package twisk.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;

/**
 * Décrit le composant graphique d'un guichet
 */
public class VueGuichetIG extends VueEtapeIG{

    /**
     * Le constructeur de VueGuichetIG
     * @param monde monde sur lequel se trouve le guichet
     * @param guichet guichet qui est représenté par notre vue
     * @param estCLique booléen qui nous permet de savoir si le guichet est sélectionné
     */
    public VueGuichetIG(MondeIG monde, EtapeIG guichet, boolean estCLique){
        super(monde,guichet,estCLique);
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
