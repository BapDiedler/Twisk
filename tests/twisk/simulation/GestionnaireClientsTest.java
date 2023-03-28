package twisk.simulation;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import javax.swing.border.EtchedBorder;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    @Test
    void allerA() {
        GestionnaireClients gestionnaireClients = new GestionnaireClients();
        int[] pid = new int[1];
        gestionnaireClients.setClients(pid);
        Etape etape = new Activite();
        gestionnaireClients.allerA(pid[0],etape,0);
        Client client = gestionnaireClients.getClient(pid[0]);
        assertEquals(0,client.getRang());
        assertEquals(etape,client.getEtape());
    }
}