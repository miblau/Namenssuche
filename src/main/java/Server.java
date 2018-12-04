import model.VornamensZuordnung;
import validators.ServerValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Michelle Blau
 */

public class Server implements Namenssuche {

    private static final int ANZAHL_NACHNAMEN = 10;
    private static final int ANZAHL_VORNAMEN = 10;

    HashMap<String, ArrayList<String>> namensHashMap = new HashMap<String, ArrayList<String>>();

    public Server(){
        listeInHashMapEinlesen();
    }


    /**
     *
     * @param nachnamen
     * @return
     */
    @Override
    public VornamensZuordnung[] sucheVornamen(String[] nachnamen){
        ServerValidator.pruefeAnzahlNachnamen(nachnamen.length <= ANZAHL_NACHNAMEN);

        VornamensZuordnung[] vornamensZuordnungen = new VornamensZuordnung[ANZAHL_VORNAMEN];
        for(int i = 0; i < ANZAHL_VORNAMEN; i++){
            vornamensZuordnungen[i] = new VornamensZuordnung();
        }


        for(int i = 0; i < nachnamen.length; i++){
            String[] array = new String[ANZAHL_VORNAMEN];
            ArrayList<String> passendeVornamen = namensHashMap.get(nachnamen[i]);
            vornamensZuordnungen[i].setNachname(nachnamen[i]);
            vornamensZuordnungen[i].setVornamen(passendeVornamen.toArray(array));
        }

        return vornamensZuordnungen;
    }





    private void listeInHashMapEinlesen(){
        Scanner scanner = null;
        File file = new File(Konstanten.DATEINAME_NAMENSLISTE);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        scanner.useDelimiter(Pattern.compile(";"));
        String vorname = null;
        String nachname = null;

        scanner.nextLine();
        while(scanner.hasNext()){
            nachname = scanner.next().toLowerCase();
            vorname = scanner.next().toLowerCase();

            if(namensHashMap.containsKey(nachname)){
                ArrayList<String> bestehendeVornamen = namensHashMap.get(nachname);
                bestehendeVornamen.add(vorname);
            }else{
                ArrayList<String> neueListe = new ArrayList<String>();
                neueListe.add(vorname);
                namensHashMap.put(nachname,neueListe);
            }
            scanner.nextLine();
        }

        scanner.close();
    }
}
