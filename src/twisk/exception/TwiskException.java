package twisk.exception;

/**
 * Exception du projet Twisk
 */
public abstract class TwiskException extends Exception{

    /**
     * Constructeur par défaut de l'exception
     */
    public TwiskException(){
        super();
    }

    /**
     * Constructeur de l'exception avec un message
     * @param msg Le message lié à l'exception
     */
    public TwiskException(String msg){super(msg);}
}
