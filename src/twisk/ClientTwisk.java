package twisk;

import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        Monde monde = new Monde();
        simulation.simuler(monde);
    }
}
