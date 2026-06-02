package model;

import java.util.HashSet;

public class DataHandler {
    private HashSet<User> usersSet = new HashSet<>();
    private String loggedInUser = "Username";

    public void init(String dateiInhalt) {
        if (dateiInhalt == null || dateiInhalt.trim().isEmpty()) {
            return;
        }
        String[] zeilen = dateiInhalt.split("\\r?\\n");

        for (int i = 0; i < zeilen.length; i++) {
            String zeile = zeilen[i].trim();
            if (zeile.isEmpty()) {
                continue;
            }
            String[] userDaten = zeile.split(";");

            if (userDaten.length == 2) {
                String username = userDaten[0].trim();
                String passwort = userDaten[1].trim();

                addUser(username, passwort);
            }
        }
    }


    public void addUser(String username, String passwortNichtGehashed) {
        usersSet.add(new User(username, passwortNichtGehashed));
    }

    public boolean addUserWithCheck(String username, String passwortNichtGehashed) {
        return usersSet.add(new User(username, passwortNichtGehashed));
    }

    public String checkUser(String username, String passwortNichtGehashed) {   //Überprüft, ob der User existiert und ob das Passwort stimmt
        if (username.isEmpty()) {
            return "Username is empty.";
        }
        if (passwortNichtGehashed.isEmpty()) {
            return "Password is empty.";
        }

        //Das Set durchlaufen
        for (User x : usersSet) {
            if (x.getUsername().equals(username)) {     //Username vergleichen
                if (x.getPasswortNichtGehashed().equals(passwortNichtGehashed)) {       //Wenn Username passt: Passwort vergleichen
                    return "login successful.";
                } else {
                    return "Wrong password!";
                }
            }
        }
        //Wenn gar kein Username übereinstimmt
        return "This user doesn't exist.";
    }

    public void printSet() {
        for (User x : usersSet) {
            System.out.println(x.toString());
        }
    }

    public HashSet<User> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(HashSet<User> usersSet) {
        this.usersSet = usersSet;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
