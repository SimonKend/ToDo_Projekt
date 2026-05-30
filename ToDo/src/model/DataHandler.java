package model;

import java.util.HashSet;

public class DataHandler {
    private HashSet<User> usersSet = new HashSet<>();

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

    public void printSet() {
        for (User x : usersSet) {
            System.out.println(x.toString());
        }
    }

//vielleicht für später (ist jetzt einmal egal):
/*
    public String printUsers() {
        StringBuilder sb = new StringBuilder();

        for (User x : usersSet) {
            sb.append(x);
            sb.append("\n");
        }
        return sb.toString();
    }
*/
}
