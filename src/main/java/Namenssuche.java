import model.VornamensZuordnung;

import java.io.IOException;
import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Listet Remote-Services in Form von Methoden auf.
 *
 * @author Michelle Blau
 */
public interface Namenssuche extends Remote {

    public ArrayList<VornamensZuordnung> sucheVornamen(String[] nachnamen) throws IOException;

}
