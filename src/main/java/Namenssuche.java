import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

public interface Namenssuche extends Remote {

    public ArrayList<VornamensZuordnung> sucheVornamen(String[] nachnamen) throws IOException;

}
