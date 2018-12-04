import java.io.IOException;

/**
 *
 */

public class Testen {
    public static void main(String[] args) throws IOException {
        System.out.println("Hallo Verteilte Systeme!");

        Server server = new Server();

        String[] s = {"pOtter", "WeAslEy", "GrangER", "Granger         "};

        for(int i = 0; i < s.length; i++){
            s[i] = s[i].toLowerCase().trim();
        }

        server.sucheVornamen(s); //TODO: Was passiert bei Übergabe eines nicht vorhandenen Nachnamens? Wie behandeln?
    }
}
