package model;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class FileHandler {

    public String dateiEinlesen(String datei) {
        StringBuilder sb = new StringBuilder();
        File f = new File(datei);
        if (!f.exists()) return "";

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                sb.append(zeile).append("\n");
            }
        } catch (IOException ex) {
            System.out.println("Fehler beim Lesen: " + ex.getMessage());
        }
        return sb.toString();
    }

    public void dateiSchreiben(String dateiZiel, HashSet<User> setUsers) {
        try (FileWriter fw = new FileWriter(dateiZiel)) {
            for (User u : setUsers) {
                String sOffen = listToString(u.getSchuleOffen());
                String sErledigt = listToString(u.getSchuleErledigt());
                String aOffen = listToString(u.getArbeitOffen());
                String aErledigt = listToString(u.getArbeitErledigt());
                String zOffen = listToString(u.getZuHauseOffen());
                String zErledigt = listToString(u.getZuHauseErledigt());

                fw.write(u.getUsername() + ";" + u.getPasswortGehashed() + ";" +
                        sOffen + "|" + sErledigt + "|" + aOffen + "|" + aErledigt + "|" + zOffen + "|" + zErledigt + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Fehler beim Schreiben: " + ex.getMessage());
        }
    }

    private String listToString(LinkedHashSet<ToDo> liste) {
        if (liste.isEmpty()) return "LEER";
        String res = "";
        for (ToDo t : liste) {
            res += t.getName() + ",";
        }
        return res;
    }
}
