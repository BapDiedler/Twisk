package twisk.mondeIG;

import org.junit.jupiter.api.Test;
import twisk.exception.MondeException;
import twisk.monde.Activite;

import static org.junit.jupiter.api.Assertions.*;

class MondeIGTest {

    @Test
    void verificationMondeValide() throws MondeException {
        MondeIG monde = new MondeIG();
        monde.ajouter("Activité");
        monde.ajouter("Activité");
        monde.getEtape(1).setEstSortie();
        monde.getEtape(0).setEstEntree();
        monde.getEtape(2).setEstEntree();
        monde.getEtape(0).setSuccesseurs(monde.getEtape(1));
        monde.getEtape(2).setSuccesseurs(monde.getEtape(1));
        //assertThrows(e->MondeException,monde.verificationMondeValide());
    }
}