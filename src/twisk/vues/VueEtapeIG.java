package twisk.vues;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

import java.util.Objects;


/**
 * Décrit le composant graphique d'une étape
 */
public abstract class VueEtapeIG extends VBox implements Observateur {
    /**
     * Titre de l'étapeIG
     */
    protected Label titre;

    /**
     * information sur l'étape comme son temps
     */
    protected HBox informations;

    /**
     * délai et variance de temps ou nbJetons
     */
    protected Label infoTemps;

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

    /**
     * méthode qui permet d'appliquer l'image d'entrée sur les informations
     */
    protected void appliquerEntree(){
        if(etape.getEstEntree()){
            Image entree = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/check-in.png")), 30,30,true,true);
            ImageView entreeView = new ImageView(entree);
            informations.getChildren().add(0,entreeView);
        }
    }

    /**
     * méthode qui permet d'appliquer l'image de sortie sur les informations
     */
    protected void appliquerSortie(){
        if(etape.getEstSortie()){
            Image entree = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/check-out.png")), 30,30,true,true);
            ImageView entreeView = new ImageView(entree);
            if(etape.getEstEntree())
                informations.getChildren().add(2,entreeView);
            else
                informations.getChildren().add(1,entreeView);
        }
    }

    /**
     * Défini le texte dans le label, contiendra le nom de l'étape et les infos importantes (délai et écart temps
     * si c'est une activité, nombre de jetons si c'est un guichet)
     */
    protected abstract void defineNomLabel();

    /**
     * Définit les paramètres du label du titre de l'étape graphique
     */
    private void defineLabel(){
        titre = new Label();
        defineNomLabel();
        Font font = Font.font("Arial", FontWeight.BOLD, 16); // Crée une police en gras avec une taille de 14 points
        infoTemps.setFont(font);
        infoTemps.setAlignment(Pos.CENTER);
        titre.setPrefWidth(etape.getLargeur());
        titre.setPrefHeight(etape.getHauteur()*0.4);
        informations.setAlignment(Pos.CENTER);
        getChildren().addAll(informations,titre);
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
