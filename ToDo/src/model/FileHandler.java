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

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strDateiInhalt;
    }
    public void dateiSchreiben(String dateiZiel, HashSet<User> setUsers) {
        try (FileWriter fw = new FileWriter(dateiZiel)) {
            for (User u : setUsers) {
                String todoListe = "";

                for(ToDo t : u.getUserToDos()){
                    todoListe = todoListe + t.getName() + ",";
                }

                fw.write(u.getUsername() + ";" +
                        u.getPasswortGehashed() + ";" +
                        todoListe + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Fehler beim Schreiben: " + ex.getMessage());
        }
    }
}
