package model;

import java.io.*;

public class FileHandler {

    public String dateiEinlesen(String datei) {
        String strDateiInhalt = "";

        try (FileReader fr = new FileReader(datei)) {
            int x = 0;

            while ((x = fr.read()) != -1) {
                strDateiInhalt += (char) x;
            }

//            System.out.println("Dateiinhalt: " + strDateiInhalt);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strDateiInhalt;
    }

//Datei schreiben mit einem FileWriter
    /*
    public void dateiSchreiben(String dateiZiel, HashSet<User> setUsers) {
        try (FileWriter fw = new FileWriter(dateiZiel)) {


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


*/
}
