package model;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;


public class FileHandler {
    public LinkedList<String> dateiEinlesen(String datei) {
        LinkedList<String> meineList = new LinkedList<>();

        try (FileReader fr = new FileReader(datei)) {
            int x = 0;
            String str = "";

            while ((x = fr.read()) != -1) {
                str += (char) x;
            }

            System.out.println(str);
            String[] arr = str.split(";");
            for (String s : arr) {
                meineList.add(s);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Liste:" + meineList);
        return meineList;
    }


    public void dateiSchreiben(String dateiZiel, LinkedList<String> list) {
        try (FileWriter fw = new FileWriter(dateiZiel)) {
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
