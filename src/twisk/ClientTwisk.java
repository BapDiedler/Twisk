package twisk;

import twisk.exception.TwiskClassLoaderException;
import twisk.exception.TwiskException;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * cette classe se trouve dans le package twisk et représente la partie client du projet
 *
 *  @author Dielder et Litchner
 */

public class ClientTwisk {

    /**
     * Le premier monde crée
     */
    private Monde premierMonde;

    /**
     * Le deuxième monde crée
     */
    private Monde deuxiemeMonde;

    /**
     * Classe simulation chargée par le ClassLoaderPerso
     */
    private Class<?> simulationClass;

    /**
     * L'object Simulation
     */
    private Object sim;

    /**
     * Fonction de Simulation qui permet de définir le nombre de clients
     */
    private Method setNbClients;

    /**
     * Fonction de Simulation qui permet de simuler
     */
    private Method simuler;

    /**
     * constructeur de la classe qui s'occupe de la simulation des mondes
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    public ClientTwisk() throws TwiskClassLoaderException {
        preparationSimulation();
        creePremierMonde();
        startSimulation(premierMonde);
        preparationSimulation();
        creeDeuxiemeMonde();
        startSimulation(deuxiemeMonde);

    }

    /**
     * Charge tout ce qui est nécessaire pour lancer la simulation. Nécessaire entre chaque simulation
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void preparationSimulation() throws TwiskClassLoaderException {
        chargeSimulationClass();
        initSimulation();
        chargeSetNbClients();
        chargeSimulation();
    }

    /**
     * Utilise le ClassLoaderPerso pour charger la classe simuler
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void chargeSimulationClass() throws TwiskClassLoaderException {
        simulationClass = null;
        ClassLoader parent = this.getClass().getClassLoader();
        ClassLoaderPerso classLoader = new ClassLoaderPerso(parent);
        try {
            simulationClass = classLoader.loadClass("twisk.simulation.Simulation");
        } catch (ClassNotFoundException e) {
            throw new TwiskClassLoaderException("La classe qui doit être chargée par le ClassLoaderPerso n'existe pas.");
        }
    }

    /**
     * Initialisation de l'object Simulation
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void initSimulation() throws TwiskClassLoaderException {
        Constructor cons;
        try {
            cons = simulationClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new TwiskClassLoaderException("Le constructeur par défaut n'a pas été trouvé");
        }
        try {
            sim = cons.newInstance();
        } catch (InstantiationException e) {
            throw new TwiskClassLoaderException("Simulation n'a pas pu être instanciée");
        } catch (IllegalAccessException e) {
            throw new TwiskClassLoaderException("L'utilisateur n'a pas les droits sur le fichier Simulation");
        } catch (InvocationTargetException e) {
            throw new TwiskClassLoaderException("Problème lors de l'instanciation de l'objet Simulation");
        }
    }

    /**
     * Charge la méthode setNbClients de Simulation
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void chargeSetNbClients() throws TwiskClassLoaderException {
        try {
            setNbClients = simulationClass.getMethod("setNbClients",int.class);
        } catch (NoSuchMethodException e) {
            throw new TwiskClassLoaderException("La méthode setNbClients n'a pas été trouvée");
        }
    }

    /**
     * Charge la méthode simulation de Simulation
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void chargeSimulation() throws TwiskClassLoaderException {
        try {
            simuler = simulationClass.getMethod("simuler",Monde.class);
        } catch (NoSuchMethodException e) {
            throw new TwiskClassLoaderException("La méthode setNbClients n'a pas été trouvée");
        }
    }

    /**
     * Crée le premier monde
     */
    private void creePremierMonde(){
        resetCompteurs();
        premierMonde = new Monde();

        Activite zoo = new Activite("balade au zoo", 3, 1);
        Guichet guichet = new Guichet("accès au toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Activite bob = new Activite("Bobo",2,1);

        zoo.ajouterSuccesseur(guichet,bob);
        guichet.ajouterSuccesseur(tob);
        premierMonde.ajouter(zoo, tob, guichet,bob);

        premierMonde.aCommeEntree(zoo);
        premierMonde.aCommeSortie(tob,bob);
    }

    /**
     * Réinitialise les compteurs d'étapes et de sémaphores. Utile avant de créer un nouveau monde
     */
    private void resetCompteurs(){
        FabriqueNumero.resetCptEtapes();
        FabriqueNumero.resetCptSem();
    }

    /**
     * création du deuxième monde
     */
    /**private void creeDeuxiemeMonde(){
        resetCompteurs();
        deuxiemeMonde = new Monde();
        Activite jardin = new Activite("Promenade au jardin", 5,4);
        Guichet queueBoutique = new Guichet("Boutique souvenirs",3);
        ActiviteRestreinte boutique = new ActiviteRestreinte("Boutique",3,2);
        Guichet queueMusee = new Guichet("Queue du musée",1);
        ActiviteRestreinte musee = new ActiviteRestreinte("Visite du musée", 5,3);

        jardin.ajouterSuccesseur(queueMusee,queueBoutique);
        queueBoutique.ajouterSuccesseur(boutique);
        queueMusee.ajouterSuccesseur(musee);
        deuxiemeMonde.ajouter(jardin,queueBoutique,queueMusee,musee,boutique);

        deuxiemeMonde.aCommeEntree(jardin);
        deuxiemeMonde.aCommeSortie(musee,boutique);

    }*/

    private void creeDeuxiemeMonde(){
        resetCompteurs();
        deuxiemeMonde = new Monde();
        Activite jardin = new Activite();
        Activite cuisine = new Activite();
        Activite salon = new Activite();
        Activite salle_de_bain = new Activite();
        Activite chambre = new Activite();

        deuxiemeMonde.ajouter(jardin,cuisine,salon,salle_de_bain,chambre);
        deuxiemeMonde.aCommeEntree(jardin);
        jardin.ajouterSuccesseur(cuisine);
        cuisine.ajouterSuccesseur(salon);
        salon.ajouterSuccesseur(salle_de_bain);
        salle_de_bain.ajouterSuccesseur(chambre);
        deuxiemeMonde.aCommeSortie(chambre);
    }



    /**
     * Défini le nombre de clients puis lance la simulation sur le monde passé en paramètre
     * @param monde Le monde sur lequel on lance la simulation
     * @throws TwiskClassLoaderException Exception levée en cas de soucis lié au ClassLoaderPerso
     */
    private void startSimulation(Monde monde) throws TwiskClassLoaderException {
        try {
            setNbClients.invoke(sim,15);
        } catch (IllegalAccessException e) {
            throw new TwiskClassLoaderException("Manque d'accès à la méthode setNbClients");
        } catch (InvocationTargetException e) {
            throw new TwiskClassLoaderException("Problème lors de l'appel de la méthode setNbClients");
        }
        try {
            simuler.invoke(sim,monde);
        } catch (IllegalAccessException e) {
            throw new TwiskClassLoaderException("Manque d'accès à la méthode simuler");
        } catch (InvocationTargetException e) {
            throw new TwiskClassLoaderException("Problème lors de l'appel de la méthode simuler");
        }
    }

    public static void main(String[] args) throws TwiskClassLoaderException {
        new ClientTwisk();
    }
}
