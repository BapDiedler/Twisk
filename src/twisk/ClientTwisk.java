package twisk;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Monde monde = new Monde();
        Etape activité = new Activite();
        monde.ajouter(activité);
        monde.aCommeEntree(activité);
        monde.aCommeSortie(activité);
        simulation.simuler(monde);
    }
}
