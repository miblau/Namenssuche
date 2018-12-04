import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client {

    private Client(){

    }

    public void start(Namenssuche stub){
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

    public static void main(String[] args){
        String host = (args.length < 1) ? null:args[0];
        try {
            Registry registry =
                    LocateRegistry.getRegistry(host, 42424);
            Namenssuche stub = (Namenssuche) registry.lookup("Namenssuche");

            Client client = new Client();
            client.start(stub);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
















}
