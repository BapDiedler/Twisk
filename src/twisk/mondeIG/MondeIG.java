package twisk.mondeIG;

import twisk.CorrespondanceEtapes;
import twisk.exception.MondeException;
import twisk.exception.TwiskArcIncorrect;
import twisk.exception.TwiskIncorrectInput;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

import java.util.*;

/**
 * Cette classe est la classe principale du modèle
 */
public class MondeIG extends SujetObserve implements Iterable<EtapeIG>{
    /**
     * Collection d'étapes graphiques. La clé est l'identifiant de l'étape
     */
    private final HashMap<String,EtapeIG> etapes;

    /**
     * Collection des arcs du monde
     */
    private final ArrayList<ArcIG> arcs;

    /**
     * Collection des arcs sélectionnés
     */
    private ArrayList<ArcIG> arcsSelectionnes;

    /**
     * Premier point de contrôle du monde. Il est initialisé à Null.
     * Si un point est cliqué et que l'attribut vaut Null, alors l'attribut devient le point cliqué.
     * Sinon appelle la fonction ajouter
     */
    private PointDeControleIG premPoint;

    /**
     * Collection des étapes sélectionnées
     */
    private HashMap<String,EtapeIG> etapesSelectionne;

    /**
     * Le nombre de clients
     */
    private int nbClients;

    /**
     * attribut qui permet de faire les relations entre les éléments graphiques et le monde
     */
    private CorrespondanceEtapes correspondanceEtapes;


    /**
     * Lors de la construction, le monde contient une activité
     */
    public MondeIG(){
        super();
        nbClients = 10;
        arcs = new ArrayList<>(10);
        etapes = new HashMap<>();
        this.premPoint = null;
        ActiviteIG activite = new ActiviteIG();
        etapes.put(activite.getIdentifiant(), activite);
        this.etapesSelectionne = new HashMap<>();
        this.arcsSelectionnes = new ArrayList<>(10);
    }

    /**
     * méthode qui permet la création d'un monde en fonction de l'application graphique
     *
     * @return le monde créer
     */
    private Monde creerMonde(){
        Monde monde = new Monde();
        correspondanceEtapes = new CorrespondanceEtapes();
        creationEtapesMonde(monde);
        liaisonEtapes(monde);
        return monde;
    }

    /**
     * méthode qui crée les liens entre les étapes du monde
     *
     * @param monde monde où se trouve les étapes
     */
    private void liaisonEtapes(Monde monde){
        EtapeIG departIG, arriveeIG;
        Etape depart, arrivee;
        for(ArcIG arc : arcs){
            departIG = arc.getPremPoint().getEtape();
            arriveeIG = arc.getDeuxPoint().getEtape();
            depart = correspondanceEtapes.get(departIG);
            arrivee = correspondanceEtapes.get(arriveeIG);
            depart.ajouterSuccesseur(arrivee);
        }
    }

    /**
     * méthode qui permet de créer les étapes se trouvant dans le monde
     *
     * @param monde monde dans lequel on ajoute les étapes
     */
    private void creationEtapesMonde(Monde monde){
        Etape etape;
        for(EtapeIG etapeIG: etapes.values()){
            if(etapeIG.estUneActivite()){
                etape = new Activite();
                etape.setNom(etapeIG.getNom());
            }else{
                etape = new Guichet(etapeIG.getNom(),etapeIG.getNbJetons());
            }
            if(etapeIG.getEstSortie()){
                monde.aCommeSortie(etape);
            }
            if(etapeIG.getEstEntree()){
                monde.aCommeEntree(etape);
            }
            monde.ajouter(etape);
            correspondanceEtapes.ajouter(etapeIG,etape);
        }
    }

    /**
     * méthode qui permet de simuler le mondeIG
     */
    public void simuler() throws MondeException {
        ajouterSuccesseurs();
        verifierMondeIG();
        Monde monde = creerMonde();
        Simulation simulation = new Simulation();
        int nbClients = getNbClients();
        simulation.setNbClients(nbClients);
        simulation.simuler(monde);
    }


