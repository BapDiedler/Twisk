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
        Monde monde = new Monde() ;

        Etape etape1 = new Activite("musee",6,2) ;
        Etape guichet = new Guichet("guichet",2);
        Etape etape2 = new ActiviteRestreinte("boutique",5,2) ;
        Etape etape3 = new Activite("coucou",5,2);

        monde.aCommeEntree(etape1,etape3);

        etape1.ajouterSuccesseur(guichet,etape3) ;

        guichet.ajouterSuccesseur(etape2);

        monde.ajouter(etape1, guichet, etape2,etape3) ;

        monde.aCommeSortie(etape2,etape3) ;


        String codeC = monde.toC();

        KitC kit = new KitC();
        kit.creerEnvironment();
        kit.creerFichier(codeC);
        kit.compilation();
        kit.construireLaLibrairie();

        Simulation simu = new Simulation();
        simu.simuler(monde);
    }
}
