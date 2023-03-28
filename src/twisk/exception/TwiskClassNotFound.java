package twisk.exception;

/**
 * Exception lancée lorsque la classe donnée pour être chargée par le ClassLoaderPerso
 */
public class TwiskClassNotFound extends TwiskException{

    /**
     * Constructeur par defaut de l'exception avec un message
     * @param msg Le message
     */
    public TwiskClassNotFound(String msg){super(msg);}
}
