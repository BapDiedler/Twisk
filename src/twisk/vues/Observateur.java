package twisk.vues;

/**
 * Cette classe regroupe tous les observateurs et leur donne la méthode réagir() pour mettre à jour leur composant
 */
public interface Observateur {

    /**
     * Met à jour les composants de la vue
     */
    public void reagir();
}
