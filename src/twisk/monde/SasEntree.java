package twisk.monde;

/**
 * @author Diedler et Litchner
 *
 * Cette classe se trouve dans le package twisk.monde et permet de savoir si une étape
 *                      est une entrée ou non. Si l'étape est une entrée alors SasEntrée aura en
 *                      successeur cette étape.
 */

class SasEntree extends Activite {

    /**
     * constructeur du SasEntrée
     */
    public SasEntree(){
        super("SasEntree");
    }

    @Override
    public String toC(){
        Etape successeur = getSuccesseur();
        String profil = "//méthode simulation pour simuler un monde\n" +
                "void simulation(int ids){\n" +
                "//on commence par entrer dans le sasEntrée\n" +
                "entrer(sasEntree);\n";
        return profil + delai() + transfert() + successeur.toC();
    }
}
