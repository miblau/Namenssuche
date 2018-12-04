package model;

import java.util.ArrayList;

/**
 * Dient als Container-Klasse.
 * Ordnet einem Nachnamen ein oder mehrere Vornamen zu.
 *
 * @author Michelle Blau
 */

public class VornamensZuordnung {
    private String nachname;
    private ArrayList<String> vornamen;

    public VornamensZuordnung(String nachname, ArrayList<String> vornamen) {
        this.nachname = nachname;
        this.vornamen = vornamen;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public ArrayList<String> getVornamen() {
        return vornamen;
    }

    public void setVornamen(ArrayList<String> vornamen) {
        this.vornamen = vornamen;
    }

    @Override
    public String toString() {
        StringBuffer ergebnis = new StringBuffer("Nachname: " + nachname + "\t| Vornamen: ");

        for(String vorname : vornamen){
            ergebnis.append(vorname + ", ");
        }
        ergebnis.deleteCharAt(ergebnis.length()-2);
        return ergebnis.toString();
    }
}
