package Entities;

public class UserRole {

    private int id;
    private String roleName;

    public UserRole () {}

    // Getters
    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
