package twisk.monde;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteRestreinteTest extends EtapeTest{

    @Test
    void ajouterSuccesseur() {
        assertEquals(0,activiteRestreinte.nbSuccesseurs());
        activiteRestreinte.ajouterSuccesseur(activite);
        assertEquals(1,activiteRestreinte.nbSuccesseurs());
        activiteRestreinte.ajouterSuccesseur(guichet);
        assertEquals(2,activiteRestreinte.nbSuccesseurs());
    }
}