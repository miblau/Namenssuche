import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/**
 * Dient zum Starten des RMI-Servers.
 * Dazu wird eine neue Registry erstellt und der Server wird dort eingetragen.
 *
 * @author Johannes Gerwert
 * @author Michelle Blau
 * @version 11.12.2018
 */
public class Launcher {

    /**
     * Main-Methode
     * Ein neues Server Objekt wird erstellt und an port 0 in einen stub verwandelt.
     * Eine neue Registry wird erstellt und der stub wird dort eingetragen.
     *
     * @param args wird nicht verwendet
     */
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
