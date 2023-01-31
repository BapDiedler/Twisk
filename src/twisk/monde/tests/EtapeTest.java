package twisk.monde.tests;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    private Etape activite;
    private Etape guichet;
    private Etape[] successeurs;

    @BeforeEach
    public void setUp(){
        guichet = new Guichet("guichet",0);
        activite = new Activite("main");
        successeurs = new Etape[]{new Activite("1"), new Activite("2"), new Activite("3")};
    }

    @Test
    void ajouterSuccesseur() {
        activite.ajouterSuccesseur();
        assertEquals(0, activite.nbSuccesseurs());
        activite.ajouterSuccesseur(successeurs);
        //on vérifie qu'il y est le bon nombre d'étapes qui le succèdent.
        assertEquals(3, activite.nbSuccesseurs());
    }

    @Test
    void estUneActivite() {
        assertFalse(guichet.estUneActivite());
        assertTrue(activite.estUneActivite());
    }

    @Test
    void estUnGuichet() {
        assertTrue(guichet.estUnGuichet());
        assertFalse(activite.estUnGuichet());
    }
}