package model;

import java.io.*;
import java.util.HashSet;

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
    public void dateiSchreiben(String dateiZiel, HashSet<User> setUsers) {
        try (FileWriter fw = new FileWriter(dateiZiel)) {
            for (User u : setUsers) {
                // Format: username;passwort und ein Zeilenumbruch (\n)
                fw.write(u.getUsername() + ";" + u.getPasswortNichtGehashed() + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Fehler beim Schreiben: " + ex.getMessage());
        }
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
