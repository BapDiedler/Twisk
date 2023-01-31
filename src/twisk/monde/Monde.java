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

    public void aCommeEntree(Etape... etapes){}

    public void aCommeSortie(Etape... etapes){}

    public void ajouter(Etape... etapes){}

    int nbEtapes(){return 0;}

    int nbGuichet(){return 0;}

    @Override
    public Iterator<Etape> iterator() {
        return null;
    }
}
