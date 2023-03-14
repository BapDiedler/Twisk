package twisk;

import twisk.monde.*;
import twisk.outils.KitC;
import twisk.simulation.Simulation;

/**
 * cette classe se trouve dans le package twisk et représente la partie client du projet
 *
 *  @author Dielder et Litchner
 */

public class ClientTwisk {
    public static void main(String[] args) {



        Monde monde = new Monde();

        Activite zoo = new Activite("balade au zoo", 3, 1);
        Guichet guichet = new Guichet("accès au toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Activite bob = new Activite("Bobo",2,1);

        zoo.ajouterSuccesseur(guichet,bob);
        guichet.ajouterSuccesseur(tob);
        monde.ajouter(zoo, tob, guichet,bob);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob,bob);

        Simulation s = new Simulation();
        s.setNbClients(10);
        s.simuler(monde);
    }
}
