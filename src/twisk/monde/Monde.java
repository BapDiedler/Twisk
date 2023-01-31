package twisk.monde;

import java.util.Iterator;

/**
 * @author Diedler et Lichtner
 *
 * Classe qui représente le monde
 */
public class Monde implements Iterable<Etape>{
    /**
     * Le gestionnaire d'étape du monde
     */
    private GestionnaireEtapes lesEtapes;

    /**
     * Activité qui servira d'entrée pour notre monde
     */
    private SasEntree entree;

    /**
     * Activité qui servira de sortie pour notre monde
     */
    private SasSortie sortie;

    /**
     * Constructeur du monde
     */
    public Monde(){
        this.lesEtapes = new GestionnaireEtapes();
        this.entree = new SasEntree();
        this.sortie = new SasSortie();
        lesEtapes.ajouter(entree,sortie);
    }

    /**
     * Définit les entrées du monde qui seront définie comme successeur de l'entrée
     * @param etapes
     */
    public void aCommeEntree(Etape... etapes){entree.ajouterSuccesseur(etapes);}

    /**
     * Définit les sorties du monde qui seront définit comme ayant comme successeur la sortie
     * @param etapes
     */
    public void aCommeSortie(Etape... etapes){
        for(Etape e: etapes){
            e.ajouterSuccesseur(sortie);
        }
    }

    /**
     * Ajoute les étapes dans le gestionnaire d'étapes
     * @param etapes les étapes à ajouter
     */
    public void ajouter(Etape... etapes){lesEtapes.ajouter(etapes);}

    /**
     * Donne le nombre d'étapes dans le monde
     * @return le nombre d'étape dans le monde
     */
    int nbEtapes(){return lesEtapes.nbEtapes();}

    /**
     * Donne le nombre de guichet dans le monde
     * @return le nombre de guichet dans le monde
     */
    int nbGuichet(){
        int cpt = 0;
        for(Etape e: lesEtapes){
           if(e.estUnGuichet()){
               cpt++;
           }
        }
        return cpt;}

    /**
     * Redéfinition de l'iterator d'Etape
     * @return l'itérator du gestionnaire d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }

    /**
     * methode qui affiche le monde
     * @return le monde
     */
    public String toString(){
        return lesEtapes.toString();
    }
}
