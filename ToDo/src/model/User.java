package model;

import java.util.LinkedHashSet;

public class User {
    private String username;
    private int passwortGehashed;

    // Die 6 Listen für die 3 Kategorien (jeweils offen und erledigt)
    private LinkedHashSet<ToDo> schuleOffen = new LinkedHashSet<>();
    private LinkedHashSet<ToDo> schuleErledigt = new LinkedHashSet<>();

    private LinkedHashSet<ToDo> arbeitOffen = new LinkedHashSet<>();
    private LinkedHashSet<ToDo> arbeitErledigt = new LinkedHashSet<>();

    private LinkedHashSet<ToDo> zuHauseOffen = new LinkedHashSet<>();
    private LinkedHashSet<ToDo> zuHauseErledigt = new LinkedHashSet<>();

    public User(String username, int passwortGehashed) {
        setUsername(username);
        setPasswortGehashed(passwortGehashed);
    }

    // Getter und Setter für alle 6 Listen
    public LinkedHashSet<ToDo> getSchuleOffen() { return schuleOffen; }
    public void setSchuleOffen(LinkedHashSet<ToDo> schuleOffen) { this.schuleOffen = schuleOffen; }

    public LinkedHashSet<ToDo> getSchuleErledigt() { return schuleErledigt; }
    public void setSchuleErledigt(LinkedHashSet<ToDo> schuleErledigt) { this.schuleErledigt = schuleErledigt; }

    public LinkedHashSet<ToDo> getArbeitOffen() { return arbeitOffen; }
    public void setArbeitOffen(LinkedHashSet<ToDo> arbeitOffen) { this.arbeitOffen = arbeitOffen; }

    public LinkedHashSet<ToDo> getArbeitErledigt() { return arbeitErledigt; }
    public void setArbeitErledigt(LinkedHashSet<ToDo> arbeitErledigt) { this.arbeitErledigt = arbeitErledigt; }

    public LinkedHashSet<ToDo> getZuHauseOffen() { return zuHauseOffen; }
    public void setZuHauseOffen(LinkedHashSet<ToDo> zuHauseOffen) { this.zuHauseOffen = zuHauseOffen; }

    public LinkedHashSet<ToDo> getZuHauseErledigt() { return zuHauseErledigt; }
    public void setZuHauseErledigt(LinkedHashSet<ToDo> zuHauseErledigt) { this.zuHauseErledigt = zuHauseErledigt; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getPasswortGehashed() { return passwortGehashed; }
    public void setPasswortGehashed(int passwortGehashed) { this.passwortGehashed = passwortGehashed; }

    @Override
    public String toString() {
        return "Username: " + username + ", Passwort(Hash): " + passwortGehashed;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) return false;
        User usr = (User) obj;
        return this.username.equals(usr.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
