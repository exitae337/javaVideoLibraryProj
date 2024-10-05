package Entities;

public class ActorOrDirector {

    private int id;
    private String personFullName;
    private String personType;

    public ActorOrDirector() {}

    // Getters

    public int getId() {
        return id;
    }

    public String getPersonFullName() {
        return personFullName;
    }

    public String getPersonType() {
        return personType;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setPersonFullName(String personFullName) {
        this.personFullName = personFullName;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}
