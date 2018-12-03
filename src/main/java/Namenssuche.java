import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.Remote;

public interface Namenssuche extends Remote {

    public VornamensZuordnung[] sucheVornamen(String[] nachnamen) throws IOException;

}
