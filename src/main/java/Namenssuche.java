import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Namenssuche extends Remote {

    public String[] sucheVornamen(String[] nachnamen);

}
