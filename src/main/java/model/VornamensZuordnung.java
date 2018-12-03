package model;

public class VornamensZuordnung {
    private String nachname;
    private String[] vornamen;

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String[] getVornamen() {
        return vornamen;
    }

    public void setVornamen(String[] vornamen) {
        this.vornamen = vornamen;
    }

    @Override
    public String toString() {

        String zugehoerigeVornamen = "|";

        if(vornamen != null) {
            for (int i = 0; i < vornamen.length; i++) {
                if (vornamen[i] != null) {
                    zugehoerigeVornamen += vornamen[i] + "|";
                }
            }
        }
        return "VornamensZuordnung{" +
                "nachname='" + nachname + '\'' +
                ", vornamen='" + zugehoerigeVornamen + '\'' +
                '}';
    }
}
