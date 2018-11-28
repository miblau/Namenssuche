import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Launcher {

    public static void main(String[] args){
       try {
           Server server = new Server();
           Namenssuche stub = (Namenssuche) UnicastRemoteObject.exportObject(server, 0);

           Registry registry = LocateRegistry.getRegistry();
           registry.bind("Namenssuche", stub);

       }catch(Exception e){
           e.printStackTrace();
       }
    }












}
