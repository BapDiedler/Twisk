package twisk.simulation;

import twisk.monde.Etape;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * classe qui permet de gérer les instances des clients
 */
public class GestionnaireClients implements Iterable<Client>{

    /**
     * clients se trouvant dans le monde
     */
    private HashMap<Integer,Client> clients;


    /**
     * constructeur de la classe
     */
    public GestionnaireClients(){
    }

    /**
     * constructeur de la classe
     *
     * @param tabClients tableau des clients se trouvant dans le monde
     */
    public GestionnaireClients(int ... tabClients){
        setClients(tabClients);
    }


    /**
     * méthode qui permet de mettre à jour les clients dans le monde
     *
     * @param tabClients tableau des clients se trouvant dans le monde
     */
    public void setClients(int ... tabClients){
        clients = new HashMap<>(tabClients.length);
        for (int tabClient : tabClients) {
            clients.put(tabClient, new Client(tabClient));
        }
    }


    /**
     * méthode qui permet de déplacer un client dans une nouvelle étape
     *
     * @param numeroDeClient numero du client qui se déplace
     * @param etape nouvelle étape du client
     * @param rang position dans la file d'attente du client
     */
    public void allerA(int numeroDeClient, Etape etape, int rang){
        clients.get(numeroDeClient).allerA(etape, rang);
    }


    /**
     * méthode qui permet de nettoyer les clients du monde
     */
    public void nettoyer(){
        this.clients = null;
    }

    @Override
    public Iterator<Client> iterator() {
        return clients.values().iterator();
    }
}
