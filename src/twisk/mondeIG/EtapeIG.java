package twisk.mondeIG;

import twisk.outils.FabriqueIdentifiant;
import twisk.outils.TailleComposants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Cette classe décrit une étape graphique
 */
    public abstract class EtapeIG implements Iterable<PointDeControleIG>{

    /**
     * Le nom de l'étape
     */
    protected String nom;

    /**
     * L'identifiant de l'étape. Cet identifiant est unique et est donné par Twisk
     */
    protected String identifiant;

    /**
     * Position en pixels de l'étape sur l'abscisse
     */
    protected int posX;

    /**
     * Position en pixels de l'étape sur l'ordonnée
     */
    protected int posY;

    /**
     * La largeur de l'étape graphique
     */
    protected int largeur;

    /**
     * La hauteur de l'étape graphique
     */
    protected int hauteur;

    /**
     * Vrai si l'étape est une entrée, faux sinon
     */
    protected boolean estEntree;

    /**
     * Vrai si l'étape est une sortie, faux sinon
     */
    protected boolean estSortie;

    /**
     * Collection de points de contrôle de l'étape
     */
    protected ArrayList<PointDeControleIG> pointsControles;

    /**
     * constructeur de la classe EtapeIG
     *
     * @param nom nom de l'étape
     */
    public EtapeIG(String nom){
        defineNom(nom);
        defineEntreeSortie();
    }

    /**
     * méthode permettant de changer la taille de l'étape
     */
    protected void defineTaille(){}

    /**
     * Défini la position
     */
    protected void definePosition(){
        // Donne des valeurs aléatoires à la position de l'étape
        Random random = new Random();
        this.posX = random.nextInt(1000 - largeur);
        this.posY = random.nextInt(1000 - hauteur);
    }

    /**
     * Défini le nom
     */
    private void defineNom(String nom){
        String numEtape = FabriqueIdentifiant.getNumeroEtape();
        this.identifiant = numEtape;
        this.nom = nom + " " + numEtape;
    }

    /**
     * Défini les points de contrôles
     */
    protected void definePointDeControles(){
        this.pointsControles = new ArrayList<>();
        int milieuLargeur = posX + largeur/2;
        int milieuHauteur = posY + hauteur/2;
        // Pdc segment du haut
        pointsControles.add(new PointDeControleIG(milieuLargeur,posY,this));
        // Pdc segment de gauche
        pointsControles.add(new PointDeControleIG(posX,milieuHauteur,this));
        // Pdc segment du bas (on ajoute en plus en X 2 fois le padding qui est de 10)
        pointsControles.add(new PointDeControleIG(milieuLargeur,posY+hauteur,this));
        // Pdc segment de droite (on ajoute en plus en Y 2 fois le padding qui est de 10)
        pointsControles.add(new PointDeControleIG(posX+largeur,milieuHauteur,this));
    }

    /**
     * Défini les variables booléennes si l'étape est une entrée et/ou une sortie
     */
    private void defineEntreeSortie(){
        this.estEntree = false;
        this.estSortie = false;
    }

    /**
     * Met à jour la coordonnée des points de contrôle de l'étape lorsqu'elle est déplacée
     */
    public void miseAJourPointDeControle(){
        int milieuLargeur = posX + largeur/2;
        int milieuHauteur = posY + hauteur/2;
        PointDeControleIG p1 = pointsControles.get(0);
        p1.setPosition(milieuLargeur,posY);
        PointDeControleIG p2 = pointsControles.get(1);
        p2.setPosition(posX,milieuHauteur);
        PointDeControleIG p3 = pointsControles.get(2);
        p3.setPosition(milieuLargeur, posY+hauteur);
        PointDeControleIG p4 = pointsControles.get(3);
        p4.setPosition(posX+largeur,milieuHauteur);
    }

    /**
     * Donne l'identifiant de l'étape graphique
     * @return l'identifiant
     */
    public String getIdentifiant(){
        return identifiant;
    }

    /**
     * Donne le nom de l'étape graphique
     * @return le nom
     */
    public String getNom(){
        return nom;
    }

    /**
     * Change le nom de l'étape
     * @param nom Le nouveau nom
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * Donne la position de l'abscisse
     * @return la position de l'abscisse
     */
    public int getPosX(){
        return posX;
    }

    /**
     * Donne la position de l'ordonnée
     * @return la position de l'ordonnée
     */
    public int getPosY(){
        return posY;
    }

    /**
     * Donne la largeur
     * @return la largeur
     */
    public int getLargeur(){
        return largeur;
    }

    /**
     * Donne la hauteur
     * @return la hauteur
     */
    public int getHauteur(){
        return hauteur;
    }

    /**
     * Donne la collection de point de contrôle
     * @return
     */
    public ArrayList<PointDeControleIG> getPointsControles(){
        return pointsControles;
    }

    @Override
    public Iterator<PointDeControleIG> iterator(){
        return pointsControles.iterator();
    }

    /**
     * Change la position de l'étape sur l'axe des abscisses
     * @param posX la nouvelle position de l'étape sur l'axe des abscisses
     */
    public void setPosX(int posX){
        this.posX = posX;
    }

    /**
     * change la position de l'étape sur l'axe des ordonnées
     * @param posY la nouvelle position de l'étape sur l'axe des ordonnées
     */
    public void setPosY(int posY){
        this.posY = posY;
    }

    /**
     * Inverse la valeur booléenne estEntree
     */
    public void setEstEntree(){
        this.estEntree = !estEntree;
    }

    /**
     * Inverse la valeur booléenne estSortie
     */
    public void setEstSortie(){
        this.estSortie = !estSortie;
    }

    /**
     * Donne la valeur booléenne estEntrée
     * @return vrai si l'étape est une entrée, faux sinon
     */
    public boolean getEstEntree(){
        return estEntree;
    }

    /**
     * Donne la valeur booléenne estSortie
     * @return vrai si l'étape est une sortie, faux sinon
     */
    public boolean getEstSortie(){
        return estSortie;
    }

    /**
     * Vérifie si l'étape est une entrée et une sortie
     * @return vrai si l'étape est une entrée et une sortie, faux sinon
     */
    public boolean estEntreeEtEstSortie(){
        return estEntree && estSortie;
    }

    /**
     * Change l'écart. La méthode ne fait rien ici et est réecrite dans ActiviteIG
     * @param ecart le nouvel écart
     */
    public void setEcart(int ecart){}

    /**
     * Change le délai. La méthode ne fait rien ici et est réecrite dans ActiviteIG
     * @param delai le nouveau délai
     */
    public void setDelai(int delai){}

    /**
     * Donne l'écart de l'étape. Renvoie -1 si ce n'est pas une ActiviteIG et est réecrite dans cette dernière.
     * @return L'écart de l'étape si c'est une activité. -1 sinon
     */
    public int getEcart(){return -1;}

    /**
     * Donne le délai de l'étape. Renvoie -1 si ce n'est pas une ActiviteIG et est réecrite dans cette dernière.
     * @return Le délai de l'étape si c'est une activité. -1 sinon
     */
    public int getDelai(){return -1;}

    /**
     * méthode qui permet de savoir si l'étape est un guichet
     * @return true si l'étape est un guichet false sinon
     */
    public boolean estUnGuichet(){
        return true;
    }

    /**
     * méthode qui permet de savoir si l'étape est une activité
     * @return true si l'étape est une activité false sinon
     */
    public boolean estUneActivite(){
        return false;
    }
}
