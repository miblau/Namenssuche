import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Launcher {

    public static void main(String[] args){
       try {
           Server server = new Server();
           Namenssuche stub = (Namenssuche) UnicastRemoteObject.exportObject(server, 0);

           LocateRegistry.createRegistry(42424);
           Registry registry = LocateRegistry.getRegistry(42424);
           registry.bind("Namenssuche", stub);

       }catch(Exception e){
           e.printStackTrace();
       }
    }












}
