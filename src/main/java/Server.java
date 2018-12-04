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
    private static final int ANZAHL_VORNAMENSZUORDNUNGEN = 50;

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
    public ArrayList<VornamensZuordnung> sucheVornamen(String[] nachnamen){
        ServerValidator.pruefeAnzahlNachnamen(nachnamen.length <= ANZAHL_NACHNAMEN);
        konvertiereInKleinbuchstaben(nachnamen);

        ArrayList<VornamensZuordnung> vornamensZuordnungen = new ArrayList<VornamensZuordnung>();

        for(int i = 0; i < nachnamen.length; i++){
            ArrayList<String> passendeVornamen = namensHashMap.get(nachnamen[i]);
            VornamensZuordnung einzelZuordnung = new VornamensZuordnung(nachnamen[i], passendeVornamen);

            vornamensZuordnungen.add(einzelZuordnung);
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


    private void konvertiereInKleinbuchstaben(String[] nachnamen){
        for(int i = 0; i < nachnamen.length; i++){
            nachnamen[i] = nachnamen[i].toLowerCase().trim();
        }
    }
}
