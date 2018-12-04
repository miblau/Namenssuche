import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client(){

    }

    public void start(Namenssuche stub){
        try {
            System.out.println("Hallo Verteilte Systeme!");

            String[] s = {"pOtter", "WeAslEy", "GrangER", "Granger         "};

            for (int i = 0; i < s.length; i++) {
                s[i] = s[i].toLowerCase().trim();
            }

            stub.sucheVornamen(s); //TODO: Was passiert bei Übergabe eines nicht vorhandenen Nachnamens? Wie behandeln?
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
