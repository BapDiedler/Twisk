package twisk.exception;

/**
 * Exception lancée lorsque la classe donnée pour être chargée par le ClassLoaderPerso
 */
public class TwiskClassLoaderException extends TwiskException{

    /**
     * Constructeur de l'exception avec un message
     * @param msg Le message
     */
    public TwiskClassLoaderException(String msg){super(msg);}
}
