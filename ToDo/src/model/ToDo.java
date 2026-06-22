package model;

public class ToDo {
    private String name;
    //noch viel mehr Attribute :-)

    public ToDo(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
