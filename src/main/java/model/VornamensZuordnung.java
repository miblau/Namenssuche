package model;

import java.util.ArrayList;

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
            if(vorname != null){
                ergebnis.append(vorname + ", ");
            }
        }
        ergebnis.deleteCharAt(ergebnis.length()-2);
        return ergebnis.toString();
    }
}
