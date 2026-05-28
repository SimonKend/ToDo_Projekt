package model;

import java.util.LinkedList;

public class DataHandler {
    private LinkedList<String> listEingelesen = new LinkedList<>();
    private LinkedList<String> listBerechnet = new LinkedList<>();

    public String printList() {
        String str = "";
        for (String x : listEingelesen) {
            str += x;
            str += "\n";
        }
        return str;
    }

    public LinkedList<String> getListBerechnet() {
        return listBerechnet;
    }

    public void setListBerechnet(LinkedList<String> listBerechnet) {
        this.listBerechnet = listBerechnet;
    }

    public LinkedList<String> getListEingelesen() {
        return listEingelesen;
    }

    public void setListEingelesen(LinkedList<String> listEingelesen) {
        this.listEingelesen = listEingelesen;
    }
}
