import model.VornamensZuordnung;
import validators.ServerValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Stellt Funktionalitaet des Servers zur Verfuegung.
 * Bei Erzeugung eines neuen Servers wird die Namensliste.csv Zeile fuer Zeile eingelesen und in einer
 * HashMap gespeichert. Jedes Server-Objekt erhaelt so seine eigene HashMap, auf der es die Vornamen finden kann.
 *
 * @author Michelle Blau
 */

public class Server implements Namenssuche {

    private static final int ANZAHL_NACHNAMEN = 10;


    private HashMap<String, ArrayList<String>> namensHashMap = new HashMap<String, ArrayList<String>>();

    /**
     * Liest bei Erzeugung eines neuen Objekts
     * die Namensliste.csv in die "namensHashMap" ein.
     */
    public Server(){
        listeInHashMapEinlesen();
    }


    /**
     * Sucht zu gegebenen Nachnamen die zugehörigen Vornamen und gibt diese in einer ArrayList zurueck.
     * Die Nachnamen werden vorher in Kleinbuchstaben konvertiert.
     *
     * @param nachnamen Arraylaenge muss <= 10 sein, ansonsten wird eine "zuVieleNachnamenException" geworfen
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


    /**
     * Liest die Inhalte der Namensliste.csv ein und speichert sie in dem Attribut "namensHashMap".
     * Dabei wird die erste Zeile der Datei uebersprungen.
     * Key=Nachname, Value=Liste mit zugehoerigen Vornamen
     *
     */
    private void listeInHashMapEinlesen(){
        Scanner scanner = null;
        File file = new File(Konstanten.DATEINAME_NAMENSLISTE);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        scanner.useDelimiter(Pattern.compile(";"));
        scanner.nextLine();
        while(scanner.hasNext()){
            String nachname = scanner.next().toLowerCase();
            String vorname = scanner.next().toLowerCase();

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


    /**
     * Hilfsmethode, die uebergebene Nachnamen in Kleinbuchstaben umwandelt und Whitespace entfernt.
     * @param nachnamen Umzuwandelnde Nachnamen
     */
    private void konvertiereInKleinbuchstaben(String[] nachnamen){
        for(int i = 0; i < nachnamen.length; i++){
            nachnamen[i] = nachnamen[i].toLowerCase().trim();
        }
    }
}
