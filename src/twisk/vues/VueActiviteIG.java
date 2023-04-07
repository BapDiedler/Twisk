package twisk.vues;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.TailleComposants;

/**
 * Décrit le composant graphique d'une activité
 */
public class VueActiviteIG extends VueEtapeIG{

    /**
     * Zone destinée à contenir les clients
     */
    private final HBox zoneClients;

    /**
     * Le constructeur de VueActiviteIG
     * @param monde le monde graphique
     * @param activite l'activite graphique
     * @param estClique si l'activité est sélectionnée ou non
     */
    public VueActiviteIG(MondeIG monde, EtapeIG activite, boolean estClique){
        super(monde,activite, estClique);
        zoneClients = new HBox();
        this.getChildren().add(zoneClients);
        defineStyle();
    }

    /**
     * Défini le style de l'étape graphique
     */
    private void defineStyle(){
        if(estSelectionne) {
            zoneClients.setStyle("-fx-background-color: #FFFFFF;" +
                    "-fx-border-color: #ff0000; " +
                    "-fx-border-width: 3px; " +
                    "-fx-background-radius: 30px;" +
                    "-fx-border-radius: 30px;");
        }
        else{
            zoneClients.setStyle("-fx-background-color: #FFFFFF;" +
                    "-fx-border-color: #5b80ff; " +
                    "-fx-border-width: 3px; " +
                    "-fx-background-radius: 30px;" +
                    "-fx-border-radius: 30px;");
        }
        zoneClients.setAlignment(Pos.CENTER);
        zoneClients.setPrefHeight(TailleComposants.getHauteurActivite()*0.6);
        zoneClients.setPrefWidth(TailleComposants.getLargeurActivite()*0.8);
    }

    @Override
    protected void defineNomLabel(){
        String nom = etape.getNom();
        String delaiEcart = etape.getDelai() + " ± " + etape.getEcart();
        titre.setText(nom + ": " + delaiEcart);
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
