import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args){
        String host = (args.length < 1) ? null:args[0];
        try {
            Registry registry =
                    LocateRegistry.getRegistry(host);
            Namenssuche stub = (Namenssuche) registry.lookup("Namenssuche");
            String response= null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
















}
