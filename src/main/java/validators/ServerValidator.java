package validators;

import exceptions.zuVieleNachnamenException;

/**
 *
 * @author Michelle Blau
 */

public class ServerValidator {
    public static final String ANZAHL_NACHNAMEN_FALSCH = "Anzahl Nachnamen muss <= 10 sein\n";


    public static void pruefeAnzahlNachnamen(boolean bedingung){
        if(!bedingung){
            throw new zuVieleNachnamenException(ANZAHL_NACHNAMEN_FALSCH);
        }
    }
}
