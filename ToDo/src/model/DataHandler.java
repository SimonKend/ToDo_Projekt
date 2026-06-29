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

            if (userDaten.length >= 2) {

                String username = userDaten[0].trim();
                String passwort = userDaten[1].trim();

                User u = new User(username, Integer.parseInt(passwort));

                if (userDaten.length > 2) {

                    String[] todos = userDaten[2].split(",");

                    for (String todo : todos) {

                        if (!todo.equals("")) {
                            u.getUserToDos().add(new ToDo(todo));
                        }
                    }
                }

                usersSet.add(u);
            }
        }
    }


    public void addUser(String username, String passwortNichtGehashed) {
        int passwortGehashed;
        passwortGehashed = passwortNichtGehashed.hashCode();

        usersSet.add(new User(username, passwortGehashed));
    }

    public boolean addUserWithCheck(String username, String passwortNichtGehashed) {

        int passwortGehashed;
        passwortGehashed = passwortNichtGehashed.hashCode();

        return usersSet.add(new User(username, passwortGehashed));
    }

    public String checkUser(String username, String passwortNichtGehashed) {   //Überprüft, ob der User existiert und ob das Passwort stimmt
        if (username.isEmpty()) {
            return "Username is empty.";
        }
        if (passwortNichtGehashed.isEmpty()) {
            return "Password is empty.";
        }

        int passwortGehashed;
        passwortGehashed = passwortNichtGehashed.hashCode();

        //Das Set durchlaufen
        for (User x : usersSet) {
            if (x.getUsername().equals(username)) {     //Username vergleichen
                if (x.getPasswortGehashed() == passwortGehashed) {       //Wenn Username passt: Passwort vergleichen
                    return "login successful.";
                } else {
                    return "Wrong password!";
                }
            }
        }
        //Wenn gar kein Username übereinstimmt
        return "This user doesn't exist.";
    }

    public void deleteUser(String username) {
      User user;
      user = getUser(username);
      usersSet.remove(user);
    }

    public User getUser(String username) {
        for (User x : usersSet) {
            if (x.getUsername().equals(username)) {     //Username vergleichen
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
