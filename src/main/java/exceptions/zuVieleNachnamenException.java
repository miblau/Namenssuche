package exceptions;

/**
 *
 * @author Michelle Blau
 */

public class zuVieleNachnamenException extends RuntimeException {
    public zuVieleNachnamenException(String fehlernachricht){
        super(fehlernachricht);
    }
}