    /**
     * méthode pour savoir si le monde est valide
     * utilisation du parcours en profondeur sur un arbre non connexe pour vérifier les chemins
     *
     * @throws MondeException déclenchée si le monde n'est pas valide
     */
    private void verifierMondeIG() throws MondeException {
        EtapeIG etapeIG;
        ArrayList<EtapeIG> etapesGrises = new ArrayList<>(10);
        ArrayList<EtapeIG> etapesNoires = new ArrayList<>(10);
        for(int i=0; i<etapes.size(); i++) {
            etapeIG = getEtape(i);
            if (!etapeIG.possedeUneSortie()) {
                throw new MondeException("une étape n'a pas de chemin vers une sortie");
            }
            if (etapeIG.getEstEntree()) {
                parcoursProfondeur(etapesGrises,etapesNoires,etapeIG);
            }
        }
        if(etapesNoires.size() != etapes.values().size()){
            throw new MondeException("une étape n'a pas été relier par une entrée");
        }
    }

    /**
     * simulation d'un parcours en profondeurs
     * @param etapesGrises étapes du parcours qui sont grisés
     * @param etapesNoires étapes du parcours qui sont noirs
     * @param etapeIG étape de départ du parcours
     */
    private void parcoursProfondeur(ArrayList<EtapeIG> etapesGrises, ArrayList<EtapeIG> etapesNoires,EtapeIG etapeIG){
        Iterator<EtapeIG> iter;
        EtapeIG successeur;
        Stack<EtapeIG> pile = new Stack<>();
        etapesGrises.add(etapeIG);
        pile.add(etapeIG);
        while (!pile.isEmpty()) {
            etapeIG = pile.pop();
            iter = etapeIG.iteratorSuccesseurs();
            while (iter.hasNext()) {
                successeur = iter.next();
                if (!etapesGrises.contains(successeur)) {
                    if (!etapesNoires.contains(successeur)) {
                        etapesGrises.add(successeur);
                        pile.add(successeur);
                    }
                }
            }
            etapesGrises.remove(etapeIG);
            etapesNoires.add(etapeIG);
        }
    }


    /**
     * méthode qui permet d'ajouter les successeurs aux étapes du monde
     */
    public void ajouterSuccesseurs(){
        EtapeIG depart, arrivee;
        for(ArcIG arc: arcs){
            depart = arc.getPremPoint().getEtape();
            arrivee = arc.getDeuxPoint().getEtape();
            depart.setSuccesseurs(arrivee);
        }
    }

    /**
     * Ajoute une activité dans le monde
     * @param type le type à ajouter
     */
    public void ajouter(String type){
        if(type.equals("Activité")){
            //System.out.println("Une activité a été ajoutée !");
            ActiviteIG activite = new ActiviteIG();
            etapes.put(activite.getIdentifiant(), activite);
            notifierObs();
        } else if (type.equals("Guichet")) {
            //System.out.println("Une guichet a été ajoutée !");
            GuichetIG guichetIG = new GuichetIG();
            etapes.put(guichetIG.getIdentifiant(), guichetIG);
            notifierObs();
        } else{
            System.err.println("Erreur, type non reconnu.\n");
        }
    }

    /**
     * Crée un arc à partir des deux points de contrôles passés en paramètre et l'ajoute à l'Arraylist d'arc
     * @param pt1 Le premier point de contrôle
     * @param pt2 Le deuxième point de contrôle
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2){
        ArcIG arc = new ArcIG(pt1,pt2);
        arcs.add(arc);
        // remet le premier point à null
        notifierObs();
    }

    /**
     * Donne l'arraylist d'arcs
     * @return La collection d'arcs
     */
    public ArrayList<ArcIG> getArcs(){
        return arcs;
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return etapes.values().iterator();
    }

    /**
     * Itére sur les arcs
     * @return l'itérateur d'arc
     */
    public Iterator<ArcIG> iteratorArc() {
        return arcs.iterator();
    }

    /**
     * Méthode appelée par l'écouteur du point de contrôle lorsqu'un point est cliqué.
     * @param point Le point cliqué
     */
    public void pointClique(PointDeControleIG point) throws TwiskArcIncorrect {
        if(premPoint==null){
            this.premPoint = point;
        }
        else{
            if(checkContraintesArc(point)){
                ajouter(premPoint,point);
                premPoint = null;
            }
            else {
                premPoint = null;
                throw new TwiskArcIncorrect();
            }
        }
    }

