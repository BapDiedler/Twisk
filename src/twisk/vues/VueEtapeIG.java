package twisk.vues;

import twisk.ecouteurs.EcouteurEtape;
import twisk.ecouteurs.EcouteurSource;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;


/**
 * Décrit le composant graphique d'une étape
 */
public abstract class VueEtapeIG extends VBox implements Observateur {
    /**
     * Titre de l'étapeIG
     */
    protected Label titre;

    /**
     * Le monde graphique
     */
    protected MondeIG monde;

    /**
     * L'étape graphique décrite par la vue
     */
    protected EtapeIG etape;

    /**
     * Ecouteur de la vue qui permet de sélectionner l'étape cliquée ou de la déselectionné
     */
    protected EcouteurEtape ecouteur;

    /**
     * Prend vrai si l'étape est sléectionné, faux sinon
     * Une étape est sélectionné si l'utilisateur clique sur une étape, si l'étape
     * était déjà sélectionné, la déselectionne
     */
    protected boolean estSelectionne;

    /**
     * Constructeur d'une VueEtapeIG
     * @param monde le monde graphique
     * @param etape l'étape graphique
     */
    public VueEtapeIG(MondeIG monde, EtapeIG etape, boolean estSelectionne){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        this.etape = etape;
        this.estSelectionne = estSelectionne;
        defineTaille();
        defineLabel();
        defineStyle();
        defineEcouteurs();
    }

    /**
     * Défini le style de l'étape graphique
     */
    private void defineStyle(){
        titre.setStyle("-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #e3e3e3;");
        titre.setAlignment(Pos.CENTER);
        // Permet de mettre du padding entre la VBox et les composants
        this.setPadding(new Insets(20));
        defineDropShadow();
    }

    /**
     * Défini l'ombre qui entoure la vue étape
     */
    private void defineDropShadow(){
        DropShadow borderglow = new DropShadow();
        borderglow.setOffsetX(0f);
        borderglow.setOffsetY(0f);
        borderglow.setWidth(50);
        borderglow.setHeight(50);
        if(estSelectionne){
            borderglow.setColor(Color.RED);
            this.setStyle("-fx-border-color: red; " +
                    "-fx-border-width: 3px; " +
                    "-fx-background-color: #e3e3e3;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-border-radius: 30px;");
        }
        else{
            borderglow.setColor(Color.BLUE);
            this.setStyle("-fx-border-color: blue; " +
                    "-fx-border-width: 3px; " +
                    "-fx-background-color: #e3e3e3;" +
                    "-fx-background-radius: 30px;" +
                    "-fx-border-radius: 30px;");
        }
        this.setEffect(borderglow);
    }

    protected void defineNomLabel(){
        String nom = etape.getNom();
        titre.setText(nom);
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

    /**
     * Définit les paramètres du label du titre de l'étape graphique
     */
    private void defineLabel(){
        titre = new Label();
        defineNomLabel();
        titre.setPrefWidth(etape.getLargeur());
        titre.setPrefHeight(etape.getHauteur()*0.4);
        getChildren().add(titre);
        reagir();
    }

    /**
     * Défini la taille de la VBox
     */
    private void defineTaille(){
        this.setPrefHeight(etape.getHauteur());
        this.setPrefWidth(etape.getLargeur());
    }

    /**
     * Défini les écouteurs de la vue étape
     */
    private void defineEcouteurs(){
        this.ecouteur = new EcouteurEtape(etape,monde);
        this.setOnMouseClicked(ecouteur);
        this.setOnDragDetected(new EcouteurSource(this));
    }

    /**
     * Donne l'identifiant de l'étape correspondante à la vue
     * @return L'identifiant
     */
    public String getIdentifiant(){
        return etape.getIdentifiant();
    }

    /**
     * getter sur la hauteur de l'étape
     * @return la hauteur de l'étape
     */
    public int getHauteur(){
        return etape.getHauteur();
    }


    /**
     * getter sur la largeur de l'étape
     * @return la largeur de l'étape
     */
    public int getLargeur(){
        return etape.getLargeur();

    }

    @Override
    public void reagir() {
        defineNomLabel();
        relocate(etape.getPosX(), etape.getPosY());
    }
}
