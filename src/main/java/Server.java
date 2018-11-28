import java.io.*;

public class Server implements Namenssuche {

    public Server(){

    }


    public String[] sucheVornamen(String[] nachnamen){
        String[] vornamen = nachnamen;

        File file = new File(Konstanten.DATEINAME_NAMENSLISTE);

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);

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
