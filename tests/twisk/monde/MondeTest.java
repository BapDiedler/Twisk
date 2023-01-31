package twisk.monde;

import static org.junit.jupiter.api.Assertions.*;

class MondeTest {

    Monde monde;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.monde = new Monde();
        Etape prem = new Guichet("Queue",5);
        Etape deux = new ActiviteRestreinte("Activite",10,3);
        monde.ajouter(prem, deux);
        monde.aCommeEntree(prem);
        monde.aCommeSortie(deux);
    }

    @org.junit.jupiter.api.Test
    void nbEtapes1() {
        assertEquals(2, monde.nbEtapes());
    }

    @org.junit.jupiter.api.Test
    void nbEtapes2() {
        Etape activite = new Activite("Activ 2", 5, 1);
        monde.ajouter(activite);
        assertNotEquals(2, monde.nbEtapes());
    }

    @org.junit.jupiter.api.Test
    void nbGuichet1() {
        assertEquals(1, monde.nbGuichet());
    }

    @org.junit.jupiter.api.Test
    void nbGuichet2() {
        Etape guichet = new Guichet("Guichet",5);
        monde.ajouter(guichet);
        assertEquals(2, monde.nbGuichet());
    }
}