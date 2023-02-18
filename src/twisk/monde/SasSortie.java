package twisk.monde;

/**
 * @author Diedler et Litchner
 *
 * Cette classe se trouve dans le package twisk.monde et permet de savoir si une étape
 *                      est une sortie ou non. Si l'étape est une sortie alors SasSortie sera successeur
 *                      de cette étape.
 */

class SasSortie extends Activite{

    /**
     * constructeur du SasSortie
     */
    public SasSortie(){
        super("SasSortie");
    }

    @Override
    public String toC(){
        return "}\n";
    }

    @Override
    public String constantes() {
        return "#define " + getNom() + " " + getNumero() + "\n";
    }
}
