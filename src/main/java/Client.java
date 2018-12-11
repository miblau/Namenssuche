import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stellt den Client zur Verfuegung.
 *
 * Es wird eine Verbindung zu einem Namenssuche-Programm auf einem
 * remote-host hergestellt.
 *
 * Die Eingabe des Nutzers wird in einem Dialog eingelesen.
 * Das Ergebnis wird formatiert und ausgegeben.
 *
 * @author Johannes Gerwert
 * @version 11.12.2018
 */
public class Client {

    private Scanner input = new Scanner(System.in);

    /**
     * Standard-Konstruktor
     */
    private Client(){

    }

    /**
     * Start-Methode
     *
     * Der Dialog wird gestartet und durchlaufen.
     *
     * @param stub Die Schnittstelle zum Server wird entgegengenommen.
     */
    public void start(Namenssuche stub){
        Boolean ende = false;
        String[] nachnamenArray;

        while(!ende){
            try{
                nachnamenArray = nachnamenEinlesen();
                vornamenAusgeben(nachnamenArray, stub);
                ende = ueberpruefeEnde();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Bis zu 10 Nachnamen werden von der Standard-Eingabe eingelesen.
     *
     * @return Die Nachnamen werden in einem Array weitergegeben.
     */
    public String[] nachnamenEinlesen(){
        String nachnamen;
        String[] nachnamenArray;

        System.out.println("\nBitte bis zu 10 Nachnamen eingeben.");
        System.out.println("Die Nachnamen werden durch Leerzeichen abgetrennt.");
        System.out.print("-->");

        nachnamen = input.nextLine();
        nachnamenArray = nachnamen.split(" ");

        return nachnamenArray;
    }

    /**
     * Zu den uebergebenen Nachnamen werden auf dem Server die passenden Vornamen gesucht.
     * Die Vornamen werden formatiert auf der Standardausgabe ausgegeben.
     *
     * @param nachnamenArray Die Nachnamen, zu denen die Vornamen gesucht werden sollen.
     * @param stub Die Schnittstelle zum Server wird entgegengenommen.
     * @throws IOException Waehrend der Verbindung zum Server koennen IOExceptions auftreten.
     */
    public void vornamenAusgeben(String[] nachnamenArray, Namenssuche stub) throws IOException{
        ArrayList<VornamensZuordnung> ergebnis;
        StringBuilder vornamen;

        ergebnis = stub.sucheVornamen(nachnamenArray);

        System.out.printf("%-15s|%-50s", "Nachname:", "Vornamen:");
        System.out.println();


        for (VornamensZuordnung vz : ergebnis) {

            vornamen = new StringBuilder();

            if(vz.getVornamen() != null){
                for(String vorname : vz.getVornamen()){
                    vornamen.append(vorname).append(", ");
                }
                vornamen.deleteCharAt(vornamen.length() - 2);
            }

            System.out.printf("%-15s|%-50s", vz.getNachname(), vornamen);
            System.out.println();

        }

    }

    /**
     * Es wird ueberprueft ob das Programm beendet werden soll.
     *
     * @return Das Ergebnis der Ueberpruefung ob das Programm beendet werden soll.
     */
    public Boolean ueberpruefeEnde(){
        Boolean ende = false;
        String endePruefen;

        System.out.println("\nNach weiteren Vornamen suchen?");
        System.out.println("j/n");
        System.out.print("-->");

        endePruefen = input.nextLine();

        if(!endePruefen.trim().toLowerCase().equals("j")){
            ende = true;
        }

        return ende;
    }

    /**
     * Main-methode
     * Verbindung zum Server wird aufgebaut
     * Dialog wird gestartet
     *
     * @param args Die IP-Adresse des Servers wird übergeben
     */
    public static void main(String[] args){
        String host = (args.length < 1) ? null:args[0];
        try {
            //Verbindungsaufbau
            Registry registry =
                    LocateRegistry.getRegistry(host, Konstanten.REGISTRY_PORT);

            //Schnittstelle auf Server wird gesucht
            Namenssuche stub = (Namenssuche) registry.lookup("Namenssuche");

            if(host == null) {
                System.out.println("Verbindung zum Namenssuche-Server auf localhost erfolgreich.");
            }else{
                System.out.println("Verbindung zum Namenssuche-Server auf IP " + host + " erfolgreich.");
            }

            Client client = new Client();
            client.start(stub);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
