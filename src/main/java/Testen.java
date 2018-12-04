import model.VornamensZuordnung;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */

public class Testen {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        String[] s = {"pOtter", "WeAslEy", "GrangER", "Granger         "};

        ArrayList<VornamensZuordnung> ergebnis = server.sucheVornamen(s);//TODO: Was passiert bei Übergabe eines nicht vorhandenen Nachnamens? Wie behandeln?

        for(VornamensZuordnung vz : ergebnis){
            System.out.println(vz);
        }
    }
}
