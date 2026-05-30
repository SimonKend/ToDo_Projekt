package model;

import java.util.HashSet;

public class DataHandler {
    private HashSet<User> usersSet = new HashSet<>();
    private String loggedInUser = "Username";

    public void init(String dateiInhalt) { //Diese Methode befüllt das Set
        String[] arr = dateiInhalt.split(";|\\R");      //RegEx Bedeutung: der String wird entweder bei einem ";" oder bei einem Zeilenumbruch (\\R) gesplitted

//        System.out.println("arr:" + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i += 2) {
            addUser(arr[i], arr[i + 1]); //Es werden neue User hinzugefügt
        }
    }


    public void addUser(String username, String passwortNichtGehashed) {
        usersSet.add(new User(username, passwortNichtGehashed));
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
