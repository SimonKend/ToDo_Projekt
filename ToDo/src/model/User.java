package model;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class User {
    private String username;
    private int passwortGehashed;
    private LinkedHashSet<ToDo> userToDos = new LinkedHashSet<>();

//für später
/*
    private int id;
    private String vorname;
    private String nachname;
    private LocalDateTime geburtsdatum;

    // nur als Idee

*/


    public User(String username, int passwortGehashed) {
        setUsername(username);
        setPasswortGehashed(passwortGehashed);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    @Override
    public String toString() {
        return "Username: " + username + ", Passwort(Hash): " + passwortGehashed;
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

    public ToDo getToDo(String name) {
        for (ToDo x : userToDos) {
            if (x.getName().equals(name)) {
                return x;
            }
        }
        return null;
    }

    public int getPasswortGehashed() {
        return passwortGehashed;
    }

    public void setPasswortGehashed(int passwortGehashed) {
        this.passwortGehashed = passwortGehashed;
    }

    public LinkedHashSet<ToDo> getUserToDos() {
        return userToDos;
    }

    public void setUserToDos(LinkedHashSet<ToDo> userToDos) {
        this.userToDos = userToDos;
    }

    @Override
    public int hashCode() {
        return username.hashCode(); //Es darf ein Username nur einmal vorkommen
    }
}
