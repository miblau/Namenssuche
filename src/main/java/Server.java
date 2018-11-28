import java.io.*;

public class Server implements Namenssuche {

    public Server(){

    }


    public String[] sucheVornamen(String[] nachnamen) {
        String[] vornamen = nachnamen;

        File file = new File(Konstanten.DATEINAME_NAMENSLISTE);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        FileReader fr = null;
        try {
            fr = new FileReader(Konstanten.DATEINAME_NAMENSLISTE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);

        String zeile = null;
        try {
            zeile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(zeile);

        return vornamen;
    }
}
