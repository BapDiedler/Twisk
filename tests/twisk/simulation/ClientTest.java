package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    public void allerA(){
        Client client = new Client(1);
        assertEquals(null,client.getEtape());
        Etape etape = new Activite();
        client.allerA(etape,1 );
        assertEquals(etape,client.getEtape());
        assertEquals(1,client.getRang());
    }

}