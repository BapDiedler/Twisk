package twisk.monde;

/**
 *
 * Cette classe se trouve dans le package twisk.monde et permet de savoir si une étape
 *                      est une entrée ou non. Si l'étape est une entrée alors SasEntrée aura en
 *                      successeur cette étape.
 * @author Diedler et Litchner
 */

class SasEntree extends Activite {

    /**
     * constructeur du SasEntrée
     */
    public SasEntree(){
        super("SasEntree");
    }

    /**
     * Méthode qui renvoie le String correspondant aux includes nécessaires à inclure dans le code C
     * généré
     * @return le String contenant les includes
     */
    private String includes(){
        return "#include <stdlib.h>\n#include <stdio.h>\n#include \"def.h\"\n";
    }

    @Override
    public String toC(){
        String suite = super.toC();
        String profil = "\n//méthode simulation pour simuler un monde\n" +
                "void simulation(int ids){\n" +
                "\n\t//on commence par entrer dans le sasEntrée\n" +
                "\tentrer(" + passNom() + getNumero() +");\n";
        return includes() + constantes() + profil + suite;
    }
}
