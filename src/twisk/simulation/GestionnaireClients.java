package twisk.simulation;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.Iterator;

/**
 * classe qui permet de gérer les instances des clients
 */
public class GestionnaireClients implements Iterable<Client>{

    /**
     * clients se trouvant dans le monde
     */
    private Client[] clients;


    /**
     * constructeur de la classe
     */
    public GestionnaireClients(){
    }


    /**
     * méthode qui permet de mettre à jour les clients dans le monde
     *
     * @param tabClients tableau des clients se trouvant dans le monde
     */
    public void setClients(int ... tabClients){
        clients = new Client[tabClients.length];
        for(int i=0; i<clients.length; i++){
            clients[i] = new Client(tabClients[i]);
        }
    }


    /**
     * méthode qui permet de déplacer un client dans une nouvelle étape
     *
     * @param numeroDeCleint numero du client qui se déplace
     * @param etape nouvelle étape du client
     * @param rang position dans la file d'attente du client
     */
    public void allerA(int numeroDeCleint, Etape etape, int rang){
        clients[numeroDeCleint].allerA(etape, rang);
    }

    @Override
    public Iterator<Client> iterator() {
        return Arrays.stream(clients).iterator();
    }
}
