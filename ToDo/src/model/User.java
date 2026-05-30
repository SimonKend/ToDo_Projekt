package model;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class User {
    private String username;
    private String passwortNichtGehashed;

//für später
/*
    private int id;
    private String vorname;
    private String nachname;
    private LocalDateTime geburtsdatum;

    // nur als Idee
    private LinkedHashSet<ToDo> userToDos = new LinkedHashSet<>();

*/


    public User(String username, String passwortNichtGehashed) {
        setUsername(username);
        setPasswortNichtGehashed(passwortNichtGehashed);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswortNichtGehashed() {
        return passwortNichtGehashed;
    }

    public void setPasswortNichtGehashed(String passwortNichtGehashed) {
        this.passwortNichtGehashed = passwortNichtGehashed;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Passwort(nicht gehashed): " + passwortNichtGehashed;
    }

    //equals-Methode
    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (!(obj instanceof User)) {
            return false;
        }

        // Typecast obj to User so that we can compare data members
        User usr = (User) obj;
        // Compare the data members and return accordingly
        return this.username.equals(usr.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode(); //Es darf ein Username nur einmal vorkommen
    }
}