    /**
     * Vérifie si l'arc que l'on s'apprête de créer respecte les contraintes.
     * L'arc doit être constitué de deux points de contrôles distincts et on ne peut pas créer l'arc s'il existe
     * déjà un arc allant de l'étape source à l'étape destination.
     * @param deuxiemePoint le deuxième point de contrôle de l'arc que l'on s'apprête de créer
     * @return Vrai si les contraintes sont respectées, faux sinon
     */
    private boolean checkContraintesArc(PointDeControleIG deuxiemePoint){
        boolean correct = true;
        if(premPoint.equals(deuxiemePoint)) {
            correct = false;
        }
        else{
            EtapeIG srcEtape = premPoint.getEtape();
            EtapeIG destEtape = deuxiemePoint.getEtape();
            Iterator<ArcIG> iterator = iteratorArc();
            while(iterator.hasNext()){
                ArcIG arc = iterator.next();
                PointDeControleIG p1 = arc.getPremPoint();
                PointDeControleIG p2 = arc.getDeuxPoint();
                for(PointDeControleIG premPoint: srcEtape){
                    if(p1.equals(premPoint)){
                        for(PointDeControleIG deuxPoint: destEtape){
                            if(p2.equals(deuxPoint)){
                                correct = false;
                            }
                        }
                    }
                }
            }

        }
        return correct;
    }

    /**
     * Donne l'attribut premPoint
     * @return Le point de contrôle PremPoint
     */
    public PointDeControleIG getPremPoint(){return this.premPoint;}

    /**
     * Ajoute l'étape dans la hashmap si elle n'y est pas et l'enlève sinon
     * @param etape l'étape
     */
    public void ajouterEtapeSelectionne(EtapeIG etape){
        String identifiant = etape.getIdentifiant();
        if(etapeSelectionne(identifiant)){
            etapesSelectionne.remove(identifiant);
        }
        else{
            etapesSelectionne.put(identifiant,etape);
        }
        notifierObs();
    }

    /**
     * Ajoute l'arc dans l'arraylist si elle n'y est pas et l'enlève sinon
     * @param arc L'arc
     */
    public void ajouterArcSelectionne(ArcIG arc){
        if(arcSelectionne(arc)){
            arcsSelectionnes.remove(arc);
        }
        else{
            arcsSelectionnes.add(arc);
        }
        notifierObs();
    }

    /**
     * Renvoie vrai si l'étape est dans la hashmap, faux sinon
     * @param identifiant l'identifiant de l'étape
     * @return Vrai si l'étape est dans la hashmap, faux sinon
     */
    public boolean etapeSelectionne(String identifiant){
        return etapesSelectionne.containsKey(identifiant);
    }

    /**
     * Renvoie vrai si l'arc est dans l'arraylist, faux sinon
     * @param arc
     * @return Vrai si l'arc est dans l'arraylist, faux sinon
     */
    public boolean arcSelectionne(ArcIG arc){
        return arcsSelectionnes.contains(arc);
    }

    /**
     * Supprime les arcs correspondants aux étapes supprimées
     * @param etape L'étape dont on vérifie si un arc lui est associé
     */
    private void supprimeArcCorrespondant(EtapeIG etape){
        Iterator<ArcIG> iterator = iteratorArc();
        ArrayList<ArcIG> arcsASupprimer = new ArrayList<>(10);
        while(iterator.hasNext()){
            ArcIG arc = iterator.next();
            PointDeControleIG p1 = arc.getPremPoint();
            EtapeIG etape1 = p1.getEtape();
            if(etape.equals(etape1)){
                arcsASupprimer.add(arc);
            }
            else{
                PointDeControleIG p2 = arc.getDeuxPoint();
                EtapeIG etape2 = p2.getEtape();
                if(etape.equals(etape2)){
                    arcsASupprimer.add(arc);
                }
            }
        }
        for(ArcIG arc: arcsASupprimer){
            arcs.remove(arc);
        }
    }

    /**
     * Supprime les éléments sélectionnés
     */
    public void supprimeSelection(){
        supprimerEtapesSelectionnees();
        supprimerArcSelectionnes();
        notifierObs();
    }

