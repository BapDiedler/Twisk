package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{

    private GestionnaireEtapes lesEtapes;

    private SasEntree entree;

    private SasSortie sortie;

    public Monde(){
        this.lesEtapes = new GestionnaireEtapes();
        this.entree = new SasEntree();
        this.sortie = new SasSortie();
    }

    public void aCommeEntree(Etape... etapes){entree.ajouterSuccesseur(etapes);}

    public void aCommeSortie(Etape... etapes){
        for(Etape e: etapes){
            e.ajouterSuccesseur(sortie);
        }
    }

    public void ajouter(Etape... etapes){lesEtapes.ajouter(etapes);}

    int nbEtapes(){return lesEtapes.nbEtapes();}

    int nbGuichet(){
        return lesEtapes.nbGuichet();}

    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }
}
