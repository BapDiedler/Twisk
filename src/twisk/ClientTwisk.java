package twisk;

import twisk.monde.Activite;

/**
 * cette classe se trouve dans le package twisk et représente la partie client du projet
 *
 *  @author Dielder et Litchner
 */

public class ClientTwisk {
    public static void main(String[] args) {
        Activite act = new Activite("Musée",5,2);
        Activite act2 = new Activite("Musée2",4,1);
        act.ajouterSuccesseur(act2);
        System.out.println(act.toC());
    }
}
