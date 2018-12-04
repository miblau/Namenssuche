package validators;

import exceptions.zuVieleNachnamenException;

/**
 * Stellt statische Methoden zum werfen von Exceptions zur Verfuegung.
 *
 * @author Michelle Blau
 */

public class ServerValidator {
    public static final String ANZAHL_NACHNAMEN_FALSCH = "Anzahl Nachnamen muss <= 10 sein\n";


    /**
     * Wirft in Abhaengigkeit einer uebergebenen Bedingung eine "zuVieleNachnamenException".
     *
     * @param bedingung diese Bedingung muss erfuellt sein, sonst wird die Exception geworfen
     */
    public static void pruefeAnzahlNachnamen(boolean bedingung){
        if(!bedingung){
            throw new zuVieleNachnamenException(ANZAHL_NACHNAMEN_FALSCH);
        }
    }
}
