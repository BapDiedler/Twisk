package twisk;

import twisk.exception.TwiskClassLoaderException;
import twisk.exception.TwiskException;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * cette classe se trouve dans le package twisk et représente la partie client du projet
 *
 *  @author Dielder et Litchner
 */

public class ClientTwisk {

    public ClientTwisk() throws TwiskClassLoaderException{
        ClassLoader parent = this.getClass().getClassLoader();
        ClassLoaderPerso classLoader = new ClassLoaderPerso(parent);
        Class<?> simulationClass;
        try {
            simulationClass = classLoader.loadClass("twisk.simulation.Simulation");
        } catch (ClassNotFoundException e) {
            throw new TwiskClassLoaderException("La classe qui doit être chargée par le ClassLoaderPerso n'existe pas.");
        }
        Constructor cons;
        try {
            cons = simulationClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new TwiskClassLoaderException("Le constructeur par défaut n'a pas été trouvé");
        }
        try {
            Object sim = cons.newInstance();
        } catch (InstantiationException e) {
            throw new TwiskClassLoaderException("Simulation n'a pas pu être instanciée");
        } catch (IllegalAccessException e) {
            throw new TwiskClassLoaderException("L'utilisateur n'a pas les droits sur le fichier Simulation");
        } catch (InvocationTargetException e) {
            throw new TwiskClassLoaderException("Problème lors de l'instanciation de l'objet Simulation");
        }
        gerePremierMonde();
    }

    /**
     * Cette méthode crée et simule le premier monde
     */
    private void gerePremierMonde(){
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

    public static void main(String[] args) throws TwiskClassLoaderException {
        new ClientTwisk();
    }
}
