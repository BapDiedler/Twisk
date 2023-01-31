package twisk.monde;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuichetTest extends EtapeTest{

    @Test
    public void ajouterSuccesseur(){
        assertEquals(0,guichet.nbSuccesseurs());
        guichet.ajouterSuccesseur(activiteRestreinte);
        assertEquals(1,guichet.nbSuccesseurs());
        guichet.ajouterSuccesseur(activite);
        assertEquals(1,guichet.nbSuccesseurs());
    }

    @Test
    public void getNumeroSemaphore(){
        assertEquals(0,guichet.getNumeroSemaphore());
    }

}