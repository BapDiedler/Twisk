package twisk.monde;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {
    protected Etape activite;
    protected Etape guichet;
    protected Etape activiteRestreinte;
    protected Etape[] successeurs;

    @BeforeEach
    public void setUp(){
        guichet = new Guichet("guichet",0);
        activite = new Activite("main");
        activiteRestreinte = new ActiviteRestreinte("restreinte");
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