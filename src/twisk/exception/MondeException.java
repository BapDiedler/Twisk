package twisk.exception;

/**
 * Exception lancée lors de la vérification du monde si le monde est incorrect
 */
public class MondeException extends TwiskException{

    /**
     * Constructeur de l'exception
     * @param msg Le message de l'esception
     */
    public MondeException(String msg){
            super(msg);
    }
}
