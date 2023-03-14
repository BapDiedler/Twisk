package twisk.monde;

import java.util.Iterator;

/**
 *
 * Classe qui se trouve dans le package twisk.monde et qui représente le monde
 *
 * @author Diedler et Lichtner
 */
public class Monde implements Iterable<Etape>{
    /**
     * Le gestionnaire d'étape du monde
     */
    private final GestionnaireEtapes lesEtapes;

    /**
     * Activité qui servira d'entrée pour notre monde
     */
    private final SasEntree entree;

    /**
     * Activité qui servira de sortie pour notre monde
     */
    private final SasSortie sortie;

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
     * @param etapes collection d'étapes que l'on ajoute au monde comme entrée
     */
    public void aCommeEntree(Etape... etapes){entree.ajouterSuccesseur(etapes);}

    /**
     * Définit les sorties du monde qui seront défini comme ayant comme successeur la sortie
     * @param etapes collection d'étapes que l'on ajoute au monde comme sortie
     */
    public void aCommeSortie(Etape... etapes){
        for(Etape e: etapes){
            e.ajouterSuccesseur(sortie);
        }
    }

    /**
     * Ajoute les étapes dans le gestionnaire d'étapes
     * @param etapes les étapes à ajouter dans le monde
     */
    public void ajouter(Etape... etapes){lesEtapes.ajouter(etapes);}

    /**
     * Donne le nombre d'étapes dans le monde
     * @return le nombre d'étapes dans le monde
     */
    public int nbEtapes(){return lesEtapes.nbEtapes();}

    /**
     * Donne le nombre de guichets dans le monde
     * @return le nombre de guichets dans le monde
     */
    public int nbGuichet(){
        int cpt = 0;
        for(Etape e: lesEtapes){
           if(e.estUnGuichet()){
               cpt++;
           }
        }
        return cpt;}

    /**
     * Redéfinition de iterator d'Etape
     * @return l'iterator du gestionnaire d'étape
     */
    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }

    /**
     * méthode qui permet de récupérer une étape à l'indice donné
     * @param i indice de l'étape
     * @return l'étape demandée
     */
    public Etape getEtape(int i){
        return lesEtapes.getEtape(i);
    }

    /**
     * methode qui affiche le monde
     * @return le monde sous forme de String
     */
    public String toString(){
        return lesEtapes.toString();
    }

    /**
     * méthode toC qui permet de créer le code C du monde
     * @return le toC du monde en String
     */
    public String toC(){
        return entree.toC()+"}";
    }
}