    /**
     * Supprime les étapes sélectionnées
     */
    private void supprimerEtapesSelectionnees(){
        ArrayList<String> identifiants = new ArrayList<>(10);
        for(String id: etapesSelectionne.keySet()){
            supprimeArcCorrespondant(etapes.get(id));
            identifiants.add(id);
            etapes.remove(id);
        }
        for(String id: identifiants){
            etapesSelectionne.remove(id);
        }
    }

    /**
     * Supprime les arcs sélectionnés
     */
    private void supprimerArcSelectionnes(){
        for(ArcIG arc: arcsSelectionnes){
            arcs.remove(arc);
        }
    }

    /**
     * Donne le nombre d'étapes qui sont sélectionnées
     * @return Le nombre d'étapes sélectionnées
     */
    public int getNbEtapesSelectionnees(){
        return etapesSelectionne.size();
    }

    /**
     * Renomme l'étape
     * @param nom Le nouveau nom de l'étape
     */
    public void renommerEtape(String nom){
        Iterator<EtapeIG> iterator = etapesSelectionne.values().iterator();
        EtapeIG etape = iterator.next();
        etape.setNom(nom);
        etapesSelectionne.remove(etape.getIdentifiant());
        notifierObs();
    }

    /**
     * Déplace l'étape
     * @param id L'identifiant de l'étape
     * @param posX La nouvelle position en X de l'étape
     * @param posY La nouvelle position en Y de l'étape
     */
    public void deplacerEtape(String id, int posX, int posY){
        EtapeIG etape = etapes.get(id);
        etape.setPosX(posX);
        etape.setPosY(posY);
        etape.miseAJourPointDeControle();
    }

    /**
     * Désélectionne tous les éléments sélectionnés
     */
    public void deselectionner(){
        etapesSelectionne = new HashMap<>();
        arcsSelectionnes = new ArrayList<>(10);
        notifierObs();
    }

    /**
     * Passe les étapes sélectionnés en entrée
     */
    public void setEntrees(){
        for(EtapeIG etape: etapesSelectionne.values()){
            etape.setEstEntree();
        }
        etapesSelectionne = new HashMap<>();
        notifierObs();
    }

    /**
     * méthode qui place une étape en mode sortie
     */
    public void setSortiees(){
        for(EtapeIG etape: etapesSelectionne.values()){
            etape.setEstSortie();
        }
        etapesSelectionne = new HashMap<>();
        notifierObs();
    }

    /**
     * Change le délai de l'étape
     * @param input L'input de l'utilisateur. Il s'agit normalement d'un nombre qui sera le nouveau délai de l'étape
     * @throws TwiskIncorrectInput Exception levée si l'input de l'utilisateur ne peut pas être parsée en int ou est
     * négative
     */
    public void setDelaiEtape(String input) throws TwiskIncorrectInput {
        try {
            Iterator<EtapeIG> iterator = etapesSelectionne.values().iterator();
            EtapeIG etape = iterator.next();
            int delai = Integer.parseInt(input);
            if(delaiInvalide(etape,delai)){
                etapesSelectionne.remove(etape.getIdentifiant());
                notifierObs();
                throw new TwiskIncorrectInput();
            }
            etape.setDelai(delai);
            etapesSelectionne.remove(etape.getIdentifiant());
            notifierObs();
        }catch (NumberFormatException e){
            deselectionneEtape();
            throw new TwiskIncorrectInput();
        }
    }

    /**
     * Change le délai de l'étape
     * @param input L'input de l'utilisateur. Il s'agit normalement d'un nombre qui sera le nouveau délai de l'étape
     * @throws TwiskIncorrectInput Exception levée si l'input de l'utilisateur ne peut pas être parsée en int ou est
     * négative
     */
    public void setNbJetonsEtape(String input) throws TwiskIncorrectInput {
        try {
            Iterator<EtapeIG> iterator = etapesSelectionne.values().iterator();
            EtapeIG etape = iterator.next();
            int nbJetons = Integer.parseInt(input);
            if(nbJetonsInvalide(nbJetons)){
                etapesSelectionne.remove(etape.getIdentifiant());
                notifierObs();
                throw new TwiskIncorrectInput();
            }
            etape.setNbJetons(nbJetons);
            etapesSelectionne.remove(etape.getIdentifiant());
            notifierObs();
        }catch (NumberFormatException e){
            deselectionneEtape();
            throw new TwiskIncorrectInput();
        }
    }

