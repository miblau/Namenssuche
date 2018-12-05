import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

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

        ergebnis = stub.sucheVornamen(nachnamenArray);

        for(VornamensZuordnung vz : ergebnis){
            System.out.println(vz);
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

        if(endePruefen.trim().toLowerCase().equals("n")){
            ende = true;
        }

        return ende;
    }

    /**
     * Überprüfung, ob Verbindung funktioniert
     *
     * @param stub Die Schnittstelle zum Server wird entgegengenommen
     */
    public void verbindungsTest(Namenssuche stub){
        //TODO: entfernen
        try {
            System.out.println("Hallo Verteilte Systeme!");

            String[] s = {"pOtter", "WeAslEy", "GrangER", "Granger         "};
            //TODO: Was passiert bei Übergabe eines nicht vorhandenen Nachnamens? Wie behandeln?
            ArrayList<VornamensZuordnung> ergebnis = stub.sucheVornamen(s);

            for(VornamensZuordnung vz : ergebnis){
                System.out.println(vz);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
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
                    LocateRegistry.getRegistry(host, 42424);

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
