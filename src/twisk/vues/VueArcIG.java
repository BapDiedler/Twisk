package twisk.vues;

import twisk.ecouteurs.EcouteurArc;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**
 * Cette classe affiche l'arc crée par l'utilisateur.
 * Elle est constituée d'une ligne (Line) et d'un triangle pour au bout pour faire la flèche (Polyline)
 */
public class VueArcIG extends Pane implements Observateur{

    /**
     * L'arc correspondant à la vue
     */
    private ArcIG arc;
    /**
     * La ligne qui représente l'arc
     */
    private Line ligne;

    /**
     * Le triangle qui sert à faire la tête de la flèche
     */
    private Polyline tete;

    /**
     * Le monde
     */
    private MondeIG monde;

    public VueArcIG(MondeIG monde, ArcIG arc){
        super();
        this.monde = monde;
        monde.ajouterObs(this);
        this.arc = arc;
        this.ligne = new Line();
        defineLigne();
        this.tete = new Polyline();
        defineTete();
        defineStyle();
        this.setOnMouseClicked(new EcouteurArc(monde,arc));
        this.getChildren().addAll(ligne, tete);
        // Nécessaire pour l'arc sélectionné soit l'arc correct
        this.setPickOnBounds(false);
    }

    /**
     * Défini le style de l'arc
     */
    private void defineStyle(){
        ligne.setStrokeWidth(4);
        if(monde.arcSelectionne(arc)){
            ligne.setStyle("-fx-stroke: #FF0000");
            tete.setFill(Color.valueOf("FF0000"));
            tete.setStyle("-fx-stroke: #FF0000");
        }
        else{
            ligne.setStyle("-fx-stroke: #0000FF");
            tete.setFill(Color.valueOf("0000FF"));
            tete.setStyle("-fx-stroke: #0000FF");
        }
    }

    /**
     * Crée la ligne
     */
    private void defineLigne(){
        PointDeControleIG premier = arc.getPremPoint();
        ligne.setStartX(premier.getPosXCentre());
        ligne.setStartY(premier.getPosYCentre());
        PointDeControleIG deuxieme = arc.getDeuxPoint();
        ligne.setEndX(deuxieme.getPosXCentre());
        ligne.setEndY(deuxieme.getPosYCentre());
    }

    /**
     * Cree la tête de la ligne
     */
    private void defineTete(){
        double tailleTete = 25.0;
        PointDeControleIG p1 = arc.getPremPoint();
        double x1 = p1.getPosXCentre();
        double y1 = p1.getPosYCentre();
        PointDeControleIG p2 = arc.getDeuxPoint();
        double x2 = p2.getPosXCentre();
        double y2 = p2.getPosYCentre();
        // détermine l'angle
        double angle = Math.atan2(y2-y1,x2-x1) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        // premier point
        double premPointX = (- 1.0/2.0 * cos + Math.sqrt(3) / 2 * sin) * tailleTete + x2;
        double premPointY = (- 1.0/2.0 * sin - Math.sqrt(3) / 2 * cos) * tailleTete + y2;
        // deuxième point
        double deuxPointX = (1.0/2.0 * cos + Math.sqrt(3) / 2 * sin) * tailleTete + x2;
        double deuxPointY = (1.0/2.0 * sin - Math.sqrt(3) / 2 * cos) * tailleTete + y2;
        tete.getPoints().addAll(premPointX,premPointY,deuxPointX,deuxPointY, x2, y2);
    }

    @Override
    public void reagir() {

    }
}
