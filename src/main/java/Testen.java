/**
 *
 */

public class Testen {
    public static void main(String[] args){
        System.out.println("Hallo Verteilte Systeme!");

        Server server = new Server();

        String[] s = {"a", "bc"};

        server.sucheVornamen(s);
    }
}
