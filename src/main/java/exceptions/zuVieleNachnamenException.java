package exceptions;

/**
 * Diese Exception soll geworfen werden, wenn der Client zu viele Nachnamen an den Server schickt.
 *
 * @author Michelle Blau
 */

public class zuVieleNachnamenException extends RuntimeException {
    public zuVieleNachnamenException(String fehlernachricht){
        super(fehlernachricht);
    }
}
