package model;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class DataHandler {
    private HashSet<User> usersSet = new HashSet<>();
    private String loggedInUser = "Username";

    public void init(String dateiInhalt) {
        if (dateiInhalt == null || dateiInhalt.trim().isEmpty()) {
            return;
        }
        String[] zeilen = dateiInhalt.split("\\r?\\n");

        for (String zeile : zeilen) {
            zeile = zeile.trim();
            if (zeile.isEmpty()) continue;

            String[] userDaten = zeile.split(";");
            if (userDaten.length >= 2) {
                String username = userDaten[0].trim();
                String passwort = userDaten[1].trim();
                User u = new User(username, Integer.parseInt(passwort));

                if (userDaten.length > 2) {
                    String[] alleListen = userDaten[2].split("\\|");
                    if (alleListen.length == 6) {
                        befuelleListe(u.getSchuleOffen(), alleListen[0]);
                        befuelleListe(u.getSchuleErledigt(), alleListen[1]);
                        befuelleListe(u.getArbeitOffen(), alleListen[2]);
                        befuelleListe(u.getArbeitErledigt(), alleListen[3]);
                        befuelleListe(u.getZuHauseOffen(), alleListen[4]);
                        befuelleListe(u.getZuHauseErledigt(), alleListen[5]);
                    }
                }
                usersSet.add(u);
            }
        }
    }

    private void befuelleListe(LinkedHashSet<ToDo> targetList, String daten) {
        if (daten.equals("LEER") || daten.trim().isEmpty()) return;
        String[] todos = daten.split(",");
        for (String todo : todos) {
            if (!todo.trim().isEmpty()) {
                targetList.add(new ToDo(todo.trim()));
            }
        }
    }

    public void addUser(String username, String passwortNichtGehashed) {
        int passwortGehashed = passwortNichtGehashed.hashCode();
        usersSet.add(new User(username, passwortGehashed));
    }

    public boolean addUserWithCheck(String username, String passwortNichtGehashed) {
        int passwortGehashed = passwortNichtGehashed.hashCode();
        return usersSet.add(new User(username, passwortGehashed));
    }

    public String checkUser(String username, String passwortNichtGehashed) {
        if (username.isEmpty()) return "Username is empty.";
        if (passwortNichtGehashed.isEmpty()) return "Password is empty.";

        int passwortGehashed = passwortNichtGehashed.hashCode();
        for (User x : usersSet) {
            if (x.getUsername().equals(username)) {
                if (x.getPasswortGehashed() == passwortGehashed) {
                    return "login successful.";
                } else {
                    return "Wrong password!";
                }
            }
        }
        return "This user doesn't exist.";
    }

    public User getUser(String username) {
        for (User x : usersSet) {
            if (x.getUsername().equals(username)) {
                return x;
            }
        }
        return null;
    }

    public void printSet() {
        for (User x : usersSet) {
            System.out.println(x.toString());
        }
    }

    public HashSet<User> getUsersSet() { return usersSet; }
    public void setUsersSet(HashSet<User> usersSet) { this.usersSet = usersSet; }
    public String getLoggedInUser() { return loggedInUser; }
    public void setLoggedInUser(String loggedInUser) { this.loggedInUser = loggedInUser; }
}
