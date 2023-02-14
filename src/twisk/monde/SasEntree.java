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
        String profil = """
                //méthode simulation pour simuler un monde
                void simulation(int ids){
                \t//on commence par entrée dans le sasEntrée
                \tentrer(sasEntree);
                """;
        return profil + delai() + transfert();
    }
}