    /**
     * Vérifie que le nombre de jetons saisi par l'utilisateur est une valeur valide
     * @param nbJetons La valeur saisie par l'utilisateur
     * @return vrai si la valeur est valide, faux sinon
     */
    private boolean nbJetonsInvalide(int nbJetons){
        return nbJetons <= 0;
    }

    /**
     * test sur la validité du délai rentré
     * @param etape étape qui se voit changer de délai
     * @param delai nouveau délai
     * @return true si le délai est valide false sinon
     */
    private boolean delaiInvalide(EtapeIG etape, int delai){
        return delai < 1 || delai <= etape.getEcart() || delai >= 100;
    }

    /**
     * test sur la validité du délai rentré
     * @param etape étape qui se voit changer de délai
     * @param ecart nouvel ecart
     * @return true si l'ecart est valide false sinon
     */
    private boolean ecartInvalide(EtapeIG etape, int ecart){
        return ecart < 1 || ecart >= etape.getDelai();
    }

    /**
     * méthode qui désélectionne toutes les étapes
     */
    private void deselectionneEtape(){
        EtapeIG etape;
        Iterator<EtapeIG> iterator = etapesSelectionne.values().iterator();
        while(iterator.hasNext()) {
            etape = iterator.next();
            etapesSelectionne.remove(etape.getIdentifiant());
        }
        notifierObs();
    }

    /**
     * Change l'écart de temps de l'activité
     * @param input L'input de l'utilisateur. Il s'agit normalement du nouvel écart temps
     * @throws TwiskIncorrectInput Si l'input de l'utilisateur n'est pas correct
     */
    public void setEcartEtape(String input) throws TwiskIncorrectInput {
        try {
            int ecart = Integer.parseInt(input);
            Iterator<EtapeIG> iterator = etapesSelectionne.values().iterator();
            EtapeIG etape = iterator.next();
            if (ecartInvalide(etape,ecart)) {
                etapesSelectionne.remove(etape.getIdentifiant());
                notifierObs();
                throw new TwiskIncorrectInput();
            }
            etape.setEcart(ecart);
            etapesSelectionne.remove(etape.getIdentifiant());
            notifierObs();
        }
        catch (NumberFormatException e){
            deselectionneEtape();
            throw new TwiskIncorrectInput();
        }
    }

    /**
     * Renvoie la hashmap contenant les étapes sélectionnées. Utile pour les tests
     * @return La hashmap contenant les étapes sélectionnées
     */
    public HashMap<String,EtapeIG> getEtapesSelectionnees(){return etapesSelectionne;}

    /**
     * Renvoie la hashmap contenant les étapes du monde. Utile pour les tests
     * @return La hashmap contenant les étpaes sélectionnées
     */
    public HashMap<String,EtapeIG> getEtapes(){return etapes;}

    public EtapeIG getEtape(int key){
        return etapes.get(String.valueOf(key));
    }

    /**
     * Renvoie vrai si un guichet est sélectionné dans le monde
     * @return vrai si un guichet est sléectionné, faux sinon
     */
    public boolean unGuichetEstSelectionne(){
        boolean contientUnGuichet = false;
        for (EtapeIG etape : etapesSelectionne.values()) {
            if (etape.estUnGuichet()) {
                contientUnGuichet = true;
            }
        }
        return contientUnGuichet;
    }

    /**
     * Donne le nombre de clients dans le monde
     * @return Le nombre de clients
     */
    public int getNbClients(){
        return nbClients;
    }

    /**
     * Change le nombre de clients dans le monde
     * @param nbClients Le nouveau nombre de clients dans le monde
     */
    public void setNbClients(String nbClients) throws TwiskIncorrectInput {
        try {
            int nombre = Integer.parseInt(nbClients);
            if(nombre < 1 || nombre > 50){
                throw new TwiskIncorrectInput();
            }
            else{
                this.nbClients = nombre;
            }
        }
        catch (NumberFormatException e){
            throw new TwiskIncorrectInput();
        }
    }
}
