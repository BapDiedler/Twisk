package twisk.vues;

import twisk.ecouteurs.EcouteurDelai;
import twisk.ecouteurs.EcouteurEcart;
import twisk.ecouteurs.EcouteurRenommer;
import twisk.ecouteurs.EcouteurSupprimer;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.mondeIG.MondeIG;

/**
 * Classe décrivant un menu qui permet de quitter et d'éditer
 */
public class VueMenu extends MenuBar implements Observateur{

    /**
     * Le monde
     */
    private MondeIG monde;

    /**
     * Menu comportant l'item qui permet de quitter
     */
    private Menu fichier;

    /**
     * Menu comportant l'item qui permet de supprimer les étapes sélectionnés et les arcs attachés
     */
    private Menu edition;

    /**
     * Item du menu qui permet de renommer l'étape lorsqu'une seule étape est sélectionnées
     */
    private MenuItem renommer;

    /**
     * Menu comportant les items qui permettent de sélectionner des entrées et des sorties
     */
    private Menu memuMonde;

    /**
     * Menu comportant les items qui permettent de changer le délai et l'écart de l'activité
     */
    private Menu parametres;

    /**
     * Item du menu qui permet de changer le délai
     */
    private MenuItem delai;

    /**
     * Item du menu qui permet de changer l'écart
     */
    private MenuItem ecart;

    /**
     * Constructeur de la vue
     */
    public VueMenu(MondeIG monde){
        this.monde = monde;
        monde.ajouterObs(this);
        defineFichier();
        defineEdition();
        defineMonde();
        defineParametre();
        this.getMenus().addAll(fichier,edition,memuMonde,parametres);
    }

    /**
     * Défini le menu Fichier
     */
    private void defineFichier(){
        this.fichier = new Menu("Fichier");
        MenuItem quitter = new MenuItem("Quitter");
        fichier.getItems().add(quitter);
        quitter.setOnAction(e -> Platform.exit());
    }

    /**
     * Défini le menu Edition
     */
    private void defineEdition(){
        this.edition = new Menu("Edition");

        MenuItem supprimer = new MenuItem("Supprimer");
        supprimer.setOnAction(new EcouteurSupprimer(monde));
        edition.getItems().add(supprimer);

        MenuItem deselectionner = new MenuItem("Désélectionner");
        deselectionner.setOnAction(e -> monde.deselectionner());
        edition.getItems().add(deselectionner);

        renommer = new MenuItem("Renommer");
        renommer.setOnAction(new EcouteurRenommer(monde));
        setAbleRenommer();
        edition.getItems().add(renommer);
    }

    /**
     * Défini le menu Monde
     */
    private void defineMonde(){
        this.memuMonde = new Menu("Monde");

        MenuItem entree = new MenuItem("Entrée");
        entree.setOnAction(e -> monde.setEntrees());
        memuMonde.getItems().add(entree);

        MenuItem sortie = new MenuItem("Sortie");
        sortie.setOnAction(e -> monde.setSortiees());
        memuMonde.getItems().add(sortie);
    }

    /**
     * Défini le menu Paramètre
     */
    private void defineParametre(){
        this.parametres = new Menu("Paramètre");

        delai = new MenuItem("Délai");
        delai.setOnAction(new EcouteurDelai(monde));
        parametres.getItems().add(delai);
        setAbleDelai();

        ecart = new MenuItem("Écart");
        ecart.setOnAction(new EcouteurEcart(monde));
        parametres.getItems().add(ecart);
        setAbleEcart();
    }

    /**
     * Rend le menuItem renommer actif si les conditions sont vérifiés (une seule étape sélectionnée).
     * La rend inactive sinon
     */
    private void setAbleRenommer(){
        boolean desactive = renommer.isDisable();
        if(monde.getNbSelectionnees()==1 && desactive){
            renommer.setDisable(false);
        } else if (!desactive) {
            renommer.setDisable(true);
        }
    }

    /**
     * Rend le menuItem délai actif si une seule étape est sélectionnée. La rend inactive sinon
     */
    private void setAbleDelai(){
        boolean desactive = delai.isDisable();
        if(monde.getNbSelectionnees()==1 && desactive){
            delai.setDisable(false);
        } else if (!desactive) {
            delai.setDisable(true);
        }
    }

    /**
     * Rend le menuItem écart actif si une seule étape est sélectionnée. La rend inactive sinon
     */
    private void setAbleEcart(){
        boolean desactive = ecart.isDisable();
        if(monde.getNbSelectionnees()==1 && desactive){
            ecart.setDisable(false);
        } else if (!desactive) {
            ecart.setDisable(true);
        }
    }

    @Override
    public void reagir() {
        setAbleRenommer();
        setAbleDelai();
        setAbleEcart();
    }
}
