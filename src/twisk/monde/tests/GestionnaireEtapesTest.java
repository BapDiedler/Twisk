package twisk.monde.tests;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtapes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GestionnaireEtapesTest {

    GestionnaireEtapes gestionnaireEtapes;
    Etape activite1;
    Etape activite2;
    Etape activite3;

    @BeforeEach
    void setUp() {
        gestionnaireEtapes = new GestionnaireEtapes();
        activite1 = new Activite("");
        activite2 = new Activite("");
        activite3 = new Activite("");
    }

    @Test
    void ajouter() {
        // ajout d'aucune Etape
        gestionnaireEtapes.ajouter();
        Assertions.assertEquals(0, gestionnaireEtapes.nbEtapes());
        //ajout de deux activités
        gestionnaireEtapes.ajouter(activite1, activite2);
        Assertions.assertEquals(gestionnaireEtapes.nbEtapes(), 2);
        //ajout d'une activité en plus des deux autres
        gestionnaireEtapes.ajouter(activite3);
        Assertions.assertEquals(3, gestionnaireEtapes.nbEtapes());
    }

    @Test
    void iterator() {
        //on regarde si l'iterator itère bien sur toutes les étapes
        gestionnaireEtapes.ajouter(activite1,activite2,activite3);
        int cpt = 0;
        for (Etape etape : gestionnaireEtapes) {
            cpt++;
        }
        Assertions.assertEquals(cpt, 3);
    }


}