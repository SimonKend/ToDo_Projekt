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
}
