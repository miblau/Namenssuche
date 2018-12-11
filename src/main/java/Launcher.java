import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Dient zum Starten des RMI-Servers
 *
 * @author Michelle Blau
 */
public class Launcher {

    public static void main(String[] args){
       try {
           Server server = new Server();
           Namenssuche stub = (Namenssuche) UnicastRemoteObject.exportObject(server, 0);

           LocateRegistry.createRegistry(Konstanten.REGISTRY_PORT);
           Registry registry = LocateRegistry.getRegistry(Konstanten.REGISTRY_PORT);
           registry.bind("Namenssuche", stub);

       }catch(Exception e){
           e.printStackTrace();
       }
    }












}
